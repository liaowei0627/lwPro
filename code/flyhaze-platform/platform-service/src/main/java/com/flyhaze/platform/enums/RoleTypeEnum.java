/**
 * platform-service
 * RoleTypeEnum.java
 */
package com.flyhaze.platform.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * RoleTypeEnum
 *
 * 角色类型
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-17 04:40:49
 * @since jdk1.8
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@ToString
public enum RoleTypeEnum {

    /**
     * 管理员
     */
    ADMIN("管理员"),
    /**
     * 用户
     */
    USER("用户");

    private String text;
}
