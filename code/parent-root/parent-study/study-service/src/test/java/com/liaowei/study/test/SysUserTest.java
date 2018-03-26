package com.liaowei.study.test;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.liaowei.study.entity.SysUser;
import com.liaowei.study.service.ISysUserService;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations = {"classpath*:**/applicationContext*.xml"})  
public class SysUserTest {

	@Resource(name = "sysUserService")
	private ISysUserService sysUserService;

	@Test
	public void testAdd() {
		SysUser sysUser = sysUserService.addUser("user1", "pwd1");
		ObjectMapper mapper = new ObjectMapper();
		String json = null;
		try {
			json = mapper.writeValueAsString(sysUser);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(json);
	}
}