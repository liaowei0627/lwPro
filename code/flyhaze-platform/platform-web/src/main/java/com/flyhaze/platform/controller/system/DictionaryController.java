/**
 * platform-web
 * DictionaryController.java
 */
package com.flyhaze.platform.controller.system;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.flyhaze.framework.core.exception.ApplicationException;
import com.flyhaze.framework.core.page.Pagination;
import com.flyhaze.framework.core.query.Where;
import com.flyhaze.framework.core.query.exception.DuplicationCodeException;
import com.flyhaze.framework.core.query.operator.NoValueComparisonOperator;
import com.flyhaze.framework.core.query.operator.OneValueComparisonOperator;
import com.flyhaze.framework.core.query.order.OrderBy;
import com.flyhaze.framework.core.query.order.OrderEnum;
import com.flyhaze.framework.mvc.controller.BaseController;
import com.flyhaze.framework.mvc.response.ResponseData;
import com.flyhaze.framework.mvc.view.TreeView;
import com.flyhaze.platform.entity.SysDictionary;
import com.flyhaze.platform.service.IDictionaryService;
import com.flyhaze.platform.view.DictionaryListView;
import com.flyhaze.platform.view.DictionaryView;
import com.flyhaze.platform.vo.DictionaryVo;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;

import lombok.extern.slf4j.Slf4j;

/**
 * DictionaryController
 *
 * 数据字典管理Controller
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-09-16 20:26:38
 * @see com.flyhaze.framework.mvc.controller.BaseController<SysDictionary, DictionaryVo>
 * @since jdk1.8
 */
@Controller
@RequestMapping(path = {"/system/dict"})
@Slf4j
public class DictionaryController extends BaseController<SysDictionary, DictionaryVo> {

    @Resource(name = "dictionaryService")
    private IDictionaryService dictionaryService;

    /**
     * 取得列表
     * 
     * @param page 页号
     * @param rows 每页行数
     * @param parentId 上级菜单id
     * @return
     * @throws ApplicationException
     */
    @RequestMapping(path = {"/list"}, method = RequestMethod.GET)
    @ResponseBody
    public Pagination<DictionaryListView> list(@RequestParam(name = "parentId", required = true) String parentId)
            throws ApplicationException {
        List<OrderBy> orderBy = Lists.<OrderBy>newArrayList();
        orderBy.add(new OrderBy("orderNum", OrderEnum.ASC));
        Where where = null;
        String[] keywords = new String[] {"text"};
        String[] excKey = new String[] {"parentId"};
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
        Pagination<?> p = configPagination();
        Pagination<DictionaryVo> pagination = dictionaryService
                .findList(new Pagination<DictionaryVo>(p.getRows(), p.getPage(), orderBy), where);
        List<DictionaryVo> data = pagination.getData();
        List<DictionaryListView> list = Lists.<DictionaryListView>newArrayList();
        for (DictionaryVo dictionaryVo : data) {
            list.add(new DictionaryListView(dictionaryVo));
        }
        return new Pagination<DictionaryListView>(pagination.getTotal(), pagination.getRows(), pagination.getPage(), list);
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
    public DictionaryView load(@RequestParam(name = "id", required = true) String id,
            @RequestParam(name = "opt", required = false) String opt) throws ApplicationException {
        DictionaryView view = null;
        DictionaryVo vo = dictionaryService.findVo(id);
        if (null != vo) {
            if (OPT_COPY.equals(opt)) {
                vo.setId(null);
            }
            view = new DictionaryView(vo);
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
    public ResponseData<String> save(DictionaryView dictionaryView) throws ApplicationException {
        ResponseData<String> responseData;

        DictionaryVo vo = dictionaryView.toVo();
        configVo(vo);
        try {
            if (Strings.isNullOrEmpty(vo.getId())) {
                dictionaryService.addVo(vo);
            } else {
                dictionaryService.updateVo(vo);
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
        dictionaryService.delList(id);
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
    public ResponseData<List<TreeView<SysDictionary, DictionaryVo>>> tree(@RequestParam(name = "id", required = false) String id,
            @RequestParam(name = "excId", required = false) String excId) throws ApplicationException {
        List<OrderBy> orderBy = Lists.<OrderBy>newArrayList();
        orderBy.add(new OrderBy("orderNum", OrderEnum.ASC));
        Pagination<DictionaryVo> pagination = new Pagination<DictionaryVo>(orderBy);
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
        pagination = dictionaryService.findList(pagination, where);
        List<DictionaryVo> data = pagination.getData();
        List<TreeView<SysDictionary, DictionaryVo>> list = Lists.newArrayList();
        for (DictionaryVo dictionaryVo : data) {
            list.add(new TreeView<SysDictionary, DictionaryVo>(dictionaryVo));
        }
        return new ResponseData<List<TreeView<SysDictionary, DictionaryVo>>>(1, "取得菜单树形数据成功！", list);
    }
}