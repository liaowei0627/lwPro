/**
 * flyhaze-framework
 * OrderEnum.java
 */
package com.flyhaze.framework.core.query.order;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * OrderEnum
 *
 * 排序枚举
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-09-16 23:13:10
 * @since jdk1.8
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum OrderEnum {

    /**
     * 升序
     */
    ASC("asc"),
    /**
     * 降序
     */
    DESC("desc");

    private String text;
}
