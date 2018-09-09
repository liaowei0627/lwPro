/**
 * framework-core
 * DaoImpl.java
 */
package com.liaowei.framework.dao.impl;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.google.common.base.Joiner;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.liaowei.framework.core.dao.impl.BasisDaoImpl;
import com.liaowei.framework.core.exception.ApplicationException;
import com.liaowei.framework.dao.IDao;
import com.liaowei.framework.entity.BaseIdEntity;
import com.liaowei.framework.entity.BaseTreeEntity;
import com.liaowei.framework.page.Pagination;
import com.liaowei.framework.query.QueryUtils;
import com.liaowei.framework.query.Where;
import com.liaowei.framework.query.operator.CollectionValueComparisonOperator;

import lombok.extern.slf4j.Slf4j;

/**
 * DaoImpl
 *
 * Dao层超类实现
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-08 21:33:14
 * @see com.liaowei.framework.dao.IDao<E, PK>
 * @see com.liaowei.framework.core.dao.impl.BasisDaoImpl<E, PK>
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
        if (entity instanceof BaseTreeEntity) {
            refreshFullCode(entity, session);
            refreshOrderNum(entity, session);
        }
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
            refreshTree(entityPojo, entity, session);
        }
        session.update(entity);
        return entityPojo;
    }

    @Override
    public Pagination<E> findList(Pagination<E> pagination, Where where) throws ApplicationException {
        log.debug("DEBUG：查询数据分页列表，查询条件：" + (null == where ? "无条件" : where.toString()) + ",分页信息："
                + (null == pagination ? "无分页" : pagination.toString()));
        List<E> resultList = null;
        int total = 0;

        try {
            Class<E> entityClass = getEntityClass();
            Session session = getSession();
            Query<Long> countQuery = QueryUtils.createCountQuery(entityClass, session, where);
            Long count = countQuery.uniqueResult();
            total = count.intValue();
            Query<E> query = QueryUtils.createQuery(pagination, entityClass, session, where);
            resultList = query.list();
        } catch (HibernateException e) {
            log.error(e.getMessage(), e);
            resultList = Lists.newArrayList();
            throw new ApplicationException(e);
        }

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

        if (BaseTreeEntity.class.isAssignableFrom(entityClass)) {
            Where where = Where.rootWhere("id", CollectionValueComparisonOperator.IN, id);
            Pagination<E> p = findList(null, where);
            List<E> list = p.getData();
            for (E e : list) {
                @SuppressWarnings("rawtypes")
                BaseTreeEntity tree = (BaseTreeEntity) e;
                delChildren(tree.getFullCode() + "-%", session);
            }
        }

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

    /**
     * 新增数结构数据时，设置fullCode、fullText列值
     * 
     * @param entity
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    protected void refreshFullCode(E entity, Session session) {
        BaseTreeEntity tree = (BaseTreeEntity) entity;
        String newCode = tree.getCode();
        String newText = tree.getText();
        BaseTreeEntity parent = tree.getParent();
        String newFullCode = null;
        String newFullText = null;
        if (null != parent) {
            E parentEntity = session.get(getEntityClass(), parent.getId());
            BaseTreeEntity newParent = (BaseTreeEntity) parentEntity;
            tree.setParent(newParent);
            String parentFullCode = newParent.getFullCode();
            String parentFullText = newParent.getFullText();
            newFullCode = parentFullCode + "-" + newCode;
            newFullText = parentFullText + "|" + newText;
        } else {
            newFullCode = newCode;
            newFullText = newText;
        }
        tree.setFullCode(newFullCode);
        tree.setFullText(newFullText);
    }

    /**
     * 新增树结构数据时，设置orderNum列值
     * 
     * @param entity
     */
    protected void refreshOrderNum(E entity, Session session) {
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
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    protected void refreshTree(E entityPojo, E entity, Session session) {
        BaseTreeEntity treeEntityPojo = (BaseTreeEntity) entityPojo;
        String newCode = treeEntityPojo.getCode();
        String newText = treeEntityPojo.getText();
        BaseTreeEntity parentPojo = treeEntityPojo.getParent();
        String newFullCode = null;
        String newFullText = null;

        BaseTreeEntity treeEntity = (BaseTreeEntity) entity;
        BaseTreeEntity oldParent = treeEntity.getParent();
        Set<E> children = treeEntity.getChildren();
        String oldFullCode = treeEntity.getFullCode();
        String oldFullText = treeEntity.getFullText();

        if (null == parentPojo) {
            newFullCode = newCode;
            newFullText = newText;
        } else {
            String parentId = parentPojo.getId();
            if (!Strings.isNullOrEmpty(parentId)) {
                if (null != oldParent && parentId.equals(oldParent.getId())) {
                    newFullCode = oldFullCode;
                    newFullText = oldFullText;
                } else {
                    E parent = session.get(getEntityClass(), parentId);
                    BaseTreeEntity newParent = (BaseTreeEntity) parent;
                    treeEntity.setParent(newParent);
                    String parentFullCode = newParent.getFullCode();
                    String parentFullText = newParent.getFullText();
                    newFullCode = parentFullCode + "-" + newCode;
                    newFullText = parentFullText + "|" + newText;
                }
            }
        }
        treeEntity.setFullCode(newFullCode);
        treeEntity.setFullText(newFullText);

        if (!oldFullCode.equals(newFullCode) && null != children && !children.isEmpty()) {
            refreshChildren(oldFullCode, oldFullText, newFullCode, oldFullText, session);
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
            String newFullTextPrefix, Session session) {
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

    protected void delChildren(String fullCodePrefix, Session session) {
        String hql = "delete from " + getEntityClass().getSimpleName() + " t where t.fullCode like :FullCodePrefix_l";
        Query<?> query = session.createQuery(hql);
        query.setParameter("FullCodePrefix_l", fullCodePrefix);
        query.executeUpdate();
    }
}