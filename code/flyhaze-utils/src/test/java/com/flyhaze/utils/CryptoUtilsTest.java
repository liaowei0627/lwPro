/**
 * flyhaze-framework
 * CryptoUtilsTest.java
 */
package com.flyhaze.utils;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

/**
 * CryptoUtilsTest
 *
 * 密码字段的处理
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-12 22:47:01
 * @since jdk1.8
 */
@Slf4j
public class CryptoUtilsTest {

    @Test
    public void testEnCrypto() {
        try {
            String pwd = "admin123";
            String seed = "123456";

            String pwdCiphertext = CryptoUtils.toMD5(pwd);
            log.info(pwdCiphertext);
            pwdCiphertext = CryptoUtils.toMD5(pwdCiphertext + seed);
            log.info(pwdCiphertext);
            pwdCiphertext = CryptoUtils.base64Encoder(pwdCiphertext);
            log.info(pwdCiphertext);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    @Test
    public void testDeCrypto() {
        try {
            String pwd = "51304D35516A51784E7A593151305245516A41304D444E474E544D33526B5A4551545243516A42474E7A673D";

            String pwdCiphertext = CryptoUtils.base64Deconder(pwd);
            log.info(pwdCiphertext);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}