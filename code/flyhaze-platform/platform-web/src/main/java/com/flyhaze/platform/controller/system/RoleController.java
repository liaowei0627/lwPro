/**
 * platform-web
 * RoleController.java
 */
package com.flyhaze.platform.controller.system;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.flyhaze.core.constants.I18nKeyConstants;
import com.flyhaze.core.exception.ApplicationException;
import com.flyhaze.core.page.Pagination;
import com.flyhaze.core.query.Where;
import com.flyhaze.core.query.order.OrderBy;
import com.flyhaze.core.query.order.OrderEnum;
import com.flyhaze.framework.mvc.controller.BaseController;
import com.flyhaze.framework.mvc.response.ResponseData;
import com.flyhaze.platform.constants.SysI18nKeyConstants;
import com.flyhaze.platform.entity.SysRole;
import com.flyhaze.platform.service.IRoleService;
import com.flyhaze.platform.view.RoleListView;
import com.flyhaze.platform.view.RoleView;
import com.flyhaze.platform.vo.RoleVo;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;

import lombok.extern.slf4j.Slf4j;

/**
 * RoleController
 *
 * 角色管理Controller
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-09-26 05:19:34
 * @see com.flyhaze.framework.mvc.controller.BaseController<SysRole, RoleVo>
 * @since jdk1.8
 */
@Controller
@RequestMapping(path = {"/system/role"})
@Slf4j
public class RoleController extends BaseController<SysRole, RoleVo> {


    @Resource(name = "roleService")
    private IRoleService roleService;

    /**
     * 取得列表
     * 
     * @param page 页号
     * @param rows 每页行数
     * @param model 查询条件
     * @return
     */
    @RequestMapping(path = {"/list"}, method = RequestMethod.GET)
    @ResponseBody
    public Pagination<RoleListView> list() {
        Pagination<RoleListView> responsePagination;
        try {
            List<OrderBy> orderBy = Lists.<OrderBy>newArrayList();
            orderBy.add(new OrderBy("roleCode", OrderEnum.ASC));
            Where where = null;
            String[] keywords = new String[] {"roleName"};
            where = configWhere(where, keywords);
            Pagination<?> p = configPagination();
            Pagination<RoleVo> pagination = roleService
                    .findList(new Pagination<RoleVo>(p.getRows(), p.getPage(), orderBy), where);
            List<RoleVo> data = pagination.getData();
            List<RoleListView> list = Lists.<RoleListView>newArrayList();
            for (RoleVo roleVo : data) {
                list.add(new RoleListView(roleVo));
            }
            responsePagination = new Pagination<RoleListView>(pagination.getTotal(), pagination.getRows(),
                    pagination.getPage(), list);
        } catch (ApplicationException e) {
            log.error(e.getMessage(), e);
            responsePagination = new Pagination<>();
            responsePagination.setData(Lists.<RoleListView>newArrayList());
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
    public ResponseData<RoleView> load(@RequestParam(name = "id", required = true) String id,
            @RequestParam(name = "opt", required = false) String opt) {
        ResponseData<RoleView> responseData;
        try {
            RoleVo vo = roleService.findVo(id);
            if (null != vo) {
                if (OPT_COPY.equals(opt)) {
                    vo.setId(null);
                }
                RoleView view = new RoleView(vo);
                responseData = ResponseData
                        .<RoleView>success(getMessage(I18nKeyConstants.BASENAME, I18nKeyConstants.KEY_LOAD_SUCCESS), view);
            } else {
                responseData = ResponseData.<RoleView>otherFailure(2,
                        getMessage(SysI18nKeyConstants.BASENAME, SysI18nKeyConstants.KEY_LOAD_ROLE));
            }
        } catch (ApplicationException e) {
            log.error(e.getMessage(), e);
            responseData = ResponseData.<RoleView>failure(getMessage(I18nKeyConstants.BASENAME, I18nKeyConstants.KEY_ERROR));
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
    public ResponseData<String> save(RoleView roleView) {
        ResponseData<String> responseData;

        RoleVo vo = roleView.toVo();
        configVo(vo);
        try {
            RoleVo roleVo;
            if (Strings.isNullOrEmpty(vo.getId())) {
                roleVo = roleService.addVo(vo);
            } else {
                roleVo = roleService.updateVo(vo);
            }
            if (Strings.isNullOrEmpty(roleVo.getMsg())) {
                responseData = ResponseData
                        .<String>success(getMessage(I18nKeyConstants.BASENAME, I18nKeyConstants.KEY_SAVE_SUCCESS));
            } else {
                responseData = ResponseData.<String>otherFailure(2, getMessage(SysI18nKeyConstants.BASENAME, roleVo.getMsg()));
            }
        } catch (ApplicationException e) {
            log.error(e.getMessage(), e);
            responseData = ResponseData.<String>failure(getMessage(I18nKeyConstants.BASENAME, I18nKeyConstants.KEY_ERROR));
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
            String rs = roleService.delList(id);
            if (Strings.isNullOrEmpty(rs)) {
                responseData = ResponseData
                        .<String>success(getMessage(I18nKeyConstants.BASENAME, I18nKeyConstants.KEY_DEL_SUCCESS));
            } else {
                responseData = ResponseData.<String>failure(rs);
            }
        } catch (ApplicationException e) {
            log.error(e.getMessage(), e);
            responseData = ResponseData.<String>failure(getMessage(I18nKeyConstants.BASENAME, I18nKeyConstants.KEY_ERROR));
        }
        return responseData;
    }
}