/**
 * mvc-framework
 * BaseModel.java
 */
package com.liaowei.framework.model;

import java.time.LocalDateTime;

import com.liaowei.framework.core.model.IBasisModel;
import com.liaowei.framework.entity.BaseEntity;
import com.liaowei.framework.vo.BaseVo;

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
public abstract class BaseModel<V extends BaseVo<E>, E extends BaseEntity> implements IBasisModel<V, E> {

    /**
     * 主键
     */
    protected String id;

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

    protected BaseModel() {
    }

    protected BaseModel(String id, Boolean valid, String creator, LocalDateTime createTime, String reviser, LocalDateTime modifyTime) {
        this.id = id;
        this.valid = valid;
        this.creator = creator;
        this.createTime = createTime;
        this.reviser = reviser;
        this.modifyTime = modifyTime;
    }

    public abstract V toVo();

    /**
     * 主键
     */
    @Override
    public String getId() {
        return id;
    }

    /**
     * 主键
     */
    @Override
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 是否有效
     */
    @Override
    public Boolean getValid() {
        return valid;
    }

    /**
     * 是否有效
     */
    @Override
    public void setValid(Boolean valid) {
        this.valid = valid;
    }

    /**
     * 创建者
     */
    @Override
    public String getCreator() {
        return creator;
    }

    /**
     * 创建者
     */
    @Override
    public void setCreator(String creator) {
        this.creator = creator;
    }

    /**
     * 创建时间
     */
    @Override
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     */
    @Override
    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    /**
     * 修改者
     */
    @Override
    public String getReviser() {
        return reviser;
    }

    /**
     * 修改者
     */
    @Override
    public void setReviser(String reviser) {
        this.reviser = reviser;
    }

    /**
     * 修改者
     */
    @Override
    public LocalDateTime getModifyTime() {
        return modifyTime;
    }

    /**
     * 修改者
     */
    @Override
    public void setModifyTime(LocalDateTime modifyTime) {
        this.modifyTime = modifyTime;
    }
}