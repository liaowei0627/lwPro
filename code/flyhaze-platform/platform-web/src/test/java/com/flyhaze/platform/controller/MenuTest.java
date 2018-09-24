/**
 * platform-web
 * MenuTest.java
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
import com.flyhaze.utils.JSONUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * MenuTest
 *
 * 菜单管理Controller测试用例
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-30 20:13:35
 * @see com.flyhaze.framework.test.TestController
 * @since jdk1.8
 */
@Slf4j
public class MenuTest extends TestController {

    @Test
    public void testAdd() {
        try {
            StatusResultMatchers matchers = MockMvcResultMatchers.status();
            ResultMatcher matcher = matchers.isOk();
            MockHttpServletResponse response;
            String result;

            MockHttpServletRequestBuilder loginPost = MockMvcRequestBuilders.post("/sys/menu/save");
            loginPost.param("id", "");
            loginPost.param("code", "SUB_SYS_SYSTEM");
            loginPost.param("menuText", "菜单管理");
            loginPost.param("menuUrl", "");
            loginPost.param("menuType", "SYSTEM");
            loginPost.param("parentId", "");
            loginPost.param("orderNum", "999");
            ResultActions loginActions = mockMvc.perform(loginPost);
            loginActions.andExpect(matcher);
            MvcResult loginResult = loginActions.andReturn();
            response = loginResult.getResponse();
            result = response.getContentAsString();
            @SuppressWarnings("unchecked")
            Map<String, String> map = JSONUtils.JSONStringToObject(result, HashMap.class);
            log.info("INFO：add is：{}", map.get("msg"));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}