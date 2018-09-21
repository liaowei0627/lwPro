/**
 * Sample
 * JSONSample.java
 */
package com.flyhaze.sample.jackson;

import java.io.IOException;
import java.text.DateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * JSONSample
 *
 * JSON操作示例
 *
 * @author liaowei
 * @date 创建时间：2018年3月31日 下午12:26:32 
 * @since jdk1.8
 */
public class JSONSample {

    private static final ObjectMapper mapper;
    static {
        mapper = new ObjectMapper();
        JavaTimeModule module = new JavaTimeModule();
        module.addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        module.addDeserializer(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        module.addSerializer(LocalTime.class, new LocalTimeSerializer(DateTimeFormatter.ofPattern("HH:mm:ss")));
        module.addDeserializer(LocalTime.class, new LocalTimeDeserializer(DateTimeFormatter.ofPattern("HH:mm:ss")));
        module.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        module.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        mapper.registerModule(module);
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM);
        mapper.setDateFormat(dateFormat);
        SerializerProvider serializerProvider = mapper.getSerializerProvider();
        serializerProvider.setNullValueSerializer(new JsonSerializer<Object>() {

            @Override
            public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
                gen.writeString("");
            }
            
        });
    }

    public static void main(String[] args) throws IOException {
        SamplePojo pojo = new SamplePojo();
        pojo.setStr("abcedfg");
        pojo.setIntObj(Integer.valueOf(1000));
        pojo.setIntNum(500);
        pojo.setLongObj(Long.valueOf("10000"));
        pojo.setLongNum(Long.valueOf("5000").longValue());
        pojo.setDate(Date.from(Instant.now()));
        pojo.setLocalDateTime(LocalDateTime.now());
        pojo.setList(Lists.newArrayList());
        pojo.setMap(Maps.newHashMap());
        String json = objectToJSONString(pojo);
        System.out.println(json);
        SamplePojo obj = JSONStringToObject(json, SamplePojo.class);
        System.out.println(obj.getStr());
    }

    private static String objectToJSONString(Object o) throws JsonProcessingException {
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(o);
    }

    private static <T> T JSONStringToObject(String json, Class<T> clazz) throws IOException {
        return mapper.readValue(json, clazz);
    }
}
