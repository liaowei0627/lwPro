/**
 * flyhaze-core
 * TestService.java
 */
package com.flyhaze.test;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * TestService
 *
 * 服务层单元测试基类
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-30 18:00:04
 * @since jdk1.8
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath*:/applicationContext.xml",
        "classpath*:/applicationContext-db.xml",
        "classpath*:/applicationContext-i18n.xml"
    })
public abstract class TestService {
}