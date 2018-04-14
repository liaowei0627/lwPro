/**
 * study-web
 * SysUserTest.java
 */
package com.liaowei.study.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.result.StatusResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.liaowei.framework.util.CryptoUtils;
import com.liaowei.framework.util.JSONUtils;

/**
 * SysUserTest
 *
 * controller层JUnit测试
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-14 17:59:06
 * @since jdk1.8
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:/spring-mvc.xml", "classpath*:**/applicationContext*.xml"})
public class SysUserTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(SysUserTest.class);

    @Resource
    private WebApplicationContext wac;
    private MockMvc mockMvc;

    @Before  
    public void setup() {   
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();  
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testLogin() {
        try {
            String userName = "admin";
            String password = "admin123";

            StatusResultMatchers matchers = MockMvcResultMatchers.status();
            ResultMatcher matcher = matchers.isOk();
            MockHttpServletResponse response;
            String result;
            Map<String, String> map;

            MockHttpServletRequestBuilder seedPost = MockMvcRequestBuilders.post("/seed");
            ResultActions seedActions = mockMvc.perform(seedPost);
            seedActions.andExpect(matcher);
            MvcResult seedResult = seedActions.andReturn();
            response = seedResult.getResponse();
            result = response.getContentAsString();
            map = JSONUtils.JSONStringToObject(result, HashMap.class);
            String seed = map.get("data");
            LOGGER.info("seed is：" + seed);

            String pwdCiphertext = CryptoUtils.toMD5(password);
            pwdCiphertext = CryptoUtils.toMD5(pwdCiphertext.concat(seed));
            pwdCiphertext = CryptoUtils.base64Encoder(pwdCiphertext);
            LOGGER.info("pwdCiphertext is：" + pwdCiphertext);

            MockHttpServletRequestBuilder loginPost = MockMvcRequestBuilders.post("/login");
            loginPost.param("userName", userName);
            loginPost.param("password", pwdCiphertext);
            ResultActions loginActions = mockMvc.perform(loginPost);
            loginActions.andExpect(matcher);
            MvcResult loginResult = loginActions.andReturn();
            response = loginResult.getResponse();
            result = response.getContentAsString();
            map = JSONUtils.JSONStringToObject(result, HashMap.class);
            LOGGER.info("login is：" + map.get("msg"));
            
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}