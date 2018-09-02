/**
 * platform-web
 * MenuTest.java
 */
package com.liaowei.platform.controller;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.result.StatusResultMatchers;

import com.liaowei.framework.test.TestController;
import com.liaowei.framework.util.JSONUtils;

/**
 * MenuTest
 *
 * 菜单管理Controller测试用例
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-30 20:13:35
 * @see com.liaowei.framework.test.TestController
 * @since jdk1.8
 */
public class MenuTest extends TestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MenuTest.class);

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
            LOGGER.info("add is：" + map.get("msg"));
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}