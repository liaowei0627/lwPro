/**
 * framework-core
 * IBasisModel.java
 */
package com.liaowei.framework.core.model;

import java.time.LocalDateTime;

/**
 * IBasisModel
 *
 * Model类顶层通用属性接口
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-08 21:25:25
 * @see java.io.Serializable
 * @since jdk1.8
 */
public interface IBasisModel extends IBasisIdModel {

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
}