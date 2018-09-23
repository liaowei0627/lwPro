/**
 * study-web
 * IndexController.java
 */
package com.flyhaze.platform.controller;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.flyhaze.framework.SessionUser;
import com.flyhaze.framework.core.exception.ApplicationException;
import com.flyhaze.framework.exception.LoginException;
import com.flyhaze.framework.mvc.controller.BaseController;
import com.flyhaze.framework.mvc.response.ResponseData;
import com.flyhaze.framework.mvc.view.TreeView;
import com.flyhaze.platform.entity.SysMenu;
import com.flyhaze.platform.service.ILoginService;
import com.flyhaze.platform.vo.MenuVo;

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
        @SuppressWarnings("unchecked")
        SessionUser<SysMenu, MenuVo> sessionUser = getCurUser();
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
    public ResponseData<String> seed() {
        String seed = setPwdSeed();
        return new ResponseData<String>(1, "seed生成成功", seed);
    }

    /**
     * 登录 客户端密码的加密方式： 明文MD5编码转16进制字符串 String pwdCiphertext = CryptoUtils.toMD5(pwd);
     * 加上seed再MD5编码转16进制字符串 pwdCiphertext = CryptoUtils.toMD5(pwdCiphertext + seed);
     * MD5字符串BASE64编码转16进制字符串 pwdCiphertext =
     * CryptoUtils.base64Encoder(pwdCiphertext); 服务端密码验证方式： 用户数据中的密码同上MD5编码两次
     * 表单中的密码字段，BASE64解码后与之比较 一致就是密码正确
     * 
     * @param userName
     * @param password
     * @param request
     * @return
     * @throws ApplicationException
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     */
    @RequestMapping(path = {"/login"}, method = RequestMethod.POST)
    @ResponseBody
    public ResponseData<SessionUser> doLogin(@RequestParam(name = "userName", required = true) String userName,
            @RequestParam(name = "password", required = true) String password)
            throws ApplicationException {

        // 比较密码
        String pwdSeed = getPwdSeed();
        removePwdSeed();

        // 用户登录对象
        SessionUser<SysMenu, MenuVo> sessionUser;
        try {
            sessionUser = loginService.login(userName, password, pwdSeed);
            if (null == sessionUser) {
                return new ResponseData<>(2, "用户不存在！");
            }
        } catch (LoginException e) {
            String msg = e.getMessage();
            log.error(msg, e);
            return new ResponseData<>(3, msg);
        }

        setCurUser(sessionUser);
        log.debug("用户登录：用户名=" + userName + "，登录成功");
        return new ResponseData<SessionUser>(1, "登录成功！", sessionUser);
    }

    /**
     * 登出
     * 
     * @return
     */
    @RequestMapping(path = {"/logout"}, method = RequestMethod.GET)
    @ResponseBody
    public ResponseData<String> logout() {
        removeCurUser();
        return new ResponseData<String>(1, "登出成功");
    }

    /**
     * 取得菜单列表
     * 
     * @return
     * @throws ApplicationException
     */
    @RequestMapping(path = {"/loadMenus"}, method = RequestMethod.GET)
    @ResponseBody
    public ResponseData<List<TreeView<SysMenu, MenuVo>>> loadMenus() throws ApplicationException {
        @SuppressWarnings("unchecked")
        SessionUser<SysMenu, MenuVo> sessionUser = getCurUser();
        List<TreeView<SysMenu, MenuVo>> list = sessionUser.getMenuList();
        return new ResponseData<List<TreeView<SysMenu, MenuVo>>>(1, "登出成功", list);
    }
}