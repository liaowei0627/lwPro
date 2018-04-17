/**
 * study-web
 * SysUserTest.java
 */
package com.liaowei.platform.service;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.liaowei.framework.core.exception.ApplicationException;
import com.liaowei.framework.util.JSONUtils;
import com.liaowei.platform.vo.UserVo;

/**
 * SysUserTest
 *
 * service层JUnit测试
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-14 17:59:26
 * @since jdk1.8
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/applicationContext*.xml" })
public class SysUserTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(SysUserTest.class);

    @Resource(name = "loginService")
    private ILoginService loginService;

    @Test
    public void testLogin() {
        try {
            UserVo user = loginService.findByUserName("admin");
            String json = JSONUtils.objectToJSONString(user);
            LOGGER.debug(json);
        } catch (JsonProcessingException | ApplicationException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}