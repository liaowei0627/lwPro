/**
 * platform-web
 * TestLombok.java
 */
package com.liaowei.platform.test;

import java.time.LocalDateTime;

import org.junit.Test;

import com.liaowei.platform.model.UserModel;

import lombok.extern.slf4j.Slf4j;

/**
 * TestLombok
 *
 * Lombok测试用例
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-18 20:38:37
 * @since jdk1.8
 */
@Slf4j
public class TestLombok {

    @Test
    public void testToString() {
        UserModel um = new UserModel("id123", "un456", "pwd789", Boolean.TRUE, "creator0", LocalDateTime.now(), "reviser0", LocalDateTime.now());
        log.info(um.toString());
    }
}