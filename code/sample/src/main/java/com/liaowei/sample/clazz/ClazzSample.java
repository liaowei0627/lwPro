package com.liaowei.sample.clazz;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Objects;

public class ClazzSample {
    private static final Logger log = LoggerFactory.getLogger(ClazzSample.class);

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