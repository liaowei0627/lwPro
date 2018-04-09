/**
 * study-web
 * IndexController.java
 */
package com.liaowei.study.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liaowei.framework.response.ResponseData;
import com.liaowei.study.service.ILoginService;

/**
 * IndexController
 *
 * 主页及登录登出
 *
 * @author liaowei
 * @date 创建时间：2018年4月6日 下午10:18:45 
 * @since jdk1.8
 */
@Controller
@RequestMapping()
public class IndexController {

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
	public String index(HttpServletRequest request, Model model) {
		return "index";
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
     * 登录
     * 
     * @return
     */
    @RequestMapping(path = {"/login"})
    @ResponseBody
    public ResponseData<String> doLogin(
            @RequestParam(name = "userName", required = true) String userName,
            @RequestParam(name = "password", required = true) String password,
            HttpServletRequest request) {
//        loginService.f
        return new ResponseData<String>(1, "登录成功");
    }

    /**
     * 登出
     * 
     * @return
     */
    @RequestMapping(path = {"/logout"})
    @ResponseBody
    public ResponseData<String> logout() {
        return new ResponseData<String>(1, "登出成功");
    }
}