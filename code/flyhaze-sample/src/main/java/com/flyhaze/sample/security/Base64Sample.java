/**
 * sample
 * Base64Sample.java
 */
package com.flyhaze.sample.security;

import java.nio.charset.Charset;
import java.util.Base64;
import java.util.Base64.Encoder;

/**
 * Base64Sample
 *
 * TODO
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-11 23:08:27
 * @see TODO
 * @since jdk1.8
 */
public class Base64Sample {

    public static void main(String[] args) {
        Encoder encoder = Base64.getEncoder();
        byte[] code = encoder.encode("abcde".getBytes(Charset.forName("UTF-8")));
        System.out.println(toHex(code));
    }

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