/**
 * framework-core
 * IBasisEntity.java
 */
package com.liaowei.framework.core.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * IBasisEntity
 * 
 * 实体类顶层接口
 * Entity用于数据层与服务层传递数据
 * 不论用何种实体层框架，其实体类均需继承此接口
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-08 21:25:25
 * @see java.io.Serializable
 * @since jdk1.8
 */
public interface IBasisEntity extends Serializable {

    /**
     * 主键
     */
    String getId();

    /**
     * 主键
     */
    void setId(String id);

    /**
     * 是否有效
     */
    Boolean getValid();

    /**
     * 是否有效
     */
    void setValid(Boolean valid);

    /**
     * 创建者
     */
    String getCreator();

    /**
     * 创建者
     */
    void setCreator(String creator);

    /**
     * 创建时间
     */
    LocalDateTime getCreateTime();

    /**
     * 创建时间
     */
    void setCreateTime(LocalDateTime createTime);

    /**
     * 修改者
     */
    String getReviser();

    /**
     * 修改者
     */
    void setReviser(String reviser);

    /**
     * 修改时间
     */
    LocalDateTime getModifyTime();

    /**
     * 修改时间
     */
    void setModifyTime(LocalDateTime modifyTime);

    int hashCode();

    boolean equals(Object obj);
}