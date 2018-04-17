/**
 * mvc-framework
 * BaseModel.java
 */
package com.liaowei.framework.model;

import com.liaowei.framework.core.model.IBasisIdModel;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * BaseModel
 *
 * Spring MVC 控制层向前端页面传递ID数据用封装类的基类
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-08 21:36:26
 * @see com.liaowei.framework.core.model.IBasisModel<V, E>
 * @since jdk1.8
 */
@SuppressWarnings("serial")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@ToString
public abstract class BaseIdModel implements IBasisIdModel {

    /**
     * 主键
     */
    protected String id;
}