/**
 * framework-core
 * DaoImpl.java
 */
package com.liaowei.framework.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.liaowei.framework.core.dao.impl.BasisDaoImpl;
import com.liaowei.framework.core.exception.ApplicationException;
import com.liaowei.framework.dao.IDao;
import com.liaowei.framework.entity.BaseIdEntity;
import com.liaowei.framework.page.Pagination;
import com.liaowei.framework.query.QueryUtils;
import com.liaowei.framework.query.Where;

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
public abstract class DaoImpl<E extends BaseIdEntity, PK extends Serializable> extends BasisDaoImpl<E, PK> implements IDao<E, PK> {

    @Resource(name = "sessionFactory")
    protected SessionFactory sessionFactory;

    @Override
    public E findEntity(PK id) {
        log.debug("根据主键值查询数据对象, 主键：" + id);
        return sessionFactory.getCurrentSession().get(getEntityClass(), id);
    }

    @Override
    public E addEntity(E entity) {
        log.debug("新增数据，数据：" + entity.toString());
        sessionFactory.getCurrentSession().save(entity);
        return entity;
    }

    @Override
    public E updateEntity(E entity) {
        log.debug("修改数据，数据：" + entity.toString());
        sessionFactory.getCurrentSession().update(entity);
        return entity;
    }

    @Override
    public Pagination<E> findList(Pagination<E> pagination, Where where) throws ApplicationException {
        log.debug("查询数据分页列表，查询条件：" + (null == where ? "无条件" : where.toString()) + ",分页信息：" + (null == pagination ? "无分页" : pagination.toString()));
        List<E> resultList = null;

        try {
            Class<E> entityClass = getEntityClass();
            Session session = sessionFactory.getCurrentSession();
            Query<Long> countQuery = QueryUtils.createCountQuery(entityClass, session, where);
            Long count = countQuery.uniqueResult();
            pagination.setTotal(count.intValue());
            Query<E> query = QueryUtils.createQuery(pagination, entityClass, session, where);
            resultList = query.list();
        } catch (HibernateException e) {
            log.error(e.getMessage(), e);
            resultList = Lists.newArrayList();
            throw new ApplicationException(e);
        }

        pagination.setData(resultList);
        return pagination;
    }

    @Override
    public void delEntity(PK id) {
        log.debug("根据主键值删除一条数据对象, 主键：" + id);
        Class<E> entityClass = getEntityClass();
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaUpdate<E> criteria = criteriaBuilder.createCriteriaUpdate(entityClass);
        Root<E> root = criteria.from(entityClass);
        criteria.set("valid", Boolean.FALSE);
        criteria.where(criteriaBuilder.and(criteriaBuilder.equal(root.get("id"), id)));
        session.createQuery(criteria).executeUpdate();
    }

    @Override
    public void delList(List<PK> ids) {
        log.debug("根据主键值批量删除数据对象, 主键：" + Joiner.on(",").join(ids));
        Class<E> entityClass = getEntityClass();
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaUpdate<E> criteria = criteriaBuilder.createCriteriaUpdate(entityClass);
        Root<E> root = criteria.from(entityClass);
        criteria.set("valid", Boolean.FALSE);
        Expression<String> exp = root.get("id");
        Predicate predicate = exp.in(ids);
        criteria.where(predicate);
        session.createQuery(criteria).executeUpdate();
    }
}