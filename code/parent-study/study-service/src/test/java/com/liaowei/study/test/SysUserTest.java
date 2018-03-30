package com.liaowei.study.test;

import java.security.MessageDigest;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.liaowei.study.entity.SysUser;
import com.liaowei.study.service.ISysUserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/applicationContext*.xml" })
public class SysUserTest {

    @Resource(name = "sysUserService")
    private ISysUserService sysUserService;

    @Test
    public void testAdd() {
        SysUser sysUser = new SysUser();
        sysUser.setUserName("admin");
        sysUser.setPassword(MD5("admin123"));
        sysUser.setValid(Boolean.TRUE);
        sysUser.setCreator("admin");
        sysUser.setReviser("admin");
        sysUser = sysUserService.addEntity(sysUser);
        ObjectMapper mapper = new ObjectMapper();
        String json = null;
        try {
            json = mapper.writeValueAsString(sysUser);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(json);
    }

    private String MD5(String s) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] bytes = md.digest(s.getBytes("utf-8"));
            return toHex(bytes);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String toHex(byte[] bytes) {

        final char[] HEX_DIGITS = "0123456789ABCDEF".toCharArray();
        StringBuilder ret = new StringBuilder(bytes.length * 2);
        for (int i = 0; i < bytes.length; i++) {
            ret.append(HEX_DIGITS[(bytes[i] >> 4) & 0x0f]);
            ret.append(HEX_DIGITS[bytes[i] & 0x0f]);
        }
        return ret.toString();
    }
}