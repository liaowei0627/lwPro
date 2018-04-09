/**
 * hibernate-framework
 * BaseEntity.java
 */
package com.liaowei.framework.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;

import com.liaowei.framework.core.entity.IBasisEntity;

/**
 * BaseEntity
 * 
 * hibernate实体类的超类
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-08 21:33:14
 * @see com.liaowei.framework.core.entity.IBasisEntity
 * @since jdk1.8
 */
@SuppressWarnings("serial")
@MappedSuperclass
public abstract class BaseEntity implements IBasisEntity {

    /**
     * 主键
     */
    @Id
    @GenericGenerator(name = "uuid", strategy = "com.liaowei.framework.generator.IdGenerator")
    @GeneratedValue(generator = "uuid")
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
    @Column(insertable = false, updatable = false)
    protected LocalDateTime createTime;

    /**
     * 修改者
     */
    protected String reviser;

    /**
     * 修改时间
     */
    @Column(insertable = false, updatable = false)
    protected LocalDateTime modifyTime;

    protected BaseEntity() {
    }

    protected BaseEntity(String id, Boolean valid, String creator, LocalDateTime createTime, String reviser, LocalDateTime modifyTime) {
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