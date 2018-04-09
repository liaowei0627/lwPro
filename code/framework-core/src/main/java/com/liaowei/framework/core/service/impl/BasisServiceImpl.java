/**
 * framework-core
 * BasisServiceImpl.java
 */
package com.liaowei.framework.core.service.impl;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.liaowei.framework.core.dao.IBasisDao;
import com.liaowei.framework.core.entity.IBasisEntity;
import com.liaowei.framework.core.exception.ApplicationException;
import com.liaowei.framework.core.service.IBasisService;
import com.liaowei.framework.core.vo.IBasisVo;
import com.liaowei.framework.page.Pagination;

/**
 * BasisServiceImpl
 *
 * Service层超类实现
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-08 21:25:25
 * @see com.liaowei.framework.core.service.IBasisService<T, PK>
 * @since jdk1.8
 */
public abstract class BasisServiceImpl<V extends IBasisVo<E>, E extends IBasisEntity, PK extends Serializable> implements IBasisService<V, E, PK> {

    protected final Logger LOGGER = LoggerFactory.getLogger(getClassName());

    /**
     * 从子类注入类名
     * @return
     */
    protected abstract String getClassName();

    /**
     * 从子类注入Dao
     * @return
     */
    protected abstract IBasisDao<E, PK> getDao();

    /**
     * 将Entity对象转换成Vo对象
     * 
     * @param entity
     * @return
     */
    protected abstract V voCopy(E entity);

    public abstract V findVo(PK pk) throws ApplicationException;

    public abstract V addVo(V vo) throws ApplicationException;

    public abstract V updateVo(V vo) throws ApplicationException;

    public abstract List<V> findList(V vo) throws ApplicationException;

    public abstract Pagination<V> findPage(Pagination<V> page, V vo) throws ApplicationException;

    public abstract void delOne(PK pk) throws ApplicationException;

    public abstract void delList(List<PK> pks) throws ApplicationException;
}