package com.liaowei.study.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.liaowei.study.dao.ISysUserDao;
import com.liaowei.study.entity.SysUser;
import com.liaowei.study.service.ISysUserService;

@Service("sysUserService")
public class SysUserServiceImpl implements ISysUserService {

	@Resource(name = "sysUserDao")
	private ISysUserDao sysUserDao;

	@Override
	public SysUser addUser(String userName, String password) {
		SysUser sysUser = new SysUser();
		sysUser.setUserName(userName);
		sysUser.setPassword(password);
		sysUserDao.addUser(sysUser);

		return sysUser;
	}

	@Override
	public SysUser findUser(String userId) {
		return sysUserDao.findUser(userId);
	}

	@Override
	public List<SysUser> findList(SysUser sysUser) {
		return sysUserDao.findList(sysUser);
	}

	@Override
	public void delList(List<String> userIds) {
		sysUserDao.delList(userIds);
	}
}