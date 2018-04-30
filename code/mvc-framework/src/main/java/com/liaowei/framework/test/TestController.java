/**
 * framework-core
 * TestController.java
 */
package com.liaowei.framework.test;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * TestController
 *
 * TODO
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-30 17:56:21
 * @see TODO
 * @since jdk1.8
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:/spring-mvc.xml", "classpath*:**/applicationContext*.xml"})
public abstract class TestController {

    @Resource
    protected WebApplicationContext wac;
    protected MockMvc mockMvc;

    @Before  
    public void setup() {   
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();  
    }
}
