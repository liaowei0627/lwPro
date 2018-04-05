/**
 * framework-core
 * BaseServiceImpl.java
 */
package com.liaowei.framework.core.service.impl;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.liaowei.framework.core.dao.IBaseDao;
import com.liaowei.framework.core.entity.IBaseEntity;
import com.liaowei.framework.core.exception.ApplicationException;
import com.liaowei.framework.core.service.IBaseService;
import com.liaowei.framework.core.vo.IBaseVo;
import com.liaowei.framework.page.Pagination;

/**
 * BaseServiceImpl
 *
 * Service层超类实现
 *
 * @author liaowei
 * @date 创建时间：2018年4月1日 上午9:43:10 
 * @see com.liaowei.framework.core.service.IBaseService<T, PK>
 * @since jdk1.8
 */
public abstract class BaseServiceImpl<V extends IBaseVo<E>, E extends IBaseEntity, PK extends Serializable> implements IBaseService<V, E, PK> {

    protected final Logger LOGGER = LoggerFactory.getLogger(getClassName());

    protected abstract String getClassName();

    protected abstract IBaseDao<E, PK> getDao();

    protected abstract V voCopy(E vo);

    public abstract V findEntity(PK pk) throws ApplicationException;

    public abstract V addEntity(V vo) throws ApplicationException;

    public abstract V updateEntity(V vo) throws ApplicationException;

    public abstract List<V> findList(V vo) throws ApplicationException;

    public abstract Pagination<V> findPage(Pagination<V> page, V vo) throws ApplicationException;

    public abstract void delEntity(PK pk) throws ApplicationException;

    public abstract void delList(List<PK> pks) throws ApplicationException;
}