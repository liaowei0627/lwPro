/**
 * framework-core
 * BasisController.java
 */
package com.liaowei.framework.core.controller;

import com.liaowei.framework.core.entity.IBasisIdEntity;
import com.liaowei.framework.core.model.IBasisIdModel;
import com.liaowei.framework.core.vo.IBasisIdVo;

/**
 * BasisController
 * 
 * 控制层基类<br>
 * 无论何种控制层框架<br>
 * 例如Spring MVC的Controller或者Struts的Action<br>
 * 都必须继承此类
 * 
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-08 21:25:25
 * @since jdk1.8
 */
public abstract class BasisController<E extends IBasisIdEntity<E>, V extends IBasisIdVo<E, V>, M extends IBasisIdModel<E, V, M>> {

    /**
     * 将Vo对象转换成Model对象
     */
    protected abstract M voToModel(V v);
}