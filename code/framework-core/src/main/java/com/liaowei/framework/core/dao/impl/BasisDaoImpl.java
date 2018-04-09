/**
 * framework-core
 * BasisDaoImpl.java
 */
package com.liaowei.framework.core.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.liaowei.framework.core.dao.IBasisDao;
import com.liaowei.framework.core.entity.IBasisEntity;
import com.liaowei.framework.core.exception.ApplicationException;
import com.liaowei.framework.page.Pagination;

/**
 * BasisDaoImpl
 *
 * Dao层超类实现
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-08 21:25:25
 * @see com.liaowei.framework.core.dao.IBasisDao<T, PK>
 * @since jdk1.8
 */
public abstract class BasisDaoImpl<E extends IBasisEntity, PK extends Serializable> implements IBasisDao<E, PK> {

    protected final Logger LOGGER = LoggerFactory.getLogger(getClassName());

    protected abstract String getClassName();
    
    protected abstract Class<E> getEntityClass();

    public abstract E findEntity(PK pk);

    public abstract E addEntity(E entity);

    public abstract E updateEntity(E entity);

    public abstract List<E> findList(E entity) throws ApplicationException;

    public abstract Pagination<E> findPage(Pagination<E> page, E entity) throws ApplicationException;

    public abstract void delEntity(PK pk);

    public abstract void delList(List<PK> pks);
}