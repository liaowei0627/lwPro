/**
 * platform-web
 * SysUserTest.java
 */
package com.flyhaze.platform.controller;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.result.StatusResultMatchers;

import com.flyhaze.framework.test.TestController;
import com.flyhaze.utils.CryptoUtils;
import com.flyhaze.utils.JSONUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * UserTest
 *
 * controller层JUnit测试
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-14 17:59:06
 * @since jdk1.8
 */
@Slf4j
public class LoginTest extends TestController {

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

            MockHttpServletRequestBuilder seedGet = MockMvcRequestBuilders.get("/seed");
            ResultActions seedActions = mockMvc.perform(seedGet);
            seedActions.andExpect(matcher);
            MvcResult seedResult = seedActions.andReturn();
            response = seedResult.getResponse();
            result = response.getContentAsString();
            map = JSONUtils.JSONStringToObject(result, HashMap.class);
            String seed = map.get("data");
            log.info("INFO：seed is：" + seed);

            String pwdCiphertext = CryptoUtils.toMD5(password);
            pwdCiphertext = CryptoUtils.toMD5(pwdCiphertext.concat(seed));
            pwdCiphertext = CryptoUtils.base64Encoder(pwdCiphertext);
            log.info("INFO：pwdCiphertext is：" + pwdCiphertext);

            MockHttpServletRequestBuilder loginPost = MockMvcRequestBuilders.post("/login");
            loginPost.param("userName", userName);
            loginPost.param("password", pwdCiphertext);
            ResultActions loginActions = mockMvc.perform(loginPost);
            loginActions.andExpect(matcher);
            MvcResult loginResult = loginActions.andReturn();
            response = loginResult.getResponse();
            result = response.getContentAsString();
            map = JSONUtils.JSONStringToObject(result, HashMap.class);
            log.info("INFO：login is：" + map.get("msg"));
            
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}