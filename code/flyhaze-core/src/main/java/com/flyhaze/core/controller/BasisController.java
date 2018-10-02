/**
 * flyhaze-core
 * BasisController.java
 */
package com.flyhaze.core.controller;

import com.flyhaze.core.entity.IBasisIdEntity;
import com.flyhaze.core.vo.IBasisIdVo;

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
public abstract class BasisController<E extends IBasisIdEntity<E>, V extends IBasisIdVo<E, V>> {
}