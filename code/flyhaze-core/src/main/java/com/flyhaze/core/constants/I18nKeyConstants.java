/**
 * flyhaze-core
 * I18nKeyConstants.java
 */
package com.flyhaze.core.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * I18nKeyConstants
 *
 * 国际化资源key常量
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-09-24 16:17:28
 * @since jdk1.8
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class I18nKeyConstants {

    /**
     * 资源名称
     */
    public static final String BASENAME = "i18n.messages";

    /**
     * 系统错误，请联系管理！
     */
    public static final String KEY_ERROR = "error.info";

    /**
     * 登录：用户{}，{}
     */
    public static final String KEY_LOGIN = "message.login";

    /**
     * 登录成功！
     */
    public static final String KEY_LOGIN_SUCCESS = "message.login.success";

    /**
     * 验证码正确!
     */
    public static final String KEY_LOGIN_KAPTCHA = "message.login.kaptcha";

    /**
     * 用户不存在！
     */
    public static final String KEY_LOGIN_USERNAME = "message.login.username";

    /**
     * seed生成成功！
     */
    public static final String KEY_LOGIN_SEED = "message.login.seed";

    /**
     * 密码错误
     */
    public static final String KEY_LOGIN_PWD = "message.login.pwd";

    /**
     * 已登录！
     */
    public static final String KEY_LOGINED = "message.logined";

    /**
     * 未登录！
     */
    public static final String KEY_UNLOGIN = "message.unlogin";

    /**
     * 退出登录：用户{}，{}
     */
    public static final String KEY_LOGOUT = "message.logout";

    /**
     * 登出成功！
     */
    public static final String KEY_LOGOUT_SUCCESS = "message.logout.success";

    /**
     * 用户未登录！
     */
    public static final String KEY_LOGOUT_USERNAME = "message.logout.username";

    /**
     * 查询成功！
     */
    public static final String KEY_LOAD_SUCCESS = "message.load.success";

    /**
     * 保存成功！
     */
    public static final String KEY_SAVE_SUCCESS = "message.save.success";

    /**
     * 编号重复！
     */
    public static final String KEY_SAVE_CODE = "message.save.code";

    /**
     * 全路径编号重复！
     */
    public static final String KEY_SAVE_FULLCODE = "message.save.fullcode";

    /**
     * 删除成功！
     */
    public static final String KEY_DEL_SUCCESS = "message.del.success";

    /**
     * 要删除的数据不存在！
     */
    public static final String KEY_DEL_DATA = "message.del.data";

    /**
     * 内置数据不可删除！
     */
    public static final String KEY_DEL_BUILTIN = "message.del.builtin";

}