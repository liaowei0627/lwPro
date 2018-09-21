/**
 * framework-core
 * IBasisEntity.java
 */
package com.flyhaze.framework.core.entity;

import java.io.Serializable;

/**
 * IBasisEntity
 * 
 * 实体类顶层主键接口<br>
 * Entity用于数据层与服务层传递数据<br>
 * 不论用何种实体层框架，其实体类均需继承此接口
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-08 21:25:25
 * @see java.io.Serializable
 * @since jdk1.8
 */
public interface IBasisIdEntity<E extends IBasisIdEntity<E>> extends Serializable {

    /**
     * 主键
     */
    String getId();

    /**
     * 主键
     */
    void setId(String id);

    /**
     * 复制对象
     * 
     * 为空或null不会复制
     * 
     * @param e
     */
    void setEntity(E e);
}