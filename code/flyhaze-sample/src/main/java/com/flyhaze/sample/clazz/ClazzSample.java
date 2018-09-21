package com.flyhaze.sample.clazz;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;

import org.junit.Test;

import com.google.common.base.Objects;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ClazzSample {

    @Test
    public void checkArray() {
        String[] strArray = new String[5];
        log.info("String[] strArray = " + String.valueOf(strArray instanceof Object[]));
        Integer[] intArray = new Integer[5];
        log.info("Integer[] intArray = " + String.valueOf(intArray instanceof Object[]));
        Date[] dateArray = new Date[5];
        log.info("Date[] dateArray = " + String.valueOf(dateArray instanceof Object[]));
    }

    @Test
    public void checkField() {
        Field[] fields = SamplePojo.class.getFields();
        log.info(String.valueOf(fields.length));
        for (Field field : fields) {
            log.info(field.getName());
        }
    }

    @Test
    public void checkInstanceOf() {
        Object pojo = new SamplePojo();
        log.info(String.valueOf(pojo instanceof SamplePojo));
        log.info(String.valueOf(pojo instanceof SampleSuperPojo));
        log.info(String.valueOf(SampleSuperPojo.class.isAssignableFrom(pojo.getClass())));
        log.info(String.valueOf(SamplePojo.class.isAssignableFrom(SampleSuperPojo.class)));
        log.info(String.valueOf(SampleSuperPojo.class.isAssignableFrom(SamplePojo.class)));
        Class<SamplePojo> c = SamplePojo.class;
        c.cast(pojo);
    }

    @Test
    public void checkMethod() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        SamplePojo pojo = new SamplePojo();
        pojo.setId("adfsdf");
        pojo.setUserName("admin");
        pojo.setPassword("admin123");
        SamplePojo parent = new SamplePojo();
        parent.setId("qwerty");
        parent.setUserName("padmin");
        parent.setPassword("padmin123");
        pojo.setParent(parent);
        Class<SamplePojo> clazz = SamplePojo.class;
        Method[] dms = clazz.getMethods();
        String methodName;
        for (Method method : dms) {
            methodName = method.getName();
            if (methodName.startsWith("get") && !Objects.equal("getClass", methodName)) {
                log.info(methodName);
                log.info(method.getReturnType().getName());
//                log.info(method.invoke(pojo));
            }
        }
    }
}