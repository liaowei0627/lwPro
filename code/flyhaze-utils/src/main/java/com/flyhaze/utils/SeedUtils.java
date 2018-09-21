/**
 * framework-mvc
 * SeedUtils.java
 */
package com.flyhaze.utils;

/**
 * SeedUtils
 *
 * 各种加密种子生成工具
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-09 22:47:19
 * @since jdk1.8
 */
public class SeedUtils {

    private SeedUtils() {
    }

    /**
     * 获取指定位数的随机字符串(包含小写字母、大写字母、数字, 0 < length)
     * @param length 位数
     * @return
     */
    public static String getRandomString(int length) {

        String rs = null;
        if (0 < length) {
            // 随机字符串的随机字符库
            String KeyString = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
            StringBuffer sb = new StringBuffer();
            int len = KeyString.length();
            for (int i = 0; i < length; i++) {
                sb.append(KeyString.charAt((int) Math.round(Math.random() * (len - 1))));
            }
            rs = sb.toString();
        }

        return rs;
    }
}