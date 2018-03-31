package com.liaowei.sample.clazz;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.google.common.base.Objects;

public class ClazzSample {

    public static void main(String[] args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        checkField();
    }

    public static void checkField() {
        Field[] fields = SamplePojo.class.getFields();
        System.out.println(fields.length);
        for (Field field : fields) {
            System.out.println(field.getName());
        }
    }

    public static void checkInstanceOf() {
        Object pojo = new SamplePojo();
        System.out.println(pojo instanceof SamplePojo);
        System.out.println(pojo instanceof SampleSuperPojo);
        System.out.println(SampleSuperPojo.class.isAssignableFrom(pojo.getClass()));
        System.out.println(SamplePojo.class.isAssignableFrom(SampleSuperPojo.class));
        Class<SamplePojo> c = SamplePojo.class;
        c.cast(pojo);
    }

    public static void checkMethod() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        SamplePojo pojo = new SamplePojo();
        pojo.setId("adfsdf");
        pojo.setUserName("admin");
        pojo.setPassword("admin123");
        Class<SamplePojo> clazz = SamplePojo.class;
        Method[] dms = clazz.getMethods();
        String methodName;
        for (Method method : dms) {
            methodName = method.getName();
            if (methodName.startsWith("get") && !Objects.equal("getClass", methodName)) {
                System.out.println(methodName);
//                System.out.println(method.getReturnType().getName());
//                System.out.println(method.invoke(pojo));
            }
        }
    }
}