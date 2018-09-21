/**
 * platform-service
 * TestEnumJson.java
 */
package com.flyhaze.platform.test.enums;

import org.junit.Test;

import com.flyhaze.platform.enums.MenuTypeEnum;

import lombok.extern.slf4j.Slf4j;

/**
 * TestEnumJson
 *
 * 枚举转json测试类
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-22 20:41:46
 * @since jdk1.8
 */
@Slf4j
public class TestEnumJson {

    @Test
    public void testEnumJson() {
        try {
            log.info(MenuTypeEnum.BUTTON.toString());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    @Test
    public void testEnumArray() {
        try {
            MenuTypeEnum[] menuTypes = MenuTypeEnum.values();
            for (MenuTypeEnum menuTypeEnum : menuTypes) {
                log.info(menuTypeEnum.toString());
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
