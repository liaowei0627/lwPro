/**
 * framework-core
 * IBasisIdModel.java
 */
package com.liaowei.framework.core.model;

import java.io.Serializable;

/**
 * IBasisIdModel
 *
 * Model类顶层ID属性接口
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-21 23:56:10
 * @see java.io.Serializable
 * @since jdk1.8
 */
public interface IBasisIdModel extends Serializable {

    /**
     * 主键
     */
    String getId();

    /**
     * 主键
     */
    void setId(String id);
}