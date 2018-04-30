/**
 * platform-web
 * MenuController.java
 */
package com.liaowei.platform.controller.sys;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.liaowei.framework.SessionUser;
import com.liaowei.framework.controller.BaseController;
import com.liaowei.framework.core.enums.OrderEnum;
import com.liaowei.framework.core.exception.ApplicationException;
import com.liaowei.framework.page.Pagination;
import com.liaowei.framework.response.ResponseData;
import com.liaowei.platform.enums.MenuTypeEnum;
import com.liaowei.platform.model.MenuModel;
import com.liaowei.platform.service.IMenuService;
import com.liaowei.platform.view.MenuListView;
import com.liaowei.platform.view.MenuView;
import com.liaowei.platform.view.TreeView;
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
public class MenuController extends BaseController<MenuModel, MenuVo> {

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
    @RequestMapping(path = {"/list"}, method = RequestMethod.GET)
    @ResponseBody
    public Pagination<MenuListView> list(@RequestParam(name = "page") int page, @RequestParam(name = "rows") int rows,
            @RequestParam(name = "menuText", required = false) String menuText,
            @RequestParam(name = "menuType", required = false) String menuType) throws ApplicationException {
        log.debug("查询菜单数据列表，page=" + page + "；rows=" + rows + "；menuText=" + menuText + "；menuType=" + menuType);
        MenuVo vo = new MenuVo();
        if (!Strings.isNullOrEmpty(menuText)) {
            vo.setMenuText(menuText);
        }
        if (!Strings.isNullOrEmpty(menuType)) {
            vo.setMenuType(MenuTypeEnum.valueOf(menuType));
        }
        Map<String, OrderEnum> orderBy = Maps.newHashMap();
        orderBy.put("orderNum", OrderEnum.ASC);
        Pagination<MenuVo> pagination = menuService.findPage(new Pagination<>(rows, page), vo, orderBy);
        List<MenuVo> data = pagination.getData();
        List<MenuListView> list = Lists.newArrayList();
        for (MenuVo menuVo : data) {
            list.add(new MenuListView(voToModel(menuVo)));
        }
        return new Pagination<MenuListView>(pagination.getTotal(), pagination.getRows(), pagination.getPage(), list);
    }

    /**
     * 取得菜单树
     * 
     * @param parentId 上级菜单id
     * @return
     * @throws ApplicationException
     */
    @RequestMapping(path = {"/tree"}, method = RequestMethod.GET)
    @ResponseBody
    public ResponseData<List<TreeView<MenuModel>>> tree(@RequestParam(name = "parentId", required = false) String parentId) throws ApplicationException {
        log.debug("取得菜单树，parentId=" + parentId);
        MenuVo vo = new MenuVo();
        MenuVo parent = new MenuVo();
        parent.setId(parentId);
        vo.setParent(parent);
        Map<String, OrderEnum> orderBy = Maps.newHashMap();
        orderBy.put("orderNum", OrderEnum.ASC);
        List<MenuVo> data = menuService.findList(vo, orderBy);
        List<TreeView<MenuModel>> list = Lists.newArrayList();
        for (MenuVo menuVo : data) {
            list.add(new TreeView<MenuModel>(voToModel(menuVo)));
        }
        return new ResponseData<List<TreeView<MenuModel>>>(1, "取得菜单树形数据成功！", list);
    }

    /**
     * 取得菜单树
     * 
     * @param parentId 上级菜单id
     * @return
     * @throws ApplicationException
     */
    @RequestMapping(path = {"/save"}, method = RequestMethod.POST)
    @ResponseBody
    public ResponseData<String> save(MenuView menu, HttpServletRequest request) throws ApplicationException {
        log.debug("取得菜单树，menu=" + menu.toString());
        ResponseData<String> responseData;
        SessionUser sessionUser = getCurUser(request);
        String userName = sessionUser.getUserName();
        MenuVo vo = modelToVo(menu.toModel());
        if (Strings.isNullOrEmpty(vo.getId())) {
            vo.setCreator(userName);
            vo.setReviser(userName);
            menuService.addVo(vo);
        } else {
            vo.setReviser(userName);
            menuService.updateVo(vo);
        }
        responseData = new ResponseData<String>(1, "保存成功！");
        return responseData;
    }
}