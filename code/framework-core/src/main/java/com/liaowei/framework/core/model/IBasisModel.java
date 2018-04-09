/**
 * framework-core
 * IBasisModel.java
 */
package com.liaowei.framework.core.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.liaowei.framework.core.entity.IBasisEntity;
import com.liaowei.framework.core.vo.IBasisVo;

/**
 * IBasisModel
 *
 * Model用于控制层向前端传递数据
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-08 21:25:25
 * @see java.io.Serializable
 * @since jdk1.8
 */
public interface IBasisModel<V extends IBasisVo<E>, E extends IBasisEntity> extends Serializable {

    /**
     * 从Vo对象中复制属性值
     * 
     * @param vo
     */
    abstract void copyVo(V vo);

    /**
     * 生成Vo对象并复制属性值
     * 
     * @return
     */
    abstract V toVo();

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