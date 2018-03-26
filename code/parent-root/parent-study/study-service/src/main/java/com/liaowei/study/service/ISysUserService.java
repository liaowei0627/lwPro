package com.liaowei.study.service;

import java.util.List;

import com.liaowei.study.entity.SysUser;

public interface ISysUserService {

	SysUser addUser(String userName, String password);

	SysUser findUser(String userId);

	List<SysUser> findList(SysUser sysUser);

	void delList(List<String> userIds);
}