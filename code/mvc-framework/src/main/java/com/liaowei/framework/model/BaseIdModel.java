/**
 * mvc-framework
 * BaseModel.java
 */
package com.liaowei.framework.model;

import com.liaowei.framework.core.model.IBasisIdModel;
import com.liaowei.framework.entity.BaseIdEntity;
import com.liaowei.framework.vo.BaseIdVo;

import lombok.AccessLevel;
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
@Getter
@Setter
@ToString
public abstract class BaseIdModel<E extends BaseIdEntity<E>, V extends BaseIdVo<E, V>, M extends BaseIdModel<E, V, M>> implements IBasisIdModel<E, V, M> {

    /**
     * 主键
     */
    protected String id;
}