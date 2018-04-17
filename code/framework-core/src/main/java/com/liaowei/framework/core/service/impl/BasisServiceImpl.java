/**
 * framework-core
 * BasisServiceImpl.java
 */
package com.liaowei.framework.core.service.impl;

import java.io.Serializable;

import com.liaowei.framework.core.dao.IBasisDao;
import com.liaowei.framework.core.entity.IBasisEntity;
import com.liaowei.framework.core.service.IBasisService;
import com.liaowei.framework.core.vo.IBasisVo;

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
public abstract class BasisServiceImpl<V extends IBasisVo, E extends IBasisEntity, PK extends Serializable> implements IBasisService<V, E, PK> {

    /**
     * 从子类注入Dao
     */
    protected abstract IBasisDao<E, PK> getDao();

    /**
     * 将Entity对象转换成Vo对象
     */
    protected abstract V entityToVo(E entity);

    /**
     * 将Vo对象转换成Entity对象
     */
    protected abstract E voToEntity(V vo);
}