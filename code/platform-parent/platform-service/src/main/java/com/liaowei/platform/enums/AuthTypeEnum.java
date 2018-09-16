/**
 * platform-service
 * AuthTypeEnum.java
 */
package com.liaowei.platform.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * AuthTypeEnum
 *
 * 权限类型
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-17 04:40:49
 * @since jdk1.8
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@ToString
public enum AuthTypeEnum {

    /**
     * 访问权限
     */
    ACCESS("访问权限"),
    /**
     * 数据权限
     */
    DATA("数据权限");

    private String text;
}
