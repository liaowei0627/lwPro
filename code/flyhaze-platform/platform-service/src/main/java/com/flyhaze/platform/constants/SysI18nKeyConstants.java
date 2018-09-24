/**
 * platform-service
 * SysI18nKeyConstants.java
 */
package com.flyhaze.platform.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * SysI18nKeyConstants
 *
 * 国际化资源key常量
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-09-24 16:17:28
 * @since jdk1.8
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SysI18nKeyConstants {

    /**
     * 资源名称
     */
    public static final String BASENAME = "i18n.sysmessages";

    /**
     * 取得类型列表成功！
     */
    public static final String KEY_CATEGORY = "message.category";

    /**
     * 权限不存在!
     */
    public static final String KEY_LOAD_AUTH = "message.load.auth";

    /**
     * 字典不存在!
     */
    public static final String KEY_LOAD_DICT = "message.load.dict";

    /**
     * 菜单不存在!
     */
    public static final String KEY_LOAD_MENU = "message.load.menu";

    /**
     * 站点不存在!
     */
    public static final String KEY_LOAD_SITE = "message.load.site";

    /**
     * 用户名重复！
     */
    public static final String KEY_SAVE_USERNAME = "message.save.username";

    /**
     * 取得字典树成功！
     */
    public static final String KEY_TREE_DICT = "message.tree.dict";

    /**
     * 取得菜单树成功！
     */
    public static final String KEY_TREE_MENU = "message.tree.menu";
}