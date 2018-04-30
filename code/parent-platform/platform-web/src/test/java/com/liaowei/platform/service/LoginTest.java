/**
 * platform-web
 * LoginTest.java
 */
package com.liaowei.platform.service;

import javax.annotation.Resource;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.liaowei.framework.test.TestService;
import com.liaowei.platform.vo.UserVo;

/**
 * LoginTest
 *
 * service层JUnit测试
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-14 17:59:26
 * @since jdk1.8
 */
public class LoginTest extends TestService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginTest.class);

    @Resource(name = "loginService")
    private ILoginService loginService;

    @Test
    public void testLogin() {
        try {
            UserVo user = loginService.findByUserName("test13");
            LOGGER.debug(user.toString());
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}