package com.liaowei.study.test;

import java.time.ZoneId;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.common.collect.Lists;
import com.liaowei.framework.core.exception.ApplicationException;
import com.liaowei.framework.page.Pagination;
import com.liaowei.framework.util.JSONUtils;
import com.liaowei.framework.util.MD5Utils;
import com.liaowei.study.service.ISysUserService;
import com.liaowei.study.vo.UserVo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/applicationContext*.xml" })
public class SysUserTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(SysUserTest.class);

    @Resource(name = "sysUserService")
    private ISysUserService sysUserService;

    @Test
    public void testTimezone() {
        ZoneId defaultZone = ZoneId.systemDefault();
        LOGGER.debug(defaultZone.getId());
    }

    @Test
    public void testFind() {
        try {
            String json = null;
            UserVo vo = sysUserService.findVo("CE77BDD4409B42F4AE0F8D54E68E6FB5");
            json = JSONUtils.objectToJSONString(vo);
            LOGGER.debug(json);
        } catch (JsonProcessingException | ApplicationException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @Test
    public void testAdd() {
        try {
            UserVo vo = new UserVo();
            vo.setUserName("test12");
            vo.setPassword(MD5Utils.MD5("test123"));
            vo.setValid(Boolean.TRUE);
            vo.setCreator("admin");
            vo.setReviser("admin");
            vo = sysUserService.addVo(vo);
            String json = JSONUtils.objectToJSONString(vo);
            LOGGER.debug(json);
        } catch (JsonProcessingException | ApplicationException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @Test
    public void testUpdate() {
        try {
            String json = null;
            UserVo vo = sysUserService.findVo("9E3BAC7DDF5141E592621E50F68618C9");
            json = JSONUtils.objectToJSONString(vo);
            LOGGER.debug(json);
            vo.setPassword(MD5Utils.MD5("test789"));
            sysUserService.updateVo(vo);
            vo = sysUserService.findVo("9E3BAC7DDF5141E592621E50F68618C9");
            json = JSONUtils.objectToJSONString(vo);
            LOGGER.debug(json);
        } catch (JsonProcessingException | ApplicationException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @Test
    public void testFindList() {
        try {
            UserVo vo = new UserVo();
            vo.setValid(true);
            List<UserVo> list = sysUserService.findList(vo);
            String json = JSONUtils.objectToJSONString(list);
            LOGGER.debug(json);
        } catch (JsonProcessingException | ApplicationException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @Test
    public void testFindPage() {
        try {
            UserVo vo = new UserVo();
            vo.setValid(true);
            Pagination<UserVo> page = new Pagination<UserVo>();
            page.setPageSize(5);
            page.setPageNumber(2);
            Pagination<UserVo> list = sysUserService.findPage(page, vo);
            String json = JSONUtils.objectToJSONString(list);
            LOGGER.debug(json);
        } catch (JsonProcessingException | ApplicationException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @Test
    public void testDel() {
        try {
            sysUserService.delOne("9E3BAC7DDF5141E592621E50F68618C9");
        } catch (ApplicationException e) {
            LOGGER.error(e.getMessage(), e);
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
            sysUserService.delList(pks);
        } catch (ApplicationException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}