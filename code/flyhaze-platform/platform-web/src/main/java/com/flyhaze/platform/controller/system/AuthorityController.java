/**
 * platform-web
 * AuthorityController.java
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
import com.flyhaze.platform.entity.SysAuthority;
import com.flyhaze.platform.service.IAuthorityService;
import com.flyhaze.platform.view.AuthorityListView;
import com.flyhaze.platform.view.AuthorityView;
import com.flyhaze.platform.vo.AuthorityVo;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;

import lombok.extern.slf4j.Slf4j;

/**
 * AuthorityController
 *
 * 权限管理Controller
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-09-24 03:32:04
 * @see com.flyhaze.framework.mvc.controller.BaseController<SysAuthority, AuthorityVo>
 * @since jdk1.8
 */
@Controller
@RequestMapping(path = {"/system/auth"})
@Slf4j
public class AuthorityController extends BaseController<SysAuthority, AuthorityVo> {

    @Resource(name = "authorityService")
    private IAuthorityService authorityService;

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
    public Pagination<AuthorityListView> list() {
        Pagination<AuthorityListView> responsePagination;
        try {
            List<OrderBy> orderBy = Lists.<OrderBy>newArrayList();
            orderBy.add(new OrderBy("authCode", OrderEnum.ASC));
            Where where = null;
            String[] keywords = new String[] {"authName"};
            where = configWhere(where, keywords);
            Pagination<?> p = configPagination();
            Pagination<AuthorityVo> pagination = authorityService
                    .findList(new Pagination<AuthorityVo>(p.getRows(), p.getPage(), orderBy), where);
            List<AuthorityVo> data = pagination.getData();
            List<AuthorityListView> list = Lists.<AuthorityListView>newArrayList();
            for (AuthorityVo authorityVo : data) {
                list.add(new AuthorityListView(authorityVo));
            }
            responsePagination = new Pagination<AuthorityListView>(pagination.getTotal(), pagination.getRows(),
                    pagination.getPage(), list);
        } catch (ApplicationException e) {
            log.error(e.getMessage(), e);
            responsePagination = new Pagination<>();
            responsePagination.setData(Lists.<AuthorityListView>newArrayList());
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
    public ResponseData<AuthorityView> load(@RequestParam(name = "id", required = true) String id,
            @RequestParam(name = "opt", required = false) String opt) {
        ResponseData<AuthorityView> responseData;
        try {
            AuthorityVo vo = authorityService.findVo(id);
            if (null != vo) {
                if (OPT_COPY.equals(opt)) {
                    vo.setId(null);
                }
                AuthorityView view = new AuthorityView(vo);
                responseData = ResponseData
                        .<AuthorityView>success(getMessage(I18nKeyConstants.BASENAME, I18nKeyConstants.KEY_LOAD_SUCCESS), view);
            } else {
                responseData = ResponseData.<AuthorityView>otherFailure(2,
                        getMessage(SysI18nKeyConstants.BASENAME, SysI18nKeyConstants.KEY_LOAD_AUTH));
            }
        } catch (ApplicationException e) {
            log.error(e.getMessage(), e);
            responseData = ResponseData.<AuthorityView>failure(getMessage(I18nKeyConstants.BASENAME, I18nKeyConstants.KEY_ERROR));
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
    public ResponseData<String> save(AuthorityView authorityView) {
        ResponseData<String> responseData;

        AuthorityVo vo = authorityView.toVo();
        configVo(vo);
        try {
            AuthorityVo authVo;
            if (Strings.isNullOrEmpty(vo.getId())) {
                authVo = authorityService.addVo(vo);
            } else {
                authVo = authorityService.updateVo(vo);
            }
            if (Strings.isNullOrEmpty(authVo.getMsg())) {
                responseData = ResponseData
                        .<String>success(getMessage(I18nKeyConstants.BASENAME, I18nKeyConstants.KEY_SAVE_SUCCESS));
            } else {
                responseData = ResponseData.<String>otherFailure(2, getMessage(SysI18nKeyConstants.BASENAME, authVo.getMsg()));
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
            String rs = authorityService.delList(id);
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