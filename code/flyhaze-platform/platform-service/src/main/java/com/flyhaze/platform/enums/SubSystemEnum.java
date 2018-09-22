/**
 * platform-service
 * SubSystemEnum.java
 */
package com.flyhaze.platform.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * SubSystemEnum
 *
 * 分系统枚举
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-09-15 12:55:11
 * @since jdk1.8
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@ToString
public enum SubSystemEnum {

    /**
     * 分系统
     */
    SYSTEM("系统管理");

    private String text;
}