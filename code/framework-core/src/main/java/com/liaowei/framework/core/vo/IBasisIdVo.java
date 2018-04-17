/**
 * framework-core
 * IBasisIdVo.java
 */
package com.liaowei.framework.core.vo;

import java.io.Serializable;

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
public interface IBasisIdVo extends Serializable {

    /**
     * 主键
     */
    String getId();

    /**
     * 主键
     */
    void setId(String id);
}