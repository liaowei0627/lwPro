/**
 * flyhaze-framework
 * CollectionValueComparisonOperator.java
 */
package com.flyhaze.framework.core.query.operator;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * CollectionOperator
 *
 * 集合运算符枚举<br>
 * in
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-08-27 23:50:02
 * @since jdk1.8
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum CollectionValueComparisonOperator {

    /**
     * in
     */
    IN("in");

    private String text;
}