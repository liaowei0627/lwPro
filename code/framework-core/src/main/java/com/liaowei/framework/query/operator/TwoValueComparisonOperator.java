/**
 * framework-core
 * TwoValueComparisonOperator.java
 */
package com.liaowei.framework.query.operator;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * TwoValueComparisonOperator
 *
 * 两个值的运算符枚举<br>
 * between
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-09-01 22:22:38
 * @since jdk1.8
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum TwoValueComparisonOperator {

    /**
     * between
     */
    BETWEEN("between");

    private String text;
}