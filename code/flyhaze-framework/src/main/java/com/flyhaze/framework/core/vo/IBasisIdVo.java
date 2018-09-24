/**
 * flyhaze-framework
 * IBasisIdVo.java
 */
package com.flyhaze.framework.core.vo;

import java.io.Serializable;

import com.flyhaze.framework.core.entity.IBasisIdEntity;

/**
 * IBasisIdVo
 *
 * 用于服务层向控制层传递数据的IDVO接口
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-21 00:59:13
 * @see java.io.Serializable
 * @since jdk1.8
 */
public interface IBasisIdVo<E extends IBasisIdEntity<E>, V extends IBasisIdVo<E, V>> extends Serializable {

    /**
     * 将Entity属性复制到当前Vo对象
     * 
     * @param vo
     */
    void copyForEntity(E e);

    /**
     * 将Vo属性复制到新的Entity对象
     */
    E copyToEntity();

    /**
     * 主键
     */
    String getId();

    /**
     * 主键
     */
    void setId(String id);

    /**
     * 消息
     */
    String getMsg();

    /**
     * 消息
     */
    void setMsg(String msg);
}