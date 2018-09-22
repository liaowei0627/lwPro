/**
 * flyhaze-framework
 * DaoImpl.java
 */
package com.flyhaze.framework.hibernate.dao.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.flyhaze.framework.core.dao.impl.BasisDaoImpl;
import com.flyhaze.framework.core.exception.ApplicationException;
import com.flyhaze.framework.core.page.Pagination;
import com.flyhaze.framework.core.query.Where;
import com.flyhaze.framework.core.query.order.OrderBy;
import com.flyhaze.framework.hibernate.dao.IDao;
import com.flyhaze.framework.hibernate.entity.BaseIdEntity;
import com.flyhaze.framework.hibernate.query.QueryUtils;

/**
 * DaoImpl
 *
 * Dao层超类实现
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-08 21:33:14
 * @see com.flyhaze.framework.hibernate.dao.IDao<E>
 * @see com.flyhaze.framework.core.dao.impl.BasisDaoImpl<E, String>
 * @since jdk1.8
 */
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
        return getSession().get(getEntityClass(), id);
    }

    @Override
    public E addEntity(E entity) throws ApplicationException {
        Session session = getSession();
        session.save(entity);
        return entity;
    }

    @Override
    public E updateEntity(E entity) throws ApplicationException {
        Session session = getSession();
        session.update(entity);
        return entity;
    }

    @Override
    public Long findCount(Where where) throws ApplicationException {
        Session session = getSession();
        Class<E> entityClass = getEntityClass();
        Query<Long> countQuery = QueryUtils.<E>createCountQuery(entityClass, session, where);
        Long count = countQuery.uniqueResult();
        return count;
    }

    @Override
    public List<E> findList(Where where, List<OrderBy> orderBy) throws ApplicationException {
        List<E> resultList = null;

        Class<E> entityClass = getEntityClass();
        Session session = getSession();
        Query<E> query = QueryUtils.<E>createQuery(entityClass, session, where, orderBy);
        resultList = query.list();

        return resultList;
    }

    @Override
    public Pagination<E> findList(Pagination<E> pagination, Where where) throws ApplicationException {
        List<E> resultList = null;
        int total = 0;

        Session session = getSession();
        Class<E> entityClass = getEntityClass();
        Long count = findCount(where);
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
    public <C> List<C> findList(Where where, String from, Class<C> cls) throws ApplicationException {
        Session session = getSession();
        Query<C> query = QueryUtils.createQuery(session, from, where, cls);
        return query.list();
    }

    @Override
    public <C> C findUnipueResult(Where where, String from, Class<C> cls) throws ApplicationException {
        Session session = getSession();
        Query<C> query = QueryUtils.createQuery(session, from, where, cls);
        return query.uniqueResult();
    }

    @Override
    public void delList(String[] id) throws ApplicationException {
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
    public void excByHQL(String hql, Map<String, Object> param) throws ApplicationException {
        Session session = getSession();

        Query<?> query = session.createQuery(hql);

        if (null != param && !param.isEmpty()) {
            Set<String> paramKeies = param.keySet();
            for (String paramKey : paramKeies) {
                query.setParameter(paramKey, param.get(paramKey));
            }
        }

        query.executeUpdate();
    }
}