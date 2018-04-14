/**
 * service-framework
 * BaseVo.java
 */
package com.liaowei.framework.vo;

import java.time.LocalDateTime;

import com.liaowei.framework.core.vo.IBasisVo;
import com.liaowei.framework.entity.BaseEntity;

/**
 * BaseVo
 *
 * Spring服务层向控制层传递数据的封装类基类
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-08 21:40:39
 * @see com.liaowei.framework.core.vo.IBaseVo<T>
 * @since jdk1.8
 */
@SuppressWarnings("serial")
public abstract class BaseVo<E extends BaseEntity> implements IBasisVo<E> {

    // 主键
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

    protected BaseVo() {
    }

    protected BaseVo(String id, Boolean valid, String creator, LocalDateTime createTime, String reviser, LocalDateTime modifyTime) {
        this.id = id;
        this.valid = valid;
        this.creator = creator;
        this.createTime = createTime;
        this.reviser = reviser;
        this.modifyTime = modifyTime;
    }

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

    @Override
    public abstract int hashCode();

    @Override
    public abstract boolean equals(Object obj);
}