/**
 * platform-web
 * SiteController.java
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
import com.flyhaze.platform.entity.SysSite;
import com.flyhaze.platform.service.ISiteService;
import com.flyhaze.platform.view.SiteListView;
import com.flyhaze.platform.view.SiteView;
import com.flyhaze.platform.vo.SiteVo;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;

import lombok.extern.slf4j.Slf4j;

/**
 * SiteController
 *
 * 站点管理Controller
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-09-14 23:35:53
 * @see com.flyhaze.framework.mvc.controller.BaseController<SysSite, SiteVo>
 * @since jdk1.8
 */
@Controller
@RequestMapping(path = {"/system/site"})
@Slf4j
public class SiteController extends BaseController<SysSite, SiteVo> {

    @Resource(name = "siteService")
    private ISiteService siteService;

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
    public Pagination<SiteListView> list() {
        Pagination<SiteListView> responsePagination;
        try {
            List<OrderBy> orderBy = Lists.<OrderBy>newArrayList();
            orderBy.add(new OrderBy("siteCode", OrderEnum.ASC));
            Where where = null;
            String[] keywords = new String[] {"siteName"};
            where = configWhere(where, keywords);
            Pagination<?> p = configPagination();
            Pagination<SiteVo> pagination = siteService.findList(new Pagination<SiteVo>(p.getRows(), p.getPage(), orderBy),
                    where);
            List<SiteVo> data = pagination.getData();
            List<SiteListView> list = Lists.<SiteListView>newArrayList();
            for (SiteVo siteVo : data) {
                list.add(new SiteListView(siteVo));
            }
            responsePagination = new Pagination<SiteListView>(pagination.getTotal(), pagination.getRows(), pagination.getPage(),
                    list);
        } catch (ApplicationException e) {
            log.error(e.getMessage(), e);
            responsePagination = new Pagination<>();
            responsePagination.setData(Lists.<SiteListView>newArrayList());
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
    public ResponseData<SiteView> load(@RequestParam(name = "id", required = true) String id,
            @RequestParam(name = "opt", required = false) String opt) {
        ResponseData<SiteView> responseData;
        try {
            SiteVo vo = siteService.findVo(id);
            if (null != vo) {
                if (OPT_COPY.equals(opt)) {
                    vo.setId(null);
                }
                SiteView view = new SiteView(vo);
                responseData = ResponseData
                        .<SiteView>success(getMessage(I18nKeyConstants.BASENAME, I18nKeyConstants.KEY_LOAD_SUCCESS), view);
            } else {
                responseData = ResponseData.<SiteView>otherFailure(2,
                        getMessage(SysI18nKeyConstants.BASENAME, SysI18nKeyConstants.KEY_LOAD_SITE));
            }
        } catch (ApplicationException e) {
            log.error(e.getMessage(), e);
            responseData = ResponseData.<SiteView>failure(getMessage(I18nKeyConstants.BASENAME, I18nKeyConstants.KEY_ERROR));
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
    public ResponseData<String> save(SiteView siteView) {
        ResponseData<String> responseData;

        SiteVo vo = siteView.toVo();
        configVo(vo);
        try {
            SiteVo siteVo;
            if (Strings.isNullOrEmpty(vo.getId())) {
                siteVo = siteService.addVo(vo);
            } else {
                siteVo = siteService.updateVo(vo);
            }
            if (Strings.isNullOrEmpty(siteVo.getMsg())) {
                responseData = ResponseData
                        .<String>success(getMessage(I18nKeyConstants.BASENAME, I18nKeyConstants.KEY_SAVE_SUCCESS));
            } else {
                responseData = ResponseData.<String>otherFailure(2, getMessage(SysI18nKeyConstants.BASENAME, siteVo.getMsg()));
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
            String rs = siteService.delList(id);
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