/**
 * flyhaze-framework
 * JSONUtils.java
 */
package com.flyhaze.utils;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flyhaze.jackson.CustomOM;

/**
 * JSONUtils
 *
 * Jackson JSON 工具类
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-08 21:31:43
 * @since jdk1.8
 */
public class JSONUtils {

    private static final ObjectMapper mapper = new CustomOM();

    private JSONUtils() {
    }

    /**
     * 对象转JSON字符串
     * 
     * @param o
     * @return
     * @throws JsonProcessingException
     */
    public static String objectToJSONString(Object o) throws JsonProcessingException {
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(o);
    }

    /**
     * JSON字符串转对象
     * 
     * @param json
     * @param clazz
     * @return
     * @throws IOException
     */
    public static <T> T JSONStringToObject(String json, Class<T> clazz) throws IOException {
        return mapper.readValue(json, clazz);
    }
}