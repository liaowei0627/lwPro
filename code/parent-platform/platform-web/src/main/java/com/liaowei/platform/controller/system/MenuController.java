/**
 * platform-web
 * MenuController.java
 */
package com.liaowei.platform.controller.sys;

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
@RequestMapping(path = {"/sys/menu"})
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
    public Pagination<MenuListView> list(@RequestParam(name = "parentId", required = true) String parentId,
            @RequestParam(name = "menuType", required = false) String menuType, Pagination<?> pagination)
            throws ApplicationException {
        log.debug("DEBUG：查询菜单数据列表，pagination=" + pagination.toString());
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
        Pagination<MenuVo> paginationTmp;
        paginationTmp = menuService.findList(new Pagination<MenuVo>(pagination.getRows(), pagination.getPage(), orderBy), where);
        List<MenuVo> data = paginationTmp.getData();
        List<MenuListView> list = Lists.newArrayList();
        for (MenuVo menuVo : data) {
            list.add(new MenuListView(voToModel(menuVo)));
        }
        return new Pagination<MenuListView>(paginationTmp.getTotal(), paginationTmp.getRows(), paginationTmp.getPage(), list);
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

    /**
     * 取得菜单详情
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
     * 保存菜单
     * 
     * @param parentId 上级菜单id
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
     * 保存菜单
     * 
     * @param parentId 上级菜单id
     * @return
     * @throws ApplicationException
     */
    @RequestMapping(path = {"/checkCode"}, method = RequestMethod.GET)
    @ResponseBody
    public ResponseData<String> checkCode(@RequestParam(name = "id", required = true) String id,
            @RequestParam(name = "code", required = true) String code) throws ApplicationException {
        log.debug("DEBUG：检查编号是否重复，code=" + code);

        Where where = Where.rootWhere("code", OneValueComparisonOperator.EQ, code);
        where.andWhere("id", OneValueComparisonOperator.UE, id);
        Pagination<MenuVo> pagination = menuService.findList(null, where);
        ResponseData<String> responseData;
        if (pagination.getTotal() == 0) {
            responseData = new ResponseData<String>(1, "编号不存在重复。");
        } else {
            responseData = new ResponseData<String>(0, "编号重复！");
        }

        return responseData;
    }

    /**
     * 删除菜单
     * 
     * @param id 要删除的菜单数组
     * @return
     * @throws ApplicationException
     */
    @RequestMapping(path = {"/del"}, method = RequestMethod.POST)
    @ResponseBody
    public ResponseData<String> del(@RequestParam(name = "ids[]", required = true) String[] id) throws ApplicationException {
        log.debug("DEBUG：删除菜单，del=" + id.toString());

        ResponseData<String> responseData;
        menuService.delList(id);
        responseData = new ResponseData<String>(1, "删除成功！");

        return responseData;
    }
}