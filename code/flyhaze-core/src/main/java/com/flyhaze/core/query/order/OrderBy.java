/**
 * flyhaze-core
 * OrderBy.java
 */
package com.flyhaze.core.query.order;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * OrderBy
 *
 * 排序对象封装类
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-09-22 13:05:45
 * @since jdk1.8
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@ToString
public class OrderBy {

    /** 字段名 */
    private String fieldName;

    /** 排序枚举 */
    private OrderEnum order;
}