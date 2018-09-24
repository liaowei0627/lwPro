/**
 * flyhaze-framework
 * BasisServiceImpl.java
 */
package com.flyhaze.framework.core.service.impl;

import java.io.Serializable;

import com.flyhaze.framework.core.entity.IBasisIdEntity;
import com.flyhaze.framework.core.exception.ApplicationException;
import com.flyhaze.framework.core.service.IBasisService;
import com.flyhaze.framework.core.vo.IBasisIdVo;

/**
 * BasisServiceImpl
 *
 * Service层超类实现
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-08 21:25:25
 * @see com.flyhaze.framework.core.service.IBasisService<E, V, PK>
 * @since jdk1.8
 */
public abstract class BasisServiceImpl<E extends IBasisIdEntity<E>, V extends IBasisIdVo<E, V>, PK extends Serializable>
        implements IBasisService<E, V, PK> {

    protected String msg;

    /**
     * 将Entity对象转换成Vo对象
     * 
     * @param entity
     * @return
     */
    protected abstract V entityToVo(E entity);

    /**
     * addVo和updateVo方法执行前的数据有效性验证
     * 
     * @param vo
     * @return
     * @throws ApplicationException
     */
    protected abstract boolean validSave(V vo) throws ApplicationException;

    /**
     * 
     * 
     * @param ids
     * @return
     * @throws ApplicationException
     */
    protected abstract boolean validDel(String[] ids) throws ApplicationException;
}