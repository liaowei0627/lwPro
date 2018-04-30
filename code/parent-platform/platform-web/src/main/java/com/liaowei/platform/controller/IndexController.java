/**
 * study-web
 * IndexController.java
 */
package com.liaowei.platform.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.base.Objects;
import com.liaowei.framework.SessionUser;
import com.liaowei.framework.controller.BaseController;
import com.liaowei.framework.core.model.IBasisModel;
import com.liaowei.framework.core.vo.IBasisVo;
import com.liaowei.framework.response.ResponseData;
import com.liaowei.framework.util.CryptoUtils;
import com.liaowei.platform.service.ILoginService;
import com.liaowei.platform.util.SeedUtils;
import com.liaowei.platform.vo.UserVo;

import lombok.extern.slf4j.Slf4j;

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
    public ResponseData<SessionUser> checkLogged(HttpServletRequest request) {
        SessionUser sessionUser = getCurUser(request);
        if (null != sessionUser) {
            return new ResponseData<SessionUser>(1, "已登录！", sessionUser);
        } else {
            return new ResponseData<>(0, "未登录！");
        }
    }

    /**
     * 获取密码加密种子字符串
     * 
     * @return
     */
    @RequestMapping(path = {"/seed"}, method = RequestMethod.GET)
    @ResponseBody
    public ResponseData<String> seed(HttpServletRequest request) {
        String seed = SeedUtils.getRandomString(6);
        setSessionAttr("pwdSeed", seed, request);
        return new ResponseData<String>(1, "seed生成成功", seed);
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
    @RequestMapping(path = {"/login"}, method = RequestMethod.POST)
    @ResponseBody
    public ResponseData<SessionUser> doLogin(
            @RequestParam(name = "userName", required = true) String userName,
            @RequestParam(name = "password", required = true) String password,
            HttpServletRequest request) {
        try {
            // 查询用户对象
            UserVo user = loginService.findByUserName(userName);
            if (null == user) {
                return new ResponseData<>(2, "用户不存在！");
            }

            String pwdSeed = String.valueOf(getSessionAttr("pwdSeed", request));

            // 比较密码
            String serverCiphertext = user.getPassword();
            serverCiphertext = CryptoUtils.toMD5(serverCiphertext + pwdSeed);
            String clientCiphertext = CryptoUtils.base64Deconder(password);
            if (!Objects.equal(serverCiphertext, clientCiphertext)) {
                return new ResponseData<>(3, "密码错误！");
            }
            
            SessionUser sessionUser = new SessionUser(user.getId(), user.getUserName());
            setCurUser(sessionUser, request);
            return new ResponseData<SessionUser>(1, "登录成功！", sessionUser);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResponseData<>(0, "登录失败！");
        }
    }

    /**
     * 登出
     * 
     * @return
     */
    @RequestMapping(path = {"/logout"}, method = RequestMethod.GET)
    @ResponseBody
    public ResponseData<String> logout(HttpServletRequest request) {
        removeCurUser(request);
        return new ResponseData<String>(1, "登出成功");
    }

    @Override
    protected IBasisModel voToModel(IBasisVo v) {
        return null;
    }

    @Override
    protected IBasisVo modelToVo(IBasisModel m) {
        return null;
    }
}