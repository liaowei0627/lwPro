/**
 * service-framework
 * JSONUtils.java
 */
package com.liaowei.framework.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.liaowei.framework.jackson.CustomOM;

/**
 * JSONUtils
 *
 * Jackson JSON 工具类
 *
 * @author liaowei
 * @date 创建时间：2018年3月31日 上午11:49:48 
 * @since jdk1.8
 */
public class JSONUtils {

    private static final ObjectMapper mapper = new CustomOM();

    private JSONUtils() {
    }

    public static String objectToJSONString(Object o) throws JsonProcessingException {
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(o);
    }

    public static <T> T JSONStringToObject(String json, Class<T> clazz) throws IOException {
        return mapper.readValue(json, clazz);
    }
}