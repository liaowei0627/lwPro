/**
 * mvc-framework
 * BaseModel.java
 */
package com.liaowei.framework.model;

import java.time.LocalDateTime;

import com.liaowei.framework.core.model.IBasisModel;
import com.liaowei.framework.entity.BaseEntity;
import com.liaowei.framework.vo.BaseVo;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * BaseModel
 *
 * Spring MVC 控制层向前端页面传递数据用封装类的基类
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
@ToString(callSuper = true)
public abstract class BaseModel<E extends BaseEntity<E>, V extends BaseVo<E, V>, M extends BaseModel<E, V, M>> extends BaseIdModel<E, V, M> implements IBasisModel<E, V, M> {

    /**
     * 是否有效
     */
    protected Boolean valid;

    /**
     * 创建者
     */
    protected String creator;

    /**
     * 创建时间
     */
    protected LocalDateTime createTime;

    /**
     * 修改者
     */
    protected String reviser;

    /**
     * 修改时间
     */
    protected LocalDateTime modifyTime;
}