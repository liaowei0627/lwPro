package com.flyhaze.platform.service;

import java.time.ZoneId;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.flyhaze.core.exception.ApplicationException;
import com.flyhaze.core.page.Pagination;
import com.flyhaze.core.query.Where;
import com.flyhaze.core.query.operator.OneValueComparisonOperator;
import com.flyhaze.core.query.order.OrderBy;
import com.flyhaze.core.query.order.OrderEnum;
import com.flyhaze.platform.vo.UserVo;
import com.flyhaze.test.TestService;
import com.flyhaze.utils.CryptoUtils;
import com.flyhaze.utils.JSONUtils;
import com.google.common.collect.Lists;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserTest extends TestService {

    @Resource(name = "userService")
    private IUserService userService;

    @Test
    public void testTimezone() {
        ZoneId defaultZone = ZoneId.systemDefault();
        log.info(defaultZone.getId());
    }

    @Test
    public void testFind() {
        try {
            UserVo vo = userService.findVo("CE77BDD4409B42F4AE0F8D54E68E6FB5");
            log.info(vo.toString());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    @Test
    public void testAdd() {
        try {
            UserVo vo = new UserVo();
            vo.setUserName("test13");
            vo.setPassword(CryptoUtils.toMD5("test123"));
            vo.setValid(Boolean.TRUE);
            vo.setCreator("admin");
            vo.setReviser("admin");
            vo = userService.addVo(vo);
            log.info(vo.toString());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    @Test
    public void testUpdate() {
        try {
            String json = null;
            UserVo vo = userService.findVo("9E3BAC7DDF5141E592621E50F68618C9");
            json = JSONUtils.objectToJSONString(vo);
            log.info(json);
            vo.setPassword(CryptoUtils.toMD5("test789"));
            userService.updateVo(vo);
            vo = userService.findVo("9E3BAC7DDF5141E592621E50F68618C9");
            json = JSONUtils.objectToJSONString(vo);
            log.info(vo.toString());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    @Test
    public void testFindList() {
        try {
            List<OrderBy> orderBy = Lists.<OrderBy>newArrayList();
            orderBy.add(new OrderBy("orderNum", OrderEnum.ASC));
            Pagination<UserVo> page = new Pagination<UserVo>(orderBy);
            page.setPageable(false);
            Where where = Where.rootWhere("valid", OneValueComparisonOperator.EQ, Boolean.TRUE);
            Pagination<UserVo> pagination = userService.findList(page, where);
            log.info(pagination.toString());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    @Test
    public void testFindPage() {
        try {
            List<OrderBy> orderBy = Lists.<OrderBy>newArrayList();
            orderBy.add(new OrderBy("orderNum", OrderEnum.ASC));
            Pagination<UserVo> page = new Pagination<UserVo>(5, 2, orderBy);
            Where where = Where.rootWhere("valid", OneValueComparisonOperator.EQ, Boolean.TRUE);
            Pagination<UserVo> pagination = userService.findList(page, where);
            log.info(pagination.toString());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    @Test
    public void testDelList() {
        try {
            List<String> pks = Lists.newArrayList();
            pks.add("50826FC2CBFC40EFB4F4E7EAD9337F9F");
            pks.add("754DBC40BA4E4C05A2A3604A61F3E570");
            pks.add("08758B607B8541ACB1FA2C6EC50637BF");
            pks.add("9F6EB231D466453996E8F93FA9B96FF4");
            pks.add("EAD9DF5A37FD4B3A830ED97C8D5A9D2E");
            userService.delList(pks.toArray(new String[pks.size()]));
        } catch (ApplicationException e) {
            log.error(e.getMessage(), e);
        }
    }
}