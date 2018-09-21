/**
 * flyhaze-framework
 * NoValueComparisonOperator.java
 */
package com.flyhaze.framework.core.query.operator;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * NoValueComparisonOperator
 *
 * 没有值的运算符枚举<br>
 * is null<br>
 * is not null<br>
 * is empty<br>
 * is not empty
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-09-01 22:22:38
 * @since jdk1.8
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum NoValueComparisonOperator {

    /**
     * is null
     */
    IS_NULL("is null"),
    /**
     * is not null
     */
    IS_NOT_NULL("is not null"),
    /**
     * is empty
     */
    IS_EMPTY("is empty"),
    /**
     * is not empty
     */
    IS_NOT_EMPTY("is not empty");

    private String text;
}