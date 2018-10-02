/**
 * platform-web
 * UserController.java
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
import com.flyhaze.platform.entity.SysUser;
import com.flyhaze.platform.service.IUserService;
import com.flyhaze.platform.view.UserListView;
import com.flyhaze.platform.view.UserView;
import com.flyhaze.platform.vo.UserVo;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;

import lombok.extern.slf4j.Slf4j;

/**
 * UserController
 *
 * 用户管理Controller
 *
 * @useror 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-09-26 06:00:54
 * @see com.flyhaze.framework.mvc.controller.BaseController<SysUser, UserVo>
 * @since jdk1.8
 */
@Controller
@RequestMapping(path = {"/system/user"})
@Slf4j
public class UserController extends BaseController<SysUser, UserVo> {

    @Resource(name = "userService")
    private IUserService userService;

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
    public Pagination<UserListView> list() {
        Pagination<UserListView> responsePagination;
        try {
            List<OrderBy> orderBy = Lists.<OrderBy>newArrayList();
            orderBy.add(new OrderBy("createTime", OrderEnum.DESC));
            Where where = null;
            String[] keywords = new String[] {"userName"};
            where = configWhere(where, keywords);
            Pagination<?> p = configPagination();
            Pagination<UserVo> pagination = userService
                    .findList(new Pagination<UserVo>(p.getRows(), p.getPage(), orderBy), where);
            List<UserVo> data = pagination.getData();
            List<UserListView> list = Lists.<UserListView>newArrayList();
            for (UserVo userVo : data) {
                list.add(new UserListView(userVo));
            }
            responsePagination = new Pagination<UserListView>(pagination.getTotal(), pagination.getRows(),
                    pagination.getPage(), list);
        } catch (ApplicationException e) {
            log.error(e.getMessage(), e);
            responsePagination = new Pagination<>();
            responsePagination.setData(Lists.<UserListView>newArrayList());
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
    public ResponseData<UserView> load(@RequestParam(name = "id", required = true) String id,
            @RequestParam(name = "opt", required = false) String opt) {
        ResponseData<UserView> responseData;
        try {
            UserVo vo = userService.findVo(id);
            if (null != vo) {
                if (OPT_COPY.equals(opt)) {
                    vo.setId(null);
                }
                UserView view = new UserView(vo);
                responseData = ResponseData
                        .<UserView>success(getMessage(I18nKeyConstants.BASENAME, I18nKeyConstants.KEY_LOAD_SUCCESS), view);
            } else {
                responseData = ResponseData.<UserView>otherFailure(2,
                        getMessage(SysI18nKeyConstants.BASENAME, SysI18nKeyConstants.KEY_LOAD_USER));
            }
        } catch (ApplicationException e) {
            log.error(e.getMessage(), e);
            responseData = ResponseData.<UserView>failure(getMessage(I18nKeyConstants.BASENAME, I18nKeyConstants.KEY_ERROR));
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
    public ResponseData<String> save(UserView userView) {
        ResponseData<String> responseData;

        UserVo vo = userView.toVo();
        configVo(vo);
        try {
            UserVo userVo;
            if (Strings.isNullOrEmpty(vo.getId())) {
                userVo = userService.addVo(vo);
            } else {
                userVo = userService.updateVo(vo);
            }
            if (Strings.isNullOrEmpty(userVo.getMsg())) {
                responseData = ResponseData
                        .<String>success(getMessage(I18nKeyConstants.BASENAME, I18nKeyConstants.KEY_SAVE_SUCCESS));
            } else {
                responseData = ResponseData.<String>otherFailure(2, getMessage(SysI18nKeyConstants.BASENAME, userVo.getMsg()));
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
            String rs = userService.delList(id);
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