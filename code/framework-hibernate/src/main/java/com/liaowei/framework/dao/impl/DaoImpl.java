/**
 * framework-hibernate
 * DaoImpl.java
 */
package com.liaowei.framework.dao.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.google.common.base.Joiner;
import com.google.common.base.Strings;
import com.liaowei.framework.core.dao.impl.BasisDaoImpl;
import com.liaowei.framework.core.exception.ApplicationException;
import com.liaowei.framework.dao.IDao;
import com.liaowei.framework.entity.BaseIdEntity;
import com.liaowei.framework.entity.BaseTreeEntity;
import com.liaowei.framework.page.Pagination;
import com.liaowei.framework.query.QueryUtils;
import com.liaowei.framework.query.Where;
import com.liaowei.framework.query.exception.DuplicationCodeException;
import com.liaowei.framework.query.order.OrderEnum;

import lombok.extern.slf4j.Slf4j;

/**
 * DaoImpl
 *
 * Dao层超类实现
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-08 21:33:14
 * @see com.liaowei.framework.dao.IDao<E>
 * @see com.liaowei.framework.core.dao.impl.BasisDaoImpl<E, String>
 * @since jdk1.8
 */
@Slf4j
public abstract class DaoImpl<E extends BaseIdEntity<E>> extends BasisDaoImpl<E, String> implements IDao<E> {

    @Resource(name = "sessionFactory")
    protected SessionFactory sessionFactory;

    /**
     * 取得单例session
     * 
     * @return
     */
    protected Session getSession() {
        Session session = sessionFactory.getCurrentSession();
        return session;
    }

    @Override
    public E findEntity(String id) throws ApplicationException {
        log.debug("DEBUG：根据主键值查询数据对象, 主键：" + id);
        return getSession().get(getEntityClass(), id);
    }

    @Override
    public E addEntity(E entity) throws ApplicationException {
        log.debug("DEBUG：新增数据，数据：" + entity.toString());
        Session session = getSession();
        session.save(entity);
        return entity;
    }

    @Override
    public E updateEntity(E entityPojo) throws ApplicationException {
        log.debug("DEBUG：修改数据，数据：" + entityPojo.toString());
        Session session = getSession();
        E entity = session.get(getEntityClass(), entityPojo.getId());
        entity.setEntity(entityPojo);
        if (entity instanceof BaseTreeEntity) {
            refreshTree(entityPojo, entity);
        }
        session.update(entity);
        return entityPojo;
    }

    @Override
    public List<E> findList(Where where, Map<String, OrderEnum> orderBy) throws ApplicationException {
        log.debug("DEBUG：查询数据列表，查询条件：" + (null == where ? "无条件" : where.toString()));
        List<E> resultList = null;

        Class<E> entityClass = getEntityClass();
        Session session = getSession();
        Query<E> query = QueryUtils.<E>createQuery(entityClass, session, where, orderBy);
        resultList = query.list();

        return resultList;
    }

    @Override
    public Pagination<E> findList(Pagination<E> pagination, Where where) throws ApplicationException {
        log.debug("DEBUG：查询数据分页列表，查询条件：" + (null == where ? "无条件" : where.toString()) + "，分页信息："
                + (null == pagination ? "无分页" : pagination.toString()));
        List<E> resultList = null;
        int total = 0;

        Class<E> entityClass = getEntityClass();
        Session session = getSession();
        Query<Long> countQuery = QueryUtils.<E>createCountQuery(entityClass, session, where);
        Long count = countQuery.uniqueResult();
        total = count.intValue();
        Query<E> query = QueryUtils.<E>createQuery(pagination, entityClass, session, where);
        resultList = query.list();

        if (null == pagination) {
            pagination = new Pagination<>();
        }
        pagination.setTotal(total);
        pagination.setData(resultList);
        return pagination;
    }

    @Override
    public void delList(String[] id) throws ApplicationException {
        log.debug("DEBUG：根据主键值批量删除数据对象, 主键：" + Joiner.on(",").join(id));
        Session session = getSession();
        Class<E> entityClass = getEntityClass();

        String hql;
        Query<?> query;
        if (1 == id.length) {
            hql = "delete from " + entityClass.getSimpleName() + " t where t.id = :id";
            query = session.createQuery(hql);
            query.setParameter("id", id[0]);
        } else {
            hql = "delete from " + entityClass.getSimpleName() + " t where t.id in (:id)";
            query = session.createQuery(hql);
            query.setParameterList("id", id);
        }
        query.executeUpdate();
    }

    @Override
    public void delChildren(String fullCodePrefix) throws ApplicationException {
        Session session = getSession();

        String hql = "delete from " + getEntityClass().getSimpleName() + " t where t.fullCode like :FullCodePrefix_l";
        Query<?> query = session.createQuery(hql);
        query.setParameter("FullCodePrefix_l", fullCodePrefix);
        query.executeUpdate();
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void refreshFullCode(E entity) throws ApplicationException {
        Session session = getSession();

        BaseTreeEntity tree = (BaseTreeEntity) entity;
        String newCode = tree.getCode();
        String newText = tree.getText();
        BaseTreeEntity parent = tree.getParent();
        String newFullCode = newCode;
        String newFullText = newText;

        BaseTreeEntity newParent = null;
        if (null != parent) {
            E parentEntity = session.get(getEntityClass(), parent.getId());
            if (null != parentEntity) {
                newParent = (BaseTreeEntity) parentEntity;
                String parentFullCode = newParent.getFullCode();
                String parentFullText = newParent.getFullText();
                newFullCode = parentFullCode + "-" + newCode;
                newFullText = parentFullText + "|" + newText;
            }
        }
        tree.setParent(newParent);

        // 检查全路径编号是否重复
        StringBuilder hql = new StringBuilder();
        hql.append("select count(*) from " + getEntityClass().getSimpleName() + " t where fullCode = :fullCode");
        String entityId = entity.getId();
        if (!Strings.isNullOrEmpty(entityId)) {
            hql.append(" and id <> :id");
        }
        Query<Long> query = session.createQuery(hql.toString(), Long.class);
        query.setParameter("fullCode", newFullCode);
        if (!Strings.isNullOrEmpty(entityId)) {
            query.setParameter("id", entityId);
        }
        Long cnt = query.uniqueResult();
        if (cnt.intValue() > 0) {
            throw new DuplicationCodeException("全路径编号重复");
        }

        tree.setFullCode(newFullCode);
        tree.setFullText(newFullText);
    }

    @Override
    public void refreshOrderNum(E entity) throws ApplicationException {
        Session session = getSession();

        @SuppressWarnings("rawtypes")
        BaseTreeEntity tree = (BaseTreeEntity) entity;
        Integer orderNum = tree.getOrderNum();
        if (null == orderNum) {
            String hql = "select max(t.orderNum) from " + getEntityClass().getSimpleName() + " t";
            Query<Long> query = session.createQuery(hql, Long.class);
            Long rs = query.uniqueResult();
            if (null == rs) {
                orderNum = Integer.valueOf(1);
            } else {
                orderNum = Integer.valueOf(rs.intValue() + 1);
            }
            tree.setOrderNum(orderNum);
        }
    }

    /**
     * 修改树结构数据时，刷新fullCode、fullText列值
     * 
     * @param entityPojo
     * @param entity
     * @throws ApplicationException 
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    protected void refreshTree(E entityPojo, E entity) throws ApplicationException {

        BaseTreeEntity treeEntityPojo = (BaseTreeEntity) entityPojo;
        BaseTreeEntity parentPojo = treeEntityPojo.getParent();

        BaseTreeEntity treeEntity = (BaseTreeEntity) entity;
        treeEntity.setParent(parentPojo);
        Set<E> children = treeEntity.getChildren();
        String oldFullCode = treeEntity.getFullCode();
        String oldFullText = treeEntity.getFullText();

        refreshFullCode(entity);
        String newFullCode = treeEntity.getFullCode();
        String newFullText = treeEntity.getFullText();

        if (!oldFullCode.equals(newFullCode) && null != children && !children.isEmpty()) {
            refreshChildren(oldFullCode, oldFullText, newFullCode, newFullText);
        }
    }

    /**
     * 刷新子节点的fullCode和fullText列值
     * 
     * @param oldFullCodePrefix
     * @param oldFullTextPrefix
     * @param newFullCodePrefix
     * @param newFullTextPrefix
     */
    protected void refreshChildren(String oldFullCodePrefix, String oldFullTextPrefix, String newFullCodePrefix,
            String newFullTextPrefix) {
        Session session = getSession();

        String hql = "update " + getEntityClass().getSimpleName() + " t set "
                + "t.fullCode = concat(:newFullCodePrefix, substring(t.fullCode, (length(:oldFullCodePrefix) + 1), length(t.fullCode))),"
                + "t.fullText = concat(:newFullTextPrefix, substring(t.fullText, (length(:oldFullTextPrefix) + 1), length(t.fullText))) "
                + "where t.fullCode like :oldFullCodePrefix_l";
        Query<?> query = session.createQuery(hql);
        query.setParameter("newFullCodePrefix", newFullCodePrefix);
        query.setParameter("oldFullCodePrefix", oldFullCodePrefix);
        query.setParameter("newFullTextPrefix", newFullTextPrefix);
        query.setParameter("oldFullTextPrefix", oldFullTextPrefix);
        query.setParameter("oldFullCodePrefix_l", oldFullCodePrefix + "-%");
        query.executeUpdate();
    }
}