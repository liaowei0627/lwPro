/**
 * framework-core
 * IBaseVo.java
 */
package com.liaowei.framework.core.vo;

import java.io.Serializable;

import com.liaowei.framework.core.entity.IBaseEntity;

/**
 * IBaseVo
 *
 * Vo用于服务层向控制层传递数据
 *
 * @author liaowei
 * @date 创建时间：2018年4月5日 上午11:56:25 
 * @since jdk1.8
 */
public interface IBaseVo<E extends IBaseEntity> extends Serializable {

    abstract void copyEntity(E entity);

    abstract E toEntity();
}