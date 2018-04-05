package com.liaowei.study.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.liaowei.framework.controller.SpringBaseController;
import com.liaowei.framework.service.ISpringService;
import com.liaowei.study.entity.SysUser;
import com.liaowei.study.model.UserModel;
import com.liaowei.study.service.ISysUserService;
import com.liaowei.study.vo.UserVo;

@Controller
@RequestMapping()
public class IndexController extends SpringBaseController<UserModel, UserVo, SysUser> {

    private ISysUserService sysUserService;

    @Override
    protected UserModel modelCopy(UserVo v) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected String getClassName() {
        return IndexController.class.getName();
    }

    @Override
    protected ISpringService<UserVo, SysUser, String> getService() {
        return sysUserService;
    }

	@RequestMapping(value = {"/index"})
	public String index(HttpServletRequest request, Model model) {
		return "index";
	}

	public String login() {
        return "index";
	}
}