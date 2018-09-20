/**
 * platform-web
 * SiteController.java
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
import com.liaowei.framework.query.Where;
import com.liaowei.framework.query.exception.DuplicationCodeException;
import com.liaowei.framework.query.order.OrderEnum;
import com.liaowei.framework.response.ResponseData;
import com.liaowei.platform.entity.SysSite;
import com.liaowei.platform.model.SiteModel;
import com.liaowei.platform.service.ISiteService;
import com.liaowei.platform.view.SiteListView;
import com.liaowei.platform.view.SiteView;
import com.liaowei.platform.vo.SiteVo;

import lombok.extern.slf4j.Slf4j;

/**
 * SiteController
 *
 * 站点管理Controller
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-09-14 23:35:53
 * @see com.liaowei.framework.controller.BaseController<SysSite, SiteVo, SiteModel>
 * @since jdk1.8
 */
@Controller
@RequestMapping(path = {"/system/site"})
@Slf4j
public class SiteController extends BaseController<SysSite, SiteVo, SiteModel> {

    @Resource(name = "siteService")
    private ISiteService siteService;

    @Override
    protected SiteModel voToModel(SiteVo v) {
        log.debug("DEBUG：Vo转换Model：" + v.toString());

        SiteModel m = new SiteModel();
        m.copyForVo(v);

        return m;
    }

    /**
     * 取得列表
     * 
     * @param page 页号
     * @param rows 每页行数
     * @param model 查询条件
     * @return
     * @throws ApplicationException
     */
    @RequestMapping(path = {"/list"}, method = RequestMethod.GET)
    @ResponseBody
    public Pagination<SiteListView> list() throws ApplicationException {
        log.debug("DEBUG：查询站点数据列表");
        Map<String, OrderEnum> orderBy = Maps.newHashMap();
        orderBy.put("siteCode", OrderEnum.ASC);
        Where where = null;
        String[] keywords = new String[] {"siteName"};
        where = configWhere(where, keywords);
        Pagination<?> p = configPagination();
        Pagination<SiteVo> pagination = siteService.findList(new Pagination<SiteVo>(p.getRows(), p.getPage(), orderBy), where);
        List<SiteVo> data = pagination.getData();
        List<SiteListView> list = Lists.newArrayList();
        for (SiteVo menuVo : data) {
            list.add(new SiteListView(voToModel(menuVo)));
        }
        return new Pagination<SiteListView>(pagination.getTotal(), pagination.getRows(), pagination.getPage(), list);
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
    public SiteView load(@RequestParam(name = "id", required = true) String id,
            @RequestParam(name = "opt", required = false) String opt) throws ApplicationException {
        log.debug("DEBUG：加载菜单详情，id=" + id);
        SiteView view = null;
        SiteVo vo = siteService.findVo(id);
        if (null != vo) {
            if (OPT_COPY.equals(opt)) {
                vo.setId(null);
            }
            view = new SiteView(voToModel(vo));
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
    public ResponseData<String> save(SiteView site) throws ApplicationException {
        log.debug("DEBUG：保存站点，site=" + site.toString());

        ResponseData<String> responseData;
        SiteModel model = site.toModel();
        configModel(model);
        SiteVo vo = model.copyToVo();
        try {
            if (Strings.isNullOrEmpty(vo.getId())) {
                siteService.addVo(vo);
            } else {
                siteService.updateVo(vo);
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

        siteService.delList(id);

        return new ResponseData<String>(1, "删除成功！");
    }
}