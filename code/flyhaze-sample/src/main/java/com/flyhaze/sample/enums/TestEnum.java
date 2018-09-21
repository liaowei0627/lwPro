/**
 * sample
 * TestEnum.java
 */
package com.flyhaze.sample.enums;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TestEnum
 *
 * 枚举功能测试
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-26 09:42:20
 * @since jdk1.8
 */
public class TestEnum {
    private static final Logger log = LoggerFactory.getLogger(TestEnum.class);

    @Test
    public void testEnum() {
        log.info(String.valueOf(OrderEnum.ASC.equals(OrderEnum.valueOf("ASC"))));
    }
}
