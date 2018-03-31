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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.CaseFormat;
import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import com.liaowei.framework.dao.IDao;
import com.liaowei.framework.entity.IdEntity;
import com.liaowei.framework.exception.ServiceException;
import com.liaowei.framework.page.Pagination;

public abstract class DaoImpl<T extends IdEntity, PK extends Serializable> implements IDao<T, PK> {

    protected final Logger LOGGER = LoggerFactory.getLogger(getClassName());

    @Resource(name = "sessionFactory")
    protected SessionFactory sessionFactory;

    protected abstract String getClassName();
    
    protected abstract Class<T> getEntityClass();

    @Override
    public T findEntity(PK pk) throws ServiceException {
        return sessionFactory.getCurrentSession().get(getEntityClass(), pk);
    }

    @Override
    public T addEntity(T entity) throws ServiceException {
        sessionFactory.getCurrentSession().save(entity);
        return entity;
    }

    @Override
    public T updateEntity(T entity) throws ServiceException {
        sessionFactory.getCurrentSession().update(entity);
        return entity;
    }

    @Override
    public List<T> findList(T entity) throws ServiceException {
        List<T> resultList;
        Class<T> entityClass = getEntityClass();
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteria = criteriaBuilder.createQuery(entityClass);
        Root<T> root = criteria.from(entityClass);
        criteria.select(root);
        List<Predicate> predicateList = Lists.newArrayList();
        if (IdEntity.class.isAssignableFrom(entityClass)) {
            Method[] declaredMethods = entityClass.getMethods();
            Class<?> returnType;
            Object returnData;
            String fieldName;
            try {
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
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                throw new ServiceException(e);
            }
        }
        if (predicateList.isEmpty()) {
            resultList = Lists.newArrayList();
        } else {
            criteria.where(criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()])));
            resultList = session.createQuery(criteria).getResultList();
        }
        return resultList;
    }

    @Override
    public Pagination<T> findPage(Pagination<T> page, T entity) throws ServiceException {
        List<T> resultList;
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
        if (IdEntity.class.isAssignableFrom(entityClass)) {
            Method[] declaredMethods = entityClass.getMethods();
            Class<?> returnType;
            Object returnData;
            String fieldName;
            try {
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
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                throw new ServiceException(e);
            }
        }
        countCriteria.where(criteriaBuilder.and(countPredicateList.toArray(new Predicate[countPredicateList.size()])));
        Tuple tuple = session.createQuery(countCriteria).getSingleResult();
        if (null != tuple && null != tuple.get(0)) {
            page.setTotal(Integer.valueOf(String.valueOf(tuple.get(0))));
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
        page.setData(resultList);
        return page;
    }

    @Override
    public void delEntity(PK pk) throws ServiceException {
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
    public void delList(List<PK> pks) throws ServiceException {
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