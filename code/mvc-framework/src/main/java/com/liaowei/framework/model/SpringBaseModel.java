/**
 * mvc-framework
 * SpringBaseModel.java
 */
package com.liaowei.framework.model;

import com.liaowei.framework.core.model.IBaseModel;
import com.liaowei.framework.entity.SpringBaseEntity;
import com.liaowei.framework.vo.SpringBaseVo;

/**
 * SpringBaseModel
 *
 * TODO
 *
 * @author liaowei
 * @date 创建时间：2018年4月5日 上午7:32:50 
 * @see TODO
 * @since jdk1.8
 */
@SuppressWarnings("serial")
public abstract class SpringBaseModel<V extends SpringBaseVo<E>, E extends SpringBaseEntity> implements IBaseModel<V, E> {

    public abstract void copyVo(V vo);

    public abstract V toVo();
}