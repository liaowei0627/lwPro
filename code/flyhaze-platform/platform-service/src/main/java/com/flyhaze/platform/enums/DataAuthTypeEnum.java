/**
 * platform-service
 * DataAuthType.java
 */
package com.flyhaze.platform.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * DataAuthType
 *
 * 数据权限类型
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-17 06:23:03
 * @since jdk1.8
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@ToString
public enum DataAuthTypeEnum {

    /**
     * 全部（无数据权限限制）
     */
    ALL("全部"),
    /**
     * 属于我的FOR_MY（creator为当前用户）
     */
    FOR_MY("属于我的"),
    /**
     * 发送给我的TO_MY（submitTo为当前用户）
     */
    TO_MY("发送给我的"),
    /**
     * 属于我角色的FOR_MYROLE（creator为拥有当前用户拥有的角色之一的用户）
     */
    FOR_MY_ROLE("属于我角色的"),
    /**
     * 发送给我角色的TO_MYROLE（submitTo为拥有当前用户拥有的角色之一的用户）
     */
    TO_MY_ROLE("发送给我角色的");

    private String text;
}