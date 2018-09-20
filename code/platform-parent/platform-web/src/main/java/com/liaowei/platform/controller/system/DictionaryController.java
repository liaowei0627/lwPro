/**
 * platform-web
 * DictionaryController.java
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
import com.liaowei.framework.query.operator.NoValueComparisonOperator;
import com.liaowei.framework.query.operator.OneValueComparisonOperator;
import com.liaowei.framework.query.order.OrderEnum;
import com.liaowei.framework.response.ResponseData;
import com.liaowei.framework.view.TreeView;
import com.liaowei.platform.entity.SysDictionary;
import com.liaowei.platform.model.DictionaryModel;
import com.liaowei.platform.service.IDictionaryService;
import com.liaowei.platform.view.DictionaryListView;
import com.liaowei.platform.view.DictionaryView;
import com.liaowei.platform.vo.DictionaryVo;

import lombok.extern.slf4j.Slf4j;

/**
 * DictionaryController
 *
 * 数据字典管理Controller
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-09-16 20:26:38
 * @see com.liaowei.framework.controller.BaseController<SysDictionary, DictionaryVo, DictionaryModel>
 * @since jdk1.8
 */
@Controller
@RequestMapping(path = {"/system/dict"})
@Slf4j
public class DictionaryController extends BaseController<SysDictionary, DictionaryVo, DictionaryModel> {

    @Resource(name = "dictionaryService")
    private IDictionaryService dictionaryService;

    @Override
    protected DictionaryModel voToModel(DictionaryVo v) {
        log.debug("DEBUG：Vo转换Model：" + v.toString());

        DictionaryModel m = new DictionaryModel();
        m.copyForVo(v);

        return m;
    }

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
        log.debug("DEBUG：查询菜单数据列表，parentId=" + parentId);
        Map<String, OrderEnum> orderBy = Maps.newHashMap();
        orderBy.put("orderNum", OrderEnum.ASC);
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
            list.add(new DictionaryListView(voToModel(dictionaryVo)));
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
        log.debug("DEBUG：加载菜单详情，id=" + id);
        DictionaryView view = null;
        DictionaryVo vo = dictionaryService.findVo(id);
        if (null != vo) {
            if (OPT_COPY.equals(opt)) {
                vo.setId(null);
            }
            view = new DictionaryView(voToModel(vo));
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
        log.debug("DEBUG：保存菜单，dictionaryView=" + dictionaryView.toString());

        ResponseData<String> responseData;
        DictionaryModel model = dictionaryView.toModel();
        configModel(model);
        DictionaryVo vo = model.copyToVo();
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
        log.debug("DEBUG：删除菜单，del=" + id.toString());

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
    public ResponseData<List<TreeView<SysDictionary, DictionaryVo, DictionaryModel>>> tree(
            @RequestParam(name = "id", required = false) String id, @RequestParam(name = "excId", required = false) String excId)
            throws ApplicationException {
        log.debug("DEBUG：取得菜单树，parentId=" + id);
        Map<String, OrderEnum> orderBy = Maps.newHashMap();
        orderBy.put("orderNum", OrderEnum.ASC);
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
        List<TreeView<SysDictionary, DictionaryVo, DictionaryModel>> list = Lists.newArrayList();
        for (DictionaryVo dictionaryVo : data) {
            list.add(new TreeView<SysDictionary, DictionaryVo, DictionaryModel>(voToModel(dictionaryVo)));
        }
        return new ResponseData<List<TreeView<SysDictionary, DictionaryVo, DictionaryModel>>>(1, "取得菜单树形数据成功！", list);
    }
}