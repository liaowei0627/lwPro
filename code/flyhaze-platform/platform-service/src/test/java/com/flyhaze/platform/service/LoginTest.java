/**
 * platform-web
 * LoginTest.java
 */
package com.flyhaze.platform.service;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.flyhaze.framework.test.TestService;
import com.flyhaze.platform.vo.MenuVo;
import com.flyhaze.platform.vo.UserVo;
import com.flyhaze.utils.JSONUtils;

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
    public void findUserByUserName() {
        try {
            UserVo user = loginService.findUserByUserName("admin");
            log.info(user.toString());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    @Test
    public void findSysMenusByUserId() {
        try {
            List<MenuVo> list = loginService.findSysMenusByUserId("E8B470073D4F41A2AA0B1B79DBAE598E", null, true);
            log.info(JSONUtils.objectToJSONString(list));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}