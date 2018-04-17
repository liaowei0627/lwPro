/**
 * mvc-framework
 * BaseModel.java
 */
package com.liaowei.framework.model;

import java.time.LocalDateTime;

import com.liaowei.framework.core.model.IBasisModel;

import lombok.Getter;
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
@Getter
@Setter
@ToString(callSuper = true)
public abstract class BaseModel extends BaseIdModel implements IBasisModel {

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

    public BaseModel() {
        super();
    }

    public BaseModel(String id, Boolean valid, String creator, LocalDateTime createTime, String reviser, LocalDateTime modifyTime) {
        super(id);
        this.valid = valid;
        this.creator = creator;
        this.createTime = createTime;
        this.reviser = reviser;
        this.modifyTime = modifyTime;
    }
}