/**
 * framework-core
 * BasisController.java
 */
package com.liaowei.framework.core.controller;

import com.liaowei.framework.core.model.IBasisModel;
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
public abstract class BasisController<M extends IBasisModel, V extends IBasisVo> {

    /**
     * 将Vo对象转换成Model对象
     */
    protected abstract M voToModel(V v);

    /**
     * 将Model对象转换成Vo对象
     */
    protected abstract V modelToVo(M m);
}