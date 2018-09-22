/**
 * flyhaze-framework
 * CryptoUtils.java
 */
package com.flyhaze.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

import lombok.extern.slf4j.Slf4j;

/**
 * CryptoUtils
 *
 * 加密解密工具类
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-12 19:57:25
 * @since jdk1.8
 */
@Slf4j
public class CryptoUtils {

    private CryptoUtils() {}

    /**
     * 对字符串进行MD5编码
     * 
     * @param s 需要MD5编码的字符串
     * @return 编码后的16进制字符串
     * @throws NoSuchAlgorithmException 
     * @throws UnsupportedEncodingException 
     */
    public static String toMD5(String s) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        log.debug("DEBUG：MD5编码：" + s);
        return toHexString(encrypt(s, "MD5"));
    }

    /**
     * 对字符串单向加密（不可逆加密）编码
     * 
     * @param s 要编码的字符串
     * @param algorithm 编码算法：MD2/MD5/SHA-1/SHA-224/SHA-256/SHA-384/SHA-512
     * @return
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
    public static byte[] encrypt(String s, String algorithm) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        log.debug("DEBUG：对字符串单向加密（不可逆加密）编码：" + s);

        MessageDigest md = MessageDigest.getInstance(algorithm);
        byte[] bytes = md.digest(s.getBytes("UTF-8"));

        return bytes;
    }

    /**
     * 将字符串进行Base64编码
     * 
     * @param s 需要编码的字符串
     * @return 编码后的16进制字符串
     * @throws UnsupportedEncodingException
     */
    public static String base64Encoder(String s) throws UnsupportedEncodingException {
        log.debug("DEBUG：将字符串进行Base64编码：" + s);

        Encoder encoder = Base64.getEncoder();
        byte[] bytes = encoder.encode(s.getBytes("UTF-8"));

        return toHexString(bytes);
    }

    /**
     * 将Base64密文解码
     * 
     * @param ciphertext 需要解码的字符串密文
     * @return 解码后的字符串明文
     */
    public static String base64Deconder(String ciphertext) {
        log.debug("DEBUG：将Base64密文解码：" + ciphertext);

        Decoder decoder = Base64.getDecoder();
        byte[] bytes = decoder.decode(hexToBytes(ciphertext));

        return new String(bytes);
    }

    /**
     * 将byte[]转换为16进制字符串
     * 字母位全大写
     * 
     * @param bytes
     * @return
     */
    public static String toHexString(byte[] bytes) {
        log.debug("DEBUG：将byte[]转换为16进制字符串：" + Arrays.toString(bytes));

        StringBuilder buf = new StringBuilder(bytes.length * 2);
        for (byte b : bytes) { // 使用String的format方法进行转换
            buf.append(String.format("%02x", new Integer(b & 0xff)));
        }

        return buf.toString().toUpperCase();
    }

    /**
     * 将16进制字符串转换为byte[]
     * 
     * @param str
     * @return
     */
    public static byte[] hexToBytes(String str) {
        log.debug("DEBUG：将16进制字符串转换为byte[]：" + str);

        if (str == null || str.trim().equals("")) {
            return new byte[0];
        }

        byte[] bytes = new byte[str.length() / 2];
        String subStr;
        for (int i = 0; i < str.length() / 2; i++) {
            subStr = str.substring(i * 2, i * 2 + 2);
            bytes[i] = (byte) Integer.parseInt(subStr, 16);
        }

        return bytes;
    }
}