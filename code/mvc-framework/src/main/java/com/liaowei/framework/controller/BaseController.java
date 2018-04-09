/**
 * mvc-framework
 * BaseController.java
 */
package com.liaowei.framework.controller;

import java.io.Serializable;

import com.liaowei.framework.core.controller.BasisController;
import com.liaowei.framework.entity.BaseEntity;
import com.liaowei.framework.model.BaseModel;
import com.liaowei.framework.vo.BaseVo;

/**
 * BaseController
 *
 * spring mvc controller基类
 * 处理异常，公共方法
 *
 * @author liaowei
 * @date 创建时间：2018年4月3日 下午10:42:34 
 * @see com.liaowei.framework.core.controller.BasisController<M, V, E>
 * @since jdk1.8
 */
public abstract class BaseController<M extends BaseModel<V, E>, V extends BaseVo<E>, E extends BaseEntity, PK extends Serializable> extends BasisController<M, V, E, PK> {


    protected abstract String getClassName();

    protected abstract M modelCopy(V v);
}