/**
 * platform-web
 * MenuController.java
 */
package com.liaowei.platform.controller.sys;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

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
import com.liaowei.framework.core.exception.ApplicationException;
import com.liaowei.framework.page.Pagination;
import com.liaowei.framework.page.Pagination.OrderEnum;
import com.liaowei.framework.query.Where;
import com.liaowei.framework.query.operator.NoValueComparisonOperator;
import com.liaowei.framework.query.operator.OneValueComparisonOperator;
import com.liaowei.framework.response.ResponseData;
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
public class MenuController extends BaseController<MenuModel, MenuVo> {

    @Resource(name = "menuService")
    private IMenuService menuService;

    @Override
    protected MenuModel voToModel(MenuVo v) {
        log.debug("Vo转换Model：" + v.toString());

        MenuVo voParent = v.getParent();
        MenuModel parent = null;
        if (null != voParent) {
            parent = voToModel(voParent);
        }
        Set<MenuModel> children = null;
        Set<MenuVo> voChildren = v.getChildren();
        if (null != voChildren && !voChildren.isEmpty()) {
            children = new HashSet<>();
            for (MenuVo vo : voChildren) {
                children.add(voToModel(vo));
            }
        }
        MenuModel m = new MenuModel(v.getId(), v.getMenuUrl(), v.getMenuType(), v.getCode(), v.getText(), v.getFullCode(),
                v.getFullText(), parent, children, v.getOrderNum(), v.getValid(), v.getCreator(), v.getCreateTime(),
                v.getReviser(), v.getModifyTime());

        return m;
    }

    @Override
    protected MenuVo modelToVo(MenuModel m) {
        log.debug("Model转换Vo：" + m.toString());

        MenuModel modelParent = m.getParent();
        MenuVo parent = null;
        if (null != modelParent) {
            parent = modelToVo(modelParent);
        }
        Set<MenuVo> children = null;
        Set<MenuModel> modelChildren = m.getChildren();
        if (null != modelChildren && !modelChildren.isEmpty()) {
            children = new HashSet<>();
            for (MenuModel vo : modelChildren) {
                children.add(modelToVo(vo));
            }
        }
        MenuVo v = new MenuVo(m.getId(), m.getMenuUrl(), m.getMenuType(), m.getCode(), m.getText(), m.getFullCode(),
                m.getFullText(), parent, children, m.getOrderNum(), m.getValid(), m.getCreator(), m.getCreateTime(),
                m.getReviser(), m.getModifyTime());

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
    public Pagination<MenuListView> list(Pagination<?> pagination) throws ApplicationException {
        log.debug("查询菜单数据列表，pagination=" + pagination.toString());
        Map<String, OrderEnum> orderBy = Maps.newHashMap();
        orderBy.put("orderNum", OrderEnum.ASC);
        Where where = null;
        where = createWhere(where);
        if (null == where) {
            where = Where.rootWhere("valid", OneValueComparisonOperator.EQ, Boolean.TRUE);
        } else {
            where.childAndWhere("valid", OneValueComparisonOperator.EQ, Boolean.TRUE);
        }
        Pagination<MenuVo> paginationTmp = menuService.findList(new Pagination<MenuVo>(pagination.getRows(), pagination.getPage(), orderBy), where);
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
    public ResponseData<List<TreeView<MenuModel>>> tree(@RequestParam(name = "parentId", required = false) String parentId)
            throws ApplicationException {
        log.debug("取得菜单树，parentId=" + parentId);
        Map<String, OrderEnum> orderBy = Maps.newHashMap();
        orderBy.put("orderNum", OrderEnum.ASC);
        Pagination<MenuVo> pagination = new Pagination<MenuVo>(orderBy);
        pagination.setPageable(false);
        Where where = Where.rootWhere("valid", OneValueComparisonOperator.EQ, Boolean.TRUE);
        if (Strings.isNullOrEmpty(parentId)) {
            where.andWhere("parent", NoValueComparisonOperator.IS_NULL);
        } else {
            where.andWhere("parent.id", OneValueComparisonOperator.EQ, parentId);
        }
        pagination = menuService.findList(pagination, where);
        List<MenuVo> data = pagination.getData();
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
    public ResponseData<String> save(MenuView menu) throws ApplicationException {
        log.debug("取得菜单树，menu=" + menu.toString());

        ResponseData<String> responseData;
        SessionUser sessionUser = getCurUser();
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