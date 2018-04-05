/**
 * framework-core
 * DaoImpl.java
 */
package com.liaowei.framework.dao.impl;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.hibernate.query.criteria.internal.OrderImpl;

import com.google.common.base.CaseFormat;
import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import com.liaowei.framework.core.dao.impl.BaseDaoImpl;
import com.liaowei.framework.core.exception.ApplicationException;
import com.liaowei.framework.dao.IDao;
import com.liaowei.framework.entity.SpringBaseEntity;
import com.liaowei.framework.page.Pagination;

/**
 * DaoImpl
 *
 * Dao层超类实现
 *
 * @author liaowei
 * @date 创建时间：2018年4月1日 上午9:50:12 
 * @see com.liaowei.framework.dao.IDao<T, PK>
 * @since jdk1.8
 */
public abstract class DaoImpl<T extends SpringBaseEntity, PK extends Serializable> extends BaseDaoImpl<T, PK> implements IDao<T, PK> {

    @Resource(name = "sessionFactory")
    protected SessionFactory sessionFactory;

    @Override
    public T findEntity(PK pk) {
        return sessionFactory.getCurrentSession().get(getEntityClass(), pk);
    }

    @Override
    public T addEntity(T entity) {
        sessionFactory.getCurrentSession().save(entity);
        return entity;
    }

    @Override
    public T updateEntity(T entity) {
        sessionFactory.getCurrentSession().update(entity);
        return entity;
    }

    @Override
    public List<T> findList(T entity) throws ApplicationException {
        List<T> resultList;

        try {
            Class<T> entityClass = getEntityClass();
            Session session = sessionFactory.getCurrentSession();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<T> criteria = criteriaBuilder.createQuery(entityClass);
            Root<T> root = criteria.from(entityClass);
            criteria.select(root);
            List<Predicate> predicateList = Lists.newArrayList();
            if (SpringBaseEntity.class.isAssignableFrom(entityClass)) {
                Method[] declaredMethods = entityClass.getMethods();
                Class<?> returnType;
                Object returnData;
                String fieldName;
                for (Method method : declaredMethods) {
                    String methodName = method.getName();
                    if (methodName.startsWith("get") && !Objects.equal("getClass", methodName)) {
                        returnType = method.getReturnType();
                        returnData = method.invoke(entity);
                        fieldName = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, methodName.replace("get", ""));
                        if (null != returnData) {
                            predicateList.add(criteriaBuilder.equal(root.get(fieldName), returnType.cast(returnData)));
                        }
                    }
                }
            }
            if (predicateList.isEmpty()) {
                resultList = Lists.newArrayList();
            } else {
                criteria.where(criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()])));
                resultList = session.createQuery(criteria).getResultList();
            }
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            LOGGER.error(e.getMessage(), e);
            resultList = Lists.newArrayList();
            throw new ApplicationException(e);
        }
        return resultList;
    }

    @Override
    public Pagination<T> findPage(Pagination<T> page, T entity) throws ApplicationException {
        List<T> resultList;

        try {
            Class<T> entityClass = getEntityClass();
            Session session = sessionFactory.getCurrentSession();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<T> criteria = criteriaBuilder.createQuery(entityClass);
            CriteriaQuery<Tuple> countCriteria = criteriaBuilder.createQuery(Tuple.class);
            Root<T> root = criteria.from(entityClass);
            Root<T> countRoot = countCriteria.from(entityClass);
            criteria.select(root);
            countCriteria.select(criteriaBuilder.tuple(criteriaBuilder.count(countRoot)));
            List<Predicate> predicateList = Lists.newArrayList();
            List<Predicate> countPredicateList = Lists.newArrayList();
            if (SpringBaseEntity.class.isAssignableFrom(entityClass)) {
                Method[] declaredMethods = entityClass.getMethods();
                Class<?> returnType;
                Object returnData;
                String fieldName;
                for (Method method : declaredMethods) {
                    String methodName = method.getName();
                    if (methodName.startsWith("get") && !Objects.equal("getClass", methodName)) {
                        returnType = method.getReturnType();
                        returnData = method.invoke(entity);
                        fieldName = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, methodName.replace("get", ""));
                        if (null != returnData) {
                            predicateList.add(criteriaBuilder.equal(root.get(fieldName), returnType.cast(returnData)));
                            countPredicateList.add(criteriaBuilder.equal(countRoot.get(fieldName), returnType.cast(returnData)));
                        }
                    }
                }
            }
            countCriteria.where(criteriaBuilder.and(countPredicateList.toArray(new Predicate[countPredicateList.size()])));
            Tuple tuple = session.createQuery(countCriteria).getSingleResult();
            if (null != tuple && null != tuple.get(0)) {
                Integer total = Integer.valueOf(String.valueOf(tuple.get(0)));
                if (0 < total.intValue()) {
                    page.setTotal(total);
                    criteria.where(criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()])));
                    Order order = new OrderImpl(root.get("modifyTime"), true);
                    criteria.orderBy(order);
                    Query<T> query = session.createQuery(criteria);
                    query.setFirstResult(page.getStartPosition());
                    query.setMaxResults(page.getPageSize());
                    resultList = query.getResultList();
                } else {
                    resultList = Lists.newArrayList();
                }
            } else {
                resultList = Lists.newArrayList();
            }
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            LOGGER.error(e.getMessage(), e);
            resultList = Lists.newArrayList();
            throw new ApplicationException(e);
        }
        page.setData(resultList);
        return page;
    }

    @Override
    public void delEntity(PK pk) {
        Class<T> entityClass = getEntityClass();
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaUpdate<T> criteria = criteriaBuilder.createCriteriaUpdate(entityClass);
        Root<T> root = criteria.from(entityClass);
        criteria.set("valid", Boolean.FALSE);
        criteria.where(criteriaBuilder.and(criteriaBuilder.equal(root.get("id"), pk)));
        session.createQuery(criteria).executeUpdate();
    }

    @Override
    public void delList(List<PK> pks) {
        Class<T> entityClass = getEntityClass();
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaUpdate<T> criteria = criteriaBuilder.createCriteriaUpdate(entityClass);
        Root<T> root = criteria.from(entityClass);
        criteria.set("valid", Boolean.FALSE);
        Expression<String> exp = root.get("id");
        Predicate predicate = exp.in(pks);
        criteria.where(predicate);
        session.createQuery(criteria).executeUpdate();
    }
}