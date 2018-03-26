package com.liaowei.study.dao;

import java.util.List;

import com.liaowei.study.entity.SysUser;

public interface ISysUserDao {

	void addUser(SysUser sysUser);

	SysUser findUser(String userId);

	List<SysUser> findList(SysUser sysUser);

	void delList(List<String> userIds);
}