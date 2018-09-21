/**
 * framework-core
 * OneValueComparisonOperator.java
 */
package com.flyhaze.framework.query.operator;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * OneValueComparisonOperator
 *
 * 一个值的运算符枚举<br>
 * <![CDATA[=]]><br>
 * <![CDATA[>]]><br>
 * <![CDATA[>=]]><br>
 * <![CDATA[<]]><br>
 * <![CDATA[<=]]><br>
 * <![CDATA[<>]]><br>
 * like<br>
 * member of<br>
 * not member of
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
     * <![CDATA[=]]>
     */
    EQ("="),
    /**
     * <![CDATA[>]]>
     */
    GT(">"),
    /**
     * <![CDATA[>=]]>
     */
    EG(">="),
    /**
     * <![CDATA[<]]>
     */
    LT("<"),
    /**
     * <![CDATA[<=]]>
     */
    EL("<="),
    /**
     * <![CDATA[<>]]>
     */
    UE("<>"),
    /**
     * like
     */
    LIKE("like"),
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