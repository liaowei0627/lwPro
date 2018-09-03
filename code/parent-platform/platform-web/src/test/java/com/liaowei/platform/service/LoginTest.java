/**
 * platform-web
 * LoginTest.java
 */
package com.liaowei.platform.service;

import javax.annotation.Resource;

import org.junit.Test;

import com.liaowei.framework.test.TestService;
import com.liaowei.platform.vo.UserVo;

import lombok.extern.slf4j.Slf4j;

/**
 * LoginTest
 *
 * service层JUnit测试
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-14 17:59:26
 * @since jdk1.8
 */
@Slf4j
public class LoginTest extends TestService {

    @Resource(name = "loginService")
    private ILoginService loginService;

    @Test
    public void testLogin() {
        try {
            UserVo user = loginService.findByUserName("test13");
            log.debug(user.toString());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}