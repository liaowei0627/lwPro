/**
 * study-web
 * IndexController.java
 */
package com.flyhaze.study.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.flyhaze.SessionUser;
import com.flyhaze.core.constants.I18nKeyConstants;
import com.flyhaze.framework.mvc.controller.BaseController;
import com.flyhaze.framework.mvc.response.ResponseData;
import com.flyhaze.study.service.ILoginService;
import com.flyhaze.study.vo.UserVo;
import com.flyhaze.utils.CryptoUtils;
import com.google.common.base.Objects;

/**
 * IndexController
 *
 * 主页及登录登出
 *
 * @author liaowei
 * @date 创建时间：2018年4月6日 下午10:18:45 
 * @since jdk1.8
 */
@SuppressWarnings("rawtypes")
@Controller
@RequestMapping()
public class IndexController extends BaseController {

    protected final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);

    @Resource(name = "loginService")
    private ILoginService loginService;

	/**
	 * 打开主页
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(path = {"/index.htm"})
	public String index() {
		return "index";
	}

    /**
     * 加载主页头部
     * 
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(path = {"/header.htm"})
    public String header() {
        return "base/header";
    }

    /**
     * 加载主页左侧
     * 
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(path = {"/menus.htm"})
    public String menus() {
        return "base/menus";
    }

    /**
     * 打开登陆页
     * 
     * @return
     */
    @RequestMapping(path = {"/login.htm"})
	public String login() {
        return "login";
	}

    /**
     * 打开首页
     * 
     * @return
     */
    @RequestMapping(path = {"/home.htm"})
    public String home() {
        return "home";
    }

    /**
     * 获取密码加密种子字符串
     * 
     * @return
     */
    @RequestMapping(path = {"/seed"})
    @ResponseBody
    public ResponseData<String> seed(HttpServletRequest request) {
        String seed = setPwdSeed();
        return ResponseData.<String>success(getMessage(I18nKeyConstants.BASENAME, I18nKeyConstants.KEY_LOGIN_SEED), seed);
    }

    /**
     * 登录
     * 客户端密码的加密方式：
     * 明文MD5编码转16进制字符串
     * String pwdCiphertext = CryptoUtils.toMD5(pwd);
     * 加上seed再MD5编码转16进制字符串
     * pwdCiphertext = CryptoUtils.toMD5(pwdCiphertext + seed);
     * MD5字符串BASE64编码转16进制字符串
     * pwdCiphertext = CryptoUtils.base64Encoder(pwdCiphertext);
     * 服务端密码验证方式：
     * 用户数据中的密码同上MD5编码两次
     * 表单中的密码字段，BASE64解码后与之比较
     * 一致就是密码正确
     * 
     * @param userName
     * @param password
     * @param request
     * @return
     */
    @RequestMapping(path = {"/login"})
    @ResponseBody
    public ResponseData<SessionUser> doLogin(
            @RequestParam(name = "userName", required = true) String userName,
            @RequestParam(name = "password", required = true) String password,
            HttpServletRequest request) {
        ResponseData<SessionUser> responseData;
        try {
            String msg;
            // 查询用户对象
            UserVo user = loginService.findByUserName(userName);
            if (null == user) {
                msg = getMessage(I18nKeyConstants.BASENAME, I18nKeyConstants.KEY_LOGIN_USERNAME);
                responseData = ResponseData.<SessionUser>otherFailure(2, msg);
            } else {
                String pwdSeed = getPwdSeed();
                removePwdSeed();

                // 比较密码
                String serverCiphertext = user.getPassword();
                serverCiphertext = CryptoUtils.toMD5(serverCiphertext + pwdSeed);
                String clientCiphertext = CryptoUtils.base64Deconder(password);
                if (!Objects.equal(serverCiphertext, clientCiphertext)) {
                    msg = getMessage(I18nKeyConstants.BASENAME, I18nKeyConstants.KEY_LOGIN_PWD);
                    responseData = ResponseData.<SessionUser>otherFailure(3, msg);
                } else {
                    SessionUser sessionUser = new SessionUser(user.getId(), user.getUserName(), "");
                    request.getSession().setAttribute("sessionUser", sessionUser);
                    msg = getMessage(I18nKeyConstants.BASENAME, I18nKeyConstants.KEY_LOGIN_SUCCESS);
                    responseData = ResponseData.<SessionUser>success(msg, sessionUser);
                }
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            responseData = ResponseData.<SessionUser>failure(getMessage(I18nKeyConstants.BASENAME, I18nKeyConstants.KEY_ERROR));
        }
        return responseData;
    }

    /**
     * 登出
     * 
     * @return
     */
    @RequestMapping(path = {"/logout"})
    @ResponseBody
    public ResponseData<String> logout(HttpServletRequest request) {
        SessionUser sessionUser = getCurUser();
        String msg;
        if (null != sessionUser) {
            msg = getMessage(I18nKeyConstants.BASENAME, I18nKeyConstants.KEY_LOGOUT_SUCCESS);
            removeCurUser();
        } else {
            msg = getMessage(I18nKeyConstants.BASENAME, I18nKeyConstants.KEY_LOGOUT_USERNAME);
        }
        return ResponseData.<String>success(msg);
    }
}