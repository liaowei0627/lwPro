/**
 * framework-core
 * OneValueComparisonOperator.java
 */
package com.liaowei.framework.query.operator;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * OneValueComparisonOperator
 *
 * 一个值的运算符枚举
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-09-01 22:22:38
 * @since jdk1.8
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum OneValueComparisonOperator {

    /**
     * =
     */
    EQ("="),
    /**
     * >
     */
    GT(">"),
    /**
     * >=
     */
    EG(">="),
    /**
     * <
     */
    LT("<"),
    /**
     * <=
     */
    EL("<="),
    /**
     * <>
     */
    UE("<>"),
    /**
     * like
     */
    LIKE("like"),
    /**
     * not like
     */
    NOT_LIKE("not like"),
    /**
     * member of
     */
    MEMBER_OF("member of"),
    /**
     * not member of
     */
    NOT_MEMBER_OF("not member of");

    private String text;
}