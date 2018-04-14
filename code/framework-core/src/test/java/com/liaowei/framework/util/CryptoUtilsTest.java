/**
 * framework-core
 * CryptoUtilsTest.java
 */
package com.liaowei.framework.util;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * CryptoUtilsTest
 *
 * 密码字段的处理
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-12 22:47:01
 * @since jdk1.8
 */
public class CryptoUtilsTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(CryptoUtilsTest.class);

    @Test
    public void testEnCrypto() {
        try {
            String pwd = "admin123";
            String seed = "123456";

            String pwdCiphertext = CryptoUtils.toMD5(pwd);
            LOGGER.info(pwdCiphertext);
            pwdCiphertext = CryptoUtils.toMD5(pwdCiphertext + seed);
            LOGGER.info(pwdCiphertext);
            pwdCiphertext = CryptoUtils.base64Encoder(pwdCiphertext);
            LOGGER.info(pwdCiphertext);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @Test
    public void testDeCrypto() {
        try {
            String pwd = "51304D35516A51784E7A593151305245516A41304D444E474E544D33526B5A4551545243516A42474E7A673D";

            String pwdCiphertext = CryptoUtils.base64Deconder(pwd);
            LOGGER.info(pwdCiphertext);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}