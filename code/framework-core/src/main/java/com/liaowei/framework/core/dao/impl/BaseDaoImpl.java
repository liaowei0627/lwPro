/**
 * framework-core
 * BaseDaoImpl.java
 */
package com.liaowei.framework.core.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.liaowei.framework.core.dao.IBaseDao;
import com.liaowei.framework.core.entity.IBaseEntity;
import com.liaowei.framework.core.exception.ApplicationException;
import com.liaowei.framework.page.Pagination;

/**
 * BaseDaoImpl
 *
 * Dao层超类实现
 *
 * @author liaowei
 * @date 创建时间：2018年4月1日 上午9:50:12 
 * @see com.liaowei.framework.core.dao.IBaseDao<T, PK>
 * @since jdk1.8
 */
public abstract class BaseDaoImpl<T extends IBaseEntity, PK extends Serializable> implements IBaseDao<T, PK> {

    protected final Logger LOGGER = LoggerFactory.getLogger(getClassName());

    protected abstract String getClassName();
    
    protected abstract Class<T> getEntityClass();

    public abstract T findEntity(PK pk);

    public abstract T addEntity(T entity);

    public abstract T updateEntity(T entity);

    public abstract List<T> findList(T entity) throws ApplicationException;

    public abstract Pagination<T> findPage(Pagination<T> page, T entity) throws ApplicationException;

    public abstract void delEntity(PK pk);

    public abstract void delList(List<PK> pks);
}