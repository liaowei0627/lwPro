/**
 * framework-core
 * IBaseModel.java
 */
package com.liaowei.framework.core.model;

import java.io.Serializable;

import com.liaowei.framework.core.entity.IBaseEntity;
import com.liaowei.framework.core.vo.IBaseVo;

/**
 * IBaseModel
 *
 * Model用于控制层向前端传递数据
 *
 * @author liaowei
 * @date 创建时间：2018年4月5日 上午7:28:20 
 * @since jdk1.8
 */
public interface IBaseModel<V extends IBaseVo<E>, E extends IBaseEntity> extends Serializable {

    abstract void copyVo(V vo);

    abstract V toVo();
}