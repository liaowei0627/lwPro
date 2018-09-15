/**
 * platform-service
 * TestClass.java
 */
package com.liaowei.platform.test.clazz;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Test;

import com.google.common.base.Objects;
import com.liaowei.platform.entity.SysMenu;

import lombok.extern.slf4j.Slf4j;

/**
 * TestClass
 *
 * 反射测试用例
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-05-07 22:35:54
 * @since jdk1.8
 */
@Slf4j
public class TestClass {

    @Test
    public void checkMethod() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        SysMenu menu = new SysMenu();
        menu.setId("菜单1id");
        menu.setText("菜单1");
        SysMenu parent = new SysMenu();
        parent.setId("分系统1id");
        parent.setText("分系统1");
        menu.setParent(parent);
        Class<SysMenu> clazz = SysMenu.class;
        Method[] dms = clazz.getMethods();
        String methodName;
        for (Method method : dms) {
            methodName = method.getName();
            if (methodName.startsWith("get") && !Objects.equal("getClass", methodName)) {
                log.info(methodName);
                Class<?> returnType = method.getReturnType();
                log.info(returnType.getName());
//                log.info(method.invoke(pojo));
            }
        }
    }
}
