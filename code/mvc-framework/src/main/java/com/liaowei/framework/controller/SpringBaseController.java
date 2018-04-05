/**
 * mvc-framework
 * BaseController.java
 */
package com.liaowei.framework.controller;

import com.liaowei.framework.core.controller.BaseController;
import com.liaowei.framework.entity.SpringBaseEntity;
import com.liaowei.framework.model.SpringBaseModel;
import com.liaowei.framework.vo.SpringBaseVo;

/**
 * BaseController
 *
 * spring mvc controller基类
 * 处理异常，公共方法
 *
 * @author liaowei
 * @date 创建时间：2018年4月3日 下午10:42:34 
 * @since jdk1.8
 */
public abstract class SpringBaseController<M extends SpringBaseModel<V, E>, V extends SpringBaseVo<E>, E extends SpringBaseEntity> extends BaseController<M, V, E> {


    protected abstract String getClassName();

    protected abstract M modelCopy(V v);
}