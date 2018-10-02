/**
 * flyhaze-framework
 * BaseEntity.java
 */
package com.flyhaze.framework.hibernate.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import com.flyhaze.core.entity.IBasisEntity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * BaseEntity
 * 
 * hibernate实体类的通用字段超类
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-08 21:33:14
 * @see com.flyhaze.core.entity.IBasisEntity<E>
 * @see com.flyhaze.framework.hibernate.entity.BaseIdEntity<E>
 * @since jdk1.8
 */
@SuppressWarnings("serial")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@ToString(callSuper = true)
@MappedSuperclass
public abstract class BaseEntity<E extends BaseEntity<E>> extends BaseIdEntity<E> implements IBasisEntity<E> {

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

    protected BaseEntity(String id, Boolean valid, String creator, LocalDateTime createTime, String reviser, LocalDateTime modifyTime) {
        super(id);
        this.valid = valid;
        this.creator = creator;
        this.createTime = createTime;
        this.reviser = reviser;
        this.modifyTime = modifyTime;
    }
}