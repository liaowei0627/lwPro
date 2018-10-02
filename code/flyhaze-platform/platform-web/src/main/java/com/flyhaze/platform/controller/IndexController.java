/**
 * study-web
 * IndexController.java
 */
package com.flyhaze.platform.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.flyhaze.SessionUser;
import com.flyhaze.core.constants.I18nKeyConstants;
import com.flyhaze.core.exception.ApplicationException;
import com.flyhaze.framework.mvc.controller.BaseController;
import com.flyhaze.framework.mvc.response.ResponseData;
import com.flyhaze.platform.entity.SysMenu;
import com.flyhaze.platform.service.ILoginService;
import com.flyhaze.platform.vo.MenuVo;
import com.google.common.base.Strings;

import lombok.extern.slf4j.Slf4j;

/**
 * IndexController
 *
 * 主页及登录登出
 *
 * @author liaowei
 * @date 创建时间：2018年4月6日 下午10:18:45
 * @see com.flyhaze.framework.mvc.controller.BaseController
 * @since jdk1.8
 */
@SuppressWarnings("rawtypes")
@Controller
@RequestMapping()
@Slf4j
public class IndexController extends BaseController {

    @Resource(name = "loginService")
    private ILoginService loginService;

    /**
     * 打开主页
     * 
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(path = {"/index.htm"}, method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping(path = {"/checkLogged"}, method = RequestMethod.GET)
    @ResponseBody
    public ResponseData<SessionUser> checkLogged() {
        ResponseData<SessionUser> responseData;
        @SuppressWarnings("unchecked")
        SessionUser<SysMenu, MenuVo> sessionUser = getCurUser();
        if (null != sessionUser) {
            responseData = ResponseData.<SessionUser>success(getMessage(I18nKeyConstants.BASENAME, I18nKeyConstants.KEY_LOGINED),
                    sessionUser);
        } else {
            responseData = ResponseData.<SessionUser>failure(getMessage(I18nKeyConstants.BASENAME, I18nKeyConstants.KEY_UNLOGIN));
        }
        return responseData;
    }

    /**
     * 获取密码加密种子字符串
     * 
     * @return
     */
    @RequestMapping(path = {"/seed"}, method = RequestMethod.GET)
    @ResponseBody
    public ResponseData<String> seed() {
        String seed = setPwdSeed();
        return ResponseData.<String>success(getMessage(I18nKeyConstants.BASENAME, I18nKeyConstants.KEY_LOGIN_SEED), seed);
    }

    /**
     * 登录<br>
     * 
     * 客户端密码的加密方式： 明文MD5编码转16进制字符串<br>
     * String pwdCiphertext = CryptoUtils.toMD5(pwd);<br>
     * 加上seed再MD5编码转16进制字符串<br>
     * pwdCiphertext = CryptoUtils.toMD5(pwdCiphertext + seed);<br>
     * MD5字符串BASE64编码转16进制字符串<br>
     * pwdCiphertext = CryptoUtils.base64Encoder(pwdCiphertext);<br>
     * 服务端密码验证方式：<br>
     * 用户数据中的密码即为明文MD5值，加上seed再MD5编码， 表单中的密码字段，BASE64解码后与之比较 一致就是密码正确
     * 
     * @param userName
     * @param password
     * @param request
     * @return
     */
    @RequestMapping(path = {"/login"}, method = RequestMethod.POST)
    @ResponseBody
    public ResponseData<SessionUser> doLogin(@RequestParam(name = "userName", required = true) String userName,
            @RequestParam(name = "password", required = true) String password) {
        ResponseData<SessionUser> responseData;
        try {
            // 比较密码
            String pwdSeed = getPwdSeed();
            removePwdSeed();

            // 用户登录对象
            SessionUser<SysMenu, MenuVo> sessionUser;
            sessionUser = loginService.login(userName, password, pwdSeed);
            String msg;
            if (null == sessionUser) {
                msg = getMessage(I18nKeyConstants.BASENAME, I18nKeyConstants.KEY_LOGIN_USERNAME);
                responseData = ResponseData.<SessionUser>otherFailure(2, msg);
            } else if (Strings.isNullOrEmpty(sessionUser.getId())) {
                msg = getMessage(I18nKeyConstants.BASENAME, sessionUser.getMsg());
                responseData = ResponseData.<SessionUser>otherFailure(3, msg);
            } else {
                setCurUser(sessionUser);
                msg = getMessage(I18nKeyConstants.BASENAME, I18nKeyConstants.KEY_LOGIN_SUCCESS);
                responseData = ResponseData.<SessionUser>success(msg, sessionUser);
            }
            log.info(getMessage(I18nKeyConstants.BASENAME, I18nKeyConstants.KEY_LOGIN), userName, msg);
        } catch (ApplicationException e) {
            log.error(e.getMessage(), e);
            responseData = ResponseData.<SessionUser>failure(getMessage(I18nKeyConstants.BASENAME, I18nKeyConstants.KEY_ERROR));
        }
        return responseData;
    }

    /**
     * 登出
     * 
     * @return
     */
    @RequestMapping(path = {"/logout"}, method = RequestMethod.GET)
    @ResponseBody
    public ResponseData<String> logout() {
        @SuppressWarnings("unchecked")
        SessionUser<SysMenu, MenuVo> sessionUser = getCurUser();
        String userName;
        String msg;
        if (null != sessionUser) {
            userName = sessionUser.getUserName();
            msg = getMessage(I18nKeyConstants.BASENAME, I18nKeyConstants.KEY_LOGOUT_SUCCESS);
            removeCurUser();
        } else {
            msg = getMessage(I18nKeyConstants.BASENAME, I18nKeyConstants.KEY_LOGOUT_USERNAME);
            userName = "";
        }
        log.info(getMessage(I18nKeyConstants.BASENAME, I18nKeyConstants.KEY_LOGOUT), userName, msg);
        return ResponseData.<String>success(msg);
    }
}