/**
 * framework-core
 * TestController.java
 */
package com.liaowei.framework.test;

import java.util.UUID;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.liaowei.framework.SessionUser;

/**
 * TestController
 *
 * Spring MVC Controller 测试用例超类
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-30 17:56:21
 * @since jdk1.8
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:/spring-mvc.xml", "classpath*:**/applicationContext*.xml"})
public abstract class TestController {

    @Resource
    protected WebApplicationContext wac;
    protected MockMvc mockMvc;
    protected MockHttpSession mockSession;

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Before  
    public void setup() {   
        mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
        mockSession = new MockHttpSession(wac.getServletContext(), UUID.randomUUID().toString());
        mockSession.setAttribute("USER_SESSION_KEY", new SessionUser("E8B470073D4F41A2AA0B1B79DBAE598E", "admin", "001", null));
    }
}
