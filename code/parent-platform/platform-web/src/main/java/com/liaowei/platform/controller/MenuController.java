/**
 * platform-web
 * MenuController.java
 */
package com.liaowei.platform.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.liaowei.framework.controller.BaseController;
import com.liaowei.framework.core.exception.ApplicationException;
import com.liaowei.framework.page.Pagination;
import com.liaowei.platform.entity.SysMenu;
import com.liaowei.platform.model.MenuModel;
import com.liaowei.platform.service.IMenuService;
import com.liaowei.platform.view.MenuListView;
import com.liaowei.platform.vo.MenuVo;

import lombok.extern.slf4j.Slf4j;

/**
 * MenuController
 *
 * 菜单管理Controller
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-21 23:18:54
 * @see com.liaowei.framework.controller.BaseController<MenuModel, MenuVo, SysMenu, String>
 * @since jdk1.8
 */
@Controller
@RequestMapping(path = {"/sys/menu"})
@Slf4j
public class MenuController extends BaseController<MenuModel, MenuVo, SysMenu, String> {

    @Resource(name = "menuService")
    private IMenuService menuService;

    @Override
    protected MenuModel voToModel(MenuVo v) {
        MenuModel m = new MenuModel();
        BeanUtils.copyProperties(v, m);
        return m;
    }

    @Override
    protected MenuVo modelToVo(MenuModel m) {
        MenuVo v = new MenuVo();
        BeanUtils.copyProperties(m, v);
        return v;
    }

    @RequestMapping(path = {"/list"})
    @ResponseBody
    public Pagination<MenuListView> list(Pagination<MenuVo> page, MenuVo vo, HttpServletRequest request) throws ApplicationException {
        page = menuService.findPage(page, vo);
        List<MenuVo> data = page.getData();
        List<MenuListView> list = Lists.newArrayList();
        for (MenuVo menuVo : data) {
            list.add(new MenuListView(voToModel(menuVo)));
        }
        return new Pagination<MenuListView>(page.getTotal(), page.getPageSize(), page.getPageNumber(), list);
    }
}