/**
 * service-framework
 * MD5Utils.java
 */
package com.liaowei.framework.util;

import java.security.MessageDigest;

/**
 * MD5Utils
 *
 * MD5工具类
 *
 * @author liaowei
 * @date 创建时间：2018年3月31日 下午12:03:46 
 * @since jdk1.8
 */
public class MD5Utils {

    private MD5Utils() {
    }

    /**
     * 对字符串进行MD5编码
     * 
     * @param s 需要MD5编码的字符串
     * @return 编码后的16进制字符串
     */
    public static String MD5(String s) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] bytes = md.digest(s.getBytes("utf-8"));
            return toHex(bytes);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 字节数组转16进制字符串
     * 
     * @param bytes 需要转码的字节数组
     * @return 转码后的16进制字符串
     */
    private static String toHex(byte[] bytes) {

        final char[] HEX_DIGITS = "0123456789ABCDEF".toCharArray();
        StringBuilder ret = new StringBuilder(bytes.length * 2);
        for (int i = 0; i < bytes.length; i++) {
            ret.append(HEX_DIGITS[(bytes[i] >> 4) & 0x0f]);
            ret.append(HEX_DIGITS[bytes[i] & 0x0f]);
        }
        return ret.toString();
    }
}
