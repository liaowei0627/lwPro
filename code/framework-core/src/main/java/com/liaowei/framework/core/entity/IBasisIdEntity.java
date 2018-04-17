/**
 * framework-core
 * IBasisEntity.java
 */
package com.liaowei.framework.core.entity;

import java.io.Serializable;

/**
 * IBasisEntity
 * 
 * 实体类顶层主键接口
 * Entity用于数据层与服务层传递数据
 * 不论用何种实体层框架，其实体类均需继承此接口
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-08 21:25:25
 * @see java.io.Serializable
 * @since jdk1.8
 */
public interface IBasisIdEntity extends Serializable {

    /**
     * 主键
     */
    String getId();

    /**
     * 主键
     */
    void setId(String id);
}