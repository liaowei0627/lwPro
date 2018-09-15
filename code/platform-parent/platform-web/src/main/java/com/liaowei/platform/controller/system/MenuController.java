/**
 * platform-web
 * MenuController.java
 */
package com.liaowei.platform.controller.system;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.liaowei.framework.controller.BaseController;
import com.liaowei.framework.core.exception.ApplicationException;
import com.liaowei.framework.page.Pagination;
import com.liaowei.framework.page.Pagination.OrderEnum;
import com.liaowei.framework.query.Where;
import com.liaowei.framework.query.exception.DuplicationCodeException;
import com.liaowei.framework.query.operator.NoValueComparisonOperator;
import com.liaowei.framework.query.operator.OneValueComparisonOperator;
import com.liaowei.framework.response.ResponseData;
import com.liaowei.platform.entity.SysMenu;
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
 * @see com.liaowei.framework.controller.BaseController<MenuModel, MenuVo>
 * @since jdk1.8
 */
@Controller
@RequestMapping(path = {"/system/menu"})
@Slf4j
public class MenuController extends BaseController<SysMenu, MenuVo, MenuModel> {

    @Resource(name = "menuService")
    private IMenuService menuService;

    @Override
    protected MenuModel voToModel(MenuVo v) {
        log.debug("DEBUG：Vo转换Model：" + v.toString());

        MenuModel m = new MenuModel();
        m.copyForVo(v);

        return m;
    }

    /**
     * 取得列表
     * 
     * @param page 页号
     * @param rows 每页行数
     * @param parentId 上级菜单id
     * @param menuType 菜单类型
     * @return
     * @throws ApplicationException
     */
    @RequestMapping(path = {"/list"}, method = RequestMethod.GET)
    @ResponseBody
    public Pagination<MenuListView> list(@RequestParam(name = "parentId", required = true) String parentId,
            @RequestParam(name = "menuType", required = false) String menuType)
            throws ApplicationException {
        log.debug("DEBUG：查询菜单数据列表，parentId=" + parentId);
        Map<String, OrderEnum> orderBy = Maps.newHashMap();
        orderBy.put("orderNum", OrderEnum.ASC);
        Where where = null;
        String[] keywords = new String[] {"text"};
        String[] excKey = new String[] {"menuType", "parentId"};
        where = configWhere(where, keywords, excKey);
        if (null == where) {
            where = Where.rootWhere("valid", OneValueComparisonOperator.EQ, Boolean.TRUE);
        } else {
            where.childAndWhere("valid", OneValueComparisonOperator.EQ, Boolean.TRUE);
        }
        if (Strings.isNullOrEmpty(parentId)) {
            where.childAndWhere("parent.id", NoValueComparisonOperator.IS_NULL);
        } else {
            where.childAndWhere("parent.id", OneValueComparisonOperator.EQ, parentId);
        }
        if (!Strings.isNullOrEmpty(menuType)) {
            MenuTypeEnum menuTypeEnum = MenuTypeEnum.valueOf(menuType);
            if (null != menuTypeEnum) {
                where.childAndWhere("menuType", OneValueComparisonOperator.EQ, menuTypeEnum);
            }
        }
        Pagination<?> p = configPagination();
        Pagination<MenuVo> pagination = menuService.findList(new Pagination<MenuVo>(p.getRows(), p.getPage(), orderBy), where);
        List<MenuVo> data = pagination.getData();
        List<MenuListView> list = Lists.newArrayList();
        for (MenuVo menuVo : data) {
            list.add(new MenuListView(voToModel(menuVo)));
        }
        return new Pagination<MenuListView>(pagination.getTotal(), pagination.getRows(), pagination.getPage(), list);
    }

    /**
     * 取得详情
     * 
     * @param id 菜单id
     * @return
     * @throws ApplicationException
     */
    @RequestMapping(path = {"/load"}, method = RequestMethod.GET)
    @ResponseBody
    public MenuView load(@RequestParam(name = "id", required = true) String id,
            @RequestParam(name = "opt", required = false) String opt) throws ApplicationException {
        log.debug("DEBUG：加载菜单详情，id=" + id);
        MenuView view = null;
        MenuVo vo = menuService.findVo(id);
        if (null != vo) {
            if (OPT_COPY.equals(opt)) {
                vo.setId(null);
            }
            view = new MenuView(voToModel(vo));
        }
        return view;
    }

    /**
     * 保存
     * 
     * @return
     * @throws ApplicationException
     */
    @RequestMapping(path = {"/save"}, method = RequestMethod.POST)
    @ResponseBody
    public ResponseData<String> save(MenuView menu) throws ApplicationException {
        log.debug("DEBUG：保存菜单，menu=" + menu.toString());

        ResponseData<String> responseData;
        MenuModel model = menu.toModel();
        configModel(model);
        MenuVo vo = model.copyToVo();
        try {
            if (Strings.isNullOrEmpty(vo.getId())) {
                menuService.addVo(vo);
            } else {
                menuService.updateVo(vo);
            }
            responseData = new ResponseData<String>(1, "保存成功！");
        } catch (ApplicationException e) {
            log.error(e.getMessage(), e);
            if (e instanceof DuplicationCodeException) {
                responseData = new ResponseData<>(0, e.getMessage());
            } else {
                throw e;
            }
        }

        return responseData;
    }

    /**
     * 批量删除
     * 
     * @param id 要删除的菜单数组
     * @return
     * @throws ApplicationException
     */
    @RequestMapping(path = {"/del"}, method = RequestMethod.POST)
    @ResponseBody
    public ResponseData<String> del(@RequestParam(name = "ids[]", required = true) String[] id) throws ApplicationException {
        log.debug("DEBUG：删除菜单，del=" + id.toString());

        menuService.delList(id);

        return new ResponseData<String>(1, "删除成功！");
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
    public ResponseData<List<TreeView<SysMenu, MenuVo, MenuModel>>> tree(@RequestParam(name = "id", required = false) String id,
            @RequestParam(name = "excId", required = false) String excId) throws ApplicationException {
        log.debug("DEBUG：取得菜单树，parentId=" + id);
        Map<String, OrderEnum> orderBy = Maps.newHashMap();
        orderBy.put("orderNum", OrderEnum.ASC);
        Pagination<MenuVo> pagination = new Pagination<MenuVo>(orderBy);
        pagination.setPageable(false);
        Where where = Where.rootWhere("valid", OneValueComparisonOperator.EQ, Boolean.TRUE);
        if (Strings.isNullOrEmpty(id)) {
            where.andWhere("parent", NoValueComparisonOperator.IS_NULL);
        } else {
            where.andWhere("parent.id", OneValueComparisonOperator.EQ, id);
        }
        if (!Strings.isNullOrEmpty(excId)) {
            where.childAndWhere("id", OneValueComparisonOperator.UE, excId);
        }
        pagination = menuService.findList(pagination, where);
        List<MenuVo> data = pagination.getData();
        List<TreeView<SysMenu, MenuVo, MenuModel>> list = Lists.newArrayList();
        for (MenuVo menuVo : data) {
            list.add(new TreeView<SysMenu, MenuVo, MenuModel>(voToModel(menuVo)));
        }
        return new ResponseData<List<TreeView<SysMenu, MenuVo, MenuModel>>>(1, "取得菜单树形数据成功！", list);
    }
}