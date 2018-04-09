/**
 * framework-core
 * BasisController.java
 */
package com.liaowei.framework.core.controller;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.liaowei.framework.core.entity.IBasisEntity;
import com.liaowei.framework.core.model.IBasisModel;
import com.liaowei.framework.core.service.IBasisService;
import com.liaowei.framework.core.vo.IBasisVo;

/**
 * BasisController
 *
 * 控制层基类
 * 无论何种控制层框架
 * 例如Spring MVC的Controller或者Struts的Action
 * 都必须继承此类
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-08 21:25:25
 * @since jdk1.8
 */
public abstract class BasisController<M extends IBasisModel<V, E>, V extends IBasisVo<E>, E extends IBasisEntity, PK extends Serializable> {

    protected final Logger LOGGER = LoggerFactory.getLogger(getClassName());

    /**
     * 从子类中注入类名
     * 
     * @return
     */
    protected abstract String getClassName();

    /**
     * 将Vo对象转换为Model对象
     * 
     * @param v
     * @return
     */
    protected abstract M modelCopy(V v);

    /**
     * 从子类中注入Servic
     * 
     * @return
     */
    protected abstract IBasisService<V, E, PK> getService();
}