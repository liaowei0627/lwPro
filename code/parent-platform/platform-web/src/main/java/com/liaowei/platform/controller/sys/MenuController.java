/**
 * platform-web
 * MenuController.java
 */
package com.liaowei.platform.controller.sys;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.liaowei.framework.controller.BaseController;
import com.liaowei.framework.core.exception.ApplicationException;
import com.liaowei.framework.page.Pagination;
import com.liaowei.platform.entity.SysMenu;
import com.liaowei.platform.enums.MenuTypeEnum;
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
 * @see com.liaowei.framework.controller.BaseController<MenuModel, MenuVo,
 *      SysMenu, String>
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
        log.debug("Vo转换Model：" + v.toString());
        MenuModel m = new MenuModel();
        BeanUtils.copyProperties(v, m);
        return m;
    }

    @Override
    protected MenuVo modelToVo(MenuModel m) {
        log.debug("Model转换Vo：" + m.toString());
        MenuVo v = new MenuVo();
        BeanUtils.copyProperties(m, v);
        return v;
    }

    /**
     * 请求列表数据
     * 
     * @param page 页号
     * @param rows 每页行数
     * @param model 查询条件
     * @return
     * @throws ApplicationException
     */
    @RequestMapping(path = {"/list"})
    @ResponseBody
    public Pagination<MenuListView> list(@RequestParam(name = "page") int page, @RequestParam(name = "rows") int rows,
            @RequestParam(name = "menuText") String menuText, @RequestParam(name = "menuType") String menuType) throws ApplicationException {
        log.debug("查询菜单数据列表，page=" + page + "；rows=" + rows + "；menuText=" + menuText + "；menuType=" + menuType);
        MenuVo vo = new MenuVo();
        vo.setMenuText(menuText);
        vo.setMenuType(MenuTypeEnum.valueOf(menuType));
        Pagination<MenuVo> pagination = menuService.findPage(new Pagination<>(rows, page), vo);
        List<MenuVo> data = pagination.getData();
        List<MenuListView> list = Lists.newArrayList();
        for (MenuVo menuVo : data) {
            list.add(new MenuListView(voToModel(menuVo)));
        }
        return new Pagination<MenuListView>(pagination.getTotal(), pagination.getRows(), pagination.getPage(), list);
    }
}