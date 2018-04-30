/**
 * framework-core
 * DaoImpl.java
 */
package com.liaowei.framework.dao.impl;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import com.google.common.base.Joiner;
import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import com.liaowei.framework.core.dao.impl.BasisDaoImpl;
import com.liaowei.framework.core.enums.OrderEnum;
import com.liaowei.framework.core.exception.ApplicationException;
import com.liaowei.framework.dao.IDao;
import com.liaowei.framework.entity.BaseEntity;
import com.liaowei.framework.page.Pagination;

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
public abstract class DaoImpl<E extends BaseEntity, PK extends Serializable> extends BasisDaoImpl<E, PK> implements IDao<E, PK> {

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
    public List<E> findList(E entity, Map<String, OrderEnum> orderBy) throws ApplicationException {
        log.debug("查询数据列表，查询条件：" + entity.toString());
        List<E> resultList;

        try {
            Class<E> entityClass = getEntityClass();
            Session session = sessionFactory.getCurrentSession();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<E> criteria = criteriaBuilder.createQuery(entityClass);
            Root<E> root = criteria.from(entityClass);
            criteria.select(root);
            List<Predicate> predicateList = Lists.newArrayList();
            if (BaseEntity.class.isAssignableFrom(entityClass)) {
                Method[] declaredMethods = entityClass.getMethods();
                Class<?> returnType;
                Object returnData;
                String fieldName;
                for (Method method : declaredMethods) {
                    String methodName = method.getName();
                    if (methodName.startsWith("get") && !Objects.equal("getClass", methodName) && !Objects.equal("getHasChild", methodName)) {
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
                Order order;
                if (null == orderBy || orderBy.isEmpty()) {
                    order = new OrderImpl(root.get("modifyTime"), true);
                    criteria.orderBy(order);
                } else {
                    Set<String> keySet = orderBy.keySet();
                    boolean isAsc;
                    if (1 == keySet.size()) {
                        String field = keySet.iterator().next();
                        isAsc = OrderEnum.ASC.equals(orderBy.get(field));
                        order = new OrderImpl(root.get(field), isAsc);
                        criteria.orderBy(order);
                    } else {
                        List<Order> orderList = Lists.newArrayList();
                        for (String field : keySet) {
                            isAsc = OrderEnum.ASC.equals(orderBy.get(field));
                            order = new OrderImpl(root.get(field), isAsc);
                            orderList.add(order);
                        }
                        criteria.orderBy(orderList);
                    }
                }
                resultList = session.createQuery(criteria).getResultList();
            }
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            log.error(e.getMessage(), e);
            resultList = Lists.newArrayList();
            throw new ApplicationException(e);
        }
        return resultList;
    }

    @Override
    public Pagination<E> findPage(Pagination<E> page, E entity, Map<String, OrderEnum> orderBy) throws ApplicationException {
        log.debug("查询数据分页列表，查询条件：" + entity.toString() + ",分页信息：" + page.toString());
        List<E> resultList;

        try {
            Class<E> entityClass = getEntityClass();
            Session session = sessionFactory.getCurrentSession();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<E> criteria = criteriaBuilder.createQuery(entityClass);
            CriteriaQuery<Tuple> countCriteria = criteriaBuilder.createQuery(Tuple.class);
            Root<E> root = criteria.from(entityClass);
            Root<E> countRoot = countCriteria.from(entityClass);
            criteria.select(root);
            countCriteria.select(criteriaBuilder.tuple(criteriaBuilder.count(countRoot)));
            List<Predicate> predicateList = Lists.newArrayList();
            List<Predicate> countPredicateList = Lists.newArrayList();
            if (BaseEntity.class.isAssignableFrom(entityClass)) {
                Method[] declaredMethods = entityClass.getMethods();
                Class<?> returnType;
                Object returnData;
                String fieldName;
                for (Method method : declaredMethods) {
                    String methodName = method.getName();
                    if (methodName.startsWith("get") && !Objects.equal("getClass", methodName) && !Objects.equal("getHasChild", methodName)) {
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
                    Order order;
                    if (null == orderBy || orderBy.isEmpty()) {
                        order = new OrderImpl(root.get("modifyTime"), true);
                        criteria.orderBy(order);
                    } else {
                        Set<String> keySet = orderBy.keySet();
                        boolean isAsc;
                        if (1 == keySet.size()) {
                            String field = keySet.iterator().next();
                            isAsc = OrderEnum.ASC.equals(orderBy.get(field));
                            order = new OrderImpl(root.get(field), isAsc);
                            criteria.orderBy(order);
                        } else {
                            List<Order> orderList = Lists.newArrayList();
                            for (String field : keySet) {
                                isAsc = OrderEnum.ASC.equals(orderBy.get(field));
                                order = new OrderImpl(root.get(field), isAsc);
                                orderList.add(order);
                            }
                            criteria.orderBy(orderList);
                        }
                    }
                    Query<E> query = session.createQuery(criteria);
                    query.setFirstResult(page.getStartPosition());
                    query.setMaxResults(page.getRows());
                    resultList = query.getResultList();
                } else {
                    resultList = Lists.newArrayList();
                }
            } else {
                resultList = Lists.newArrayList();
            }
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            log.error(e.getMessage(), e);
            resultList = Lists.newArrayList();
            throw new ApplicationException(e);
        }
        page.setData(resultList);
        return page;
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