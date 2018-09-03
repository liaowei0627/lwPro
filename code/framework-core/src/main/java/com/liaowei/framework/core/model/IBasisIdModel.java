/**
 * framework-core
 * IBasisIdModel.java
 */
package com.liaowei.framework.core.model;

import java.io.Serializable;

import com.liaowei.framework.core.entity.IBasisIdEntity;
import com.liaowei.framework.core.vo.IBasisIdVo;

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
public interface IBasisIdModel<E extends IBasisIdEntity<E>, V extends IBasisIdVo<E, V>, M extends IBasisIdModel<E, V, M>>
        extends Serializable {

    /**
     * 将Vo属性复制到当前Entity对象
     * 
     * @param vo
     */
    void copyForVo(V v);

    /**
     * 将Model属性复制到新的Vo对象
     */
    V copyToVo();

    /**
     * 主键
     */
    String getId();

    /**
     * 主键
     */
    void setId(String id);
}