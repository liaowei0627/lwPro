/**
 * hibernate-framework
 * SpringBaseEntity.java
 */
package com.liaowei.framework.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;

import com.liaowei.framework.core.entity.IBaseEntity;

/**
 * SpringBaseEntity
 * 
 * hibernate实体类的超类
 *
 * @author liaowei
 * @date 创建时间：2018年4月1日 上午9:47:47 
 * @since jdk1.8
 */
@SuppressWarnings("serial")
@MappedSuperclass
public abstract class SpringBaseEntity implements IBaseEntity {

    // 主键
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

    protected SpringBaseEntity() {
    }

    protected SpringBaseEntity(String id, Boolean valid, String creator, LocalDateTime createTime, String reviser, LocalDateTime modifyTime) {
        this.id = id;
        this.valid = valid;
        this.creator = creator;
        this.createTime = createTime;
        this.reviser = reviser;
        this.modifyTime = modifyTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public String getReviser() {
        return reviser;
    }

    public void setReviser(String reviser) {
        this.reviser = reviser;
    }

    public LocalDateTime getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(LocalDateTime modifyTime) {
        this.modifyTime = modifyTime;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        SpringBaseEntity other = (SpringBaseEntity) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}