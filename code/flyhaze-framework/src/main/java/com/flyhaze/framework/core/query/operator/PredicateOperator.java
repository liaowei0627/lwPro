/**
 * flyhaze-framework
 * PredicateOperator.java
 */
package com.flyhaze.framework.core.query.operator;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * PredicateOperator
 *
 * HQL Where子句操作符枚举<br>
 * and<br>
 * or
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-08-27 23:50:02
 * @since jdk1.8
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum PredicateOperator {

    /**
     * and
     */
    AND("and"),
    /**
     * or
     */
    OR("or");

    private String text;
}