/**
 * platform-service
 * MenuTypeEnum.java
 */
package com.liaowei.platform.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * MenuTypeEnum
 *
 * 菜单类型枚举
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-17 04:34:50
 * @since jdk1.8
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@ToString
public enum MenuTypeEnum {

    /**
     * 导航栏
     */
    NAVIGATION("导航栏"),
    /**
     * 菜单组
     */
    MENU_GROUP("菜单组"),
    /**
     * 菜单
     */
    MENU("菜单"),
    /**
     * 链接
     */
    LINK("链接"),
    /**
     * 按钮
     */
    BUTTON("按钮");

    private String text;
}