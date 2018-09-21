/**
 * framework-hibernate
 * WhereClause.java
 */
package com.flyhaze.framework.query;

import java.util.Map;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * WhereClause
 *
 * 查询条件对象
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-09-01 11:50:46
 * @since jdk1.8
 */
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@ToString
public class WhereClause {

    /** where子句 */
    private String whereClause;
    /** 查询条件 */
    private Map<String, Object> param;
}