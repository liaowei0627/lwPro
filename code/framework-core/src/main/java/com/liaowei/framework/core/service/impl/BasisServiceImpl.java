/**
 * framework-core
 * BasisServiceImpl.java
 */
package com.liaowei.framework.core.service.impl;

import java.io.Serializable;

import com.liaowei.framework.core.entity.IBasisIdEntity;
import com.liaowei.framework.core.service.IBasisService;
import com.liaowei.framework.core.vo.IBasisIdVo;

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
public abstract class BasisServiceImpl<V extends IBasisIdVo, E extends IBasisIdEntity, PK extends Serializable> implements IBasisService<V, E, PK> {

    /**
     * 将Entity对象转换成Vo对象
     */
    protected abstract V entityToVo(E entity);

    /**
     * 将Vo对象转换成Entity对象
     */
    protected abstract E voToEntity(V vo);
}