/**
 * framework-core
 * IBaseController.java
 */
package com.liaowei.framework.core.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.liaowei.framework.core.entity.IBaseEntity;
import com.liaowei.framework.core.model.IBaseModel;
import com.liaowei.framework.core.service.IBaseService;
import com.liaowei.framework.core.vo.IBaseVo;

/**
 * IBaseController
 *
 * 控制层基类
 * 无论何种控制层框架
 * 例如Spring MVC的Controller或者Struts的Action
 * 都必须继承此类
 *
 * @author liaowei
 * @date 创建时间：2018年4月4日 下午10:55:07 
 * @since jdk1.8
 */
public abstract class BaseController<M extends IBaseModel<V, E>, V extends IBaseVo<E>, E extends IBaseEntity> {

    protected final Logger LOGGER = LoggerFactory.getLogger(getClassName());

    protected abstract String getClassName();

    protected abstract M modelCopy(V v);

    protected abstract IBaseService<V, E, String> getService();
}