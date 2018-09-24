/**
 * platform-web
 * MenuController.java
 */
package com.flyhaze.platform.controller.system;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.flyhaze.framework.core.constants.I18nKeyConstants;
import com.flyhaze.framework.core.exception.ApplicationException;
import com.flyhaze.framework.core.page.Pagination;
import com.flyhaze.framework.core.query.Where;
import com.flyhaze.framework.core.query.operator.NoValueComparisonOperator;
import com.flyhaze.framework.core.query.operator.OneValueComparisonOperator;
import com.flyhaze.framework.core.query.order.OrderBy;
import com.flyhaze.framework.core.query.order.OrderEnum;
import com.flyhaze.framework.mvc.controller.BaseController;
import com.flyhaze.framework.mvc.response.ResponseData;
import com.flyhaze.framework.mvc.view.TreeView;
import com.flyhaze.platform.constants.SysI18nKeyConstants;
import com.flyhaze.platform.entity.SysMenu;
import com.flyhaze.platform.enums.MenuTypeEnum;
import com.flyhaze.platform.service.IMenuService;
import com.flyhaze.platform.view.MenuListView;
import com.flyhaze.platform.view.MenuView;
import com.flyhaze.platform.vo.MenuVo;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;

import lombok.extern.slf4j.Slf4j;

/**
 * MenuController
 *
 * 菜单管理Controller
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-21 23:18:54
 * @see com.flyhaze.framework.mvc.controller.BaseController<SysMenu, MenuVo>
 * @since jdk1.8
 */
@Controller
@RequestMapping(path = {"/system/menu"})
@Slf4j
public class MenuController extends BaseController<SysMenu, MenuVo> {

    @Resource(name = "menuService")
    private IMenuService menuService;

    /**
     * 取得列表
     * 
     * @param page 页号
     * @param rows 每页行数
     * @param parentId 上级菜单id
     * @param menuType 菜单类型
     * @return
     */
    @RequestMapping(path = {"/list"}, method = RequestMethod.GET)
    @ResponseBody
    public Pagination<MenuListView> list(@RequestParam(name = "parentId", required = true) String parentId,
            @RequestParam(name = "menuType", required = false) String menuType) {
        Pagination<MenuListView> responsePagination;
        try {
            List<OrderBy> orderBy = Lists.<OrderBy>newArrayList();
            orderBy.add(new OrderBy("orderNum", OrderEnum.ASC));
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
            Pagination<MenuVo> pagination = menuService.findList(new Pagination<MenuVo>(p.getRows(), p.getPage(), orderBy),
                    where);
            List<MenuVo> data = pagination.getData();
            List<MenuListView> list = Lists.<MenuListView>newArrayList();
            for (MenuVo menuVo : data) {
                list.add(new MenuListView(menuVo));
            }
            responsePagination = new Pagination<MenuListView>(pagination.getTotal(), pagination.getRows(), pagination.getPage(),
                    list);
        } catch (ApplicationException e) {
            log.error(e.getMessage(), e);
            responsePagination = new Pagination<>();
            responsePagination.setData(Lists.<MenuListView>newArrayList());
        }
        return responsePagination;
    }

    /**
     * 取得详情
     * 
     * @param id 菜单id
     * @return
     */
    @RequestMapping(path = {"/load"}, method = RequestMethod.GET)
    @ResponseBody
    public ResponseData<MenuView> load(@RequestParam(name = "id", required = true) String id,
            @RequestParam(name = "opt", required = false) String opt) {
        ResponseData<MenuView> responseData;
        try {
            MenuVo vo = menuService.findVo(id);
            if (null != vo) {
                if (OPT_COPY.equals(opt)) {
                    vo.setId(null);
                }
                MenuView view = new MenuView(vo);
                responseData = new ResponseData<MenuView>(1,
                        getMessage(I18nKeyConstants.BASENAME, I18nKeyConstants.KEY_LOAD_SUCCESS), view);
            } else {
                responseData = new ResponseData<MenuView>(2,
                        getMessage(SysI18nKeyConstants.BASENAME, SysI18nKeyConstants.KEY_LOAD_MENU));
            }
        } catch (ApplicationException e) {
            log.error(e.getMessage(), e);
            responseData = new ResponseData<MenuView>(0, getMessage(I18nKeyConstants.BASENAME, I18nKeyConstants.KEY_ERROR));
        }
        return responseData;
    }

    /**
     * 保存
     * 
     * @return
     */
    @RequestMapping(path = {"/save"}, method = RequestMethod.POST)
    @ResponseBody
    public ResponseData<String> save(MenuView menuView) {
        ResponseData<String> responseData;

        MenuVo vo = menuView.toVo();
        configVo(vo);
        try {
            MenuVo menuVo;
            if (Strings.isNullOrEmpty(vo.getId())) {
                menuVo = menuService.addVo(vo);
            } else {
                menuVo = menuService.updateVo(vo);
            }
            if (Strings.isNullOrEmpty(menuVo.getMsg())) {
                responseData = new ResponseData<String>(1,
                        getMessage(I18nKeyConstants.BASENAME, I18nKeyConstants.KEY_SAVE_SUCCESS));
            } else {
                responseData = new ResponseData<String>(2, getMessage(SysI18nKeyConstants.BASENAME, menuVo.getMsg()));
            }
        } catch (ApplicationException e) {
            log.error(e.getMessage(), e);
            responseData = new ResponseData<String>(0, getMessage(I18nKeyConstants.BASENAME, I18nKeyConstants.KEY_ERROR));
        }

        return responseData;
    }

    /**
     * 批量删除
     * 
     * @param id 要删除的菜单数组
     * @return
     */
    @RequestMapping(path = {"/del"}, method = RequestMethod.POST)
    @ResponseBody
    public ResponseData<String> del(@RequestParam(name = "ids[]", required = true) String[] id) {
        ResponseData<String> responseData;
        try {
            menuService.delList(id);
            return new ResponseData<String>(1, getMessage(I18nKeyConstants.BASENAME, I18nKeyConstants.KEY_DEL_SUCCESS));
        } catch (ApplicationException e) {
            log.error(e.getMessage(), e);
            responseData = new ResponseData<String>(0, getMessage(I18nKeyConstants.BASENAME, I18nKeyConstants.KEY_ERROR));
        }
        return responseData;
    }

    /**
     * 取得菜单树
     * 
     * @param parentId 上级菜单id
     * @return
     */
    @RequestMapping(path = {"/tree"}, method = RequestMethod.GET)
    @ResponseBody
    public ResponseData<List<TreeView<SysMenu, MenuVo>>> tree(@RequestParam(name = "id", required = false) String id,
            @RequestParam(name = "excId", required = false) String excId) {
        ResponseData<List<TreeView<SysMenu, MenuVo>>> responseData;
        try {
            List<OrderBy> orderBy = Lists.<OrderBy>newArrayList();
            orderBy.add(new OrderBy("orderNum", OrderEnum.ASC));
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
            List<TreeView<SysMenu, MenuVo>> list = Lists.<TreeView<SysMenu, MenuVo>>newArrayList();
            for (MenuVo menuVo : data) {
                list.add(new TreeView<SysMenu, MenuVo>(menuVo));
            }
            responseData = new ResponseData<List<TreeView<SysMenu, MenuVo>>>(1,
                    getMessage(SysI18nKeyConstants.BASENAME, SysI18nKeyConstants.KEY_TREE_MENU), list);
        } catch (ApplicationException e) {
            log.error(e.getMessage(), e);
            responseData = new ResponseData<List<TreeView<SysMenu, MenuVo>>>(0,
                    getMessage(I18nKeyConstants.BASENAME, I18nKeyConstants.KEY_ERROR),
                    Lists.<TreeView<SysMenu, MenuVo>>newArrayList());
        }
        return responseData;
    }
}