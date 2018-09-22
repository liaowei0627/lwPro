/**
 * flyhaze-framework
 * JSONUtils.java
 */
package com.flyhaze.utils;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flyhaze.jackson.CustomOM;

import lombok.extern.slf4j.Slf4j;

/**
 * JSONUtils
 *
 * Jackson JSON 工具类
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-08 21:31:43
 * @since jdk1.8
 */
@Slf4j
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
        log.debug("DEBUG：对象转JSON字符串：" + o.toString());
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
        log.debug("DEBUG：JSON字符串转对象：" + json);
        return mapper.readValue(json, clazz);
    }
}