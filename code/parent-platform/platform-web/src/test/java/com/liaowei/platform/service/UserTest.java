package com.liaowei.platform.service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.time.ZoneId;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.common.collect.Lists;
import com.liaowei.framework.core.exception.ApplicationException;
import com.liaowei.framework.page.Pagination;
import com.liaowei.framework.test.TestService;
import com.liaowei.framework.util.CryptoUtils;
import com.liaowei.framework.util.JSONUtils;
import com.liaowei.platform.vo.UserVo;

public class UserTest extends TestService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserTest.class);

    @Resource(name = "userService")
    private IUserService userService;

    @Test
    public void testTimezone() {
        ZoneId defaultZone = ZoneId.systemDefault();
        LOGGER.debug(defaultZone.getId());
    }

    @Test
    public void testFind() {
        try {
            String json = null;
            UserVo vo = userService.findVo("CE77BDD4409B42F4AE0F8D54E68E6FB5");
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
            vo.setUserName("test13");
            vo.setPassword(CryptoUtils.toMD5("test123"));
            vo.setValid(Boolean.TRUE);
            vo.setCreator("admin");
            vo.setReviser("admin");
            vo = userService.addVo(vo);
            LOGGER.debug(vo.toString());
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @Test
    public void testUpdate() {
        try {
            String json = null;
            UserVo vo = userService.findVo("9E3BAC7DDF5141E592621E50F68618C9");
            json = JSONUtils.objectToJSONString(vo);
            LOGGER.debug(json);
            vo.setPassword(CryptoUtils.toMD5("test789"));
            userService.updateVo(vo);
            vo = userService.findVo("9E3BAC7DDF5141E592621E50F68618C9");
            json = JSONUtils.objectToJSONString(vo);
            LOGGER.debug(json);
        } catch (JsonProcessingException | ApplicationException | NoSuchAlgorithmException | UnsupportedEncodingException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @Test
    public void testFindList() {
        try {
            UserVo vo = new UserVo();
            vo.setValid(true);
            List<UserVo> list = userService.findList(vo, null);
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
            page.setRows(5);
            page.setPage(2);
            Pagination<UserVo> list = userService.findPage(page, vo, null);
            String json = JSONUtils.objectToJSONString(list);
            LOGGER.debug(json);
        } catch (JsonProcessingException | ApplicationException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @Test
    public void testDel() {
        try {
            userService.delOne("9E3BAC7DDF5141E592621E50F68618C9");
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
            userService.delList(pks);
        } catch (ApplicationException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}