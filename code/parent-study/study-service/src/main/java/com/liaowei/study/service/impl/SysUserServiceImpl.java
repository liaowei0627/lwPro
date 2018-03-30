package com.liaowei.study.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.liaowei.framework.dao.IDao;
import com.liaowei.framework.service.impl.ServiceImpl;
import com.liaowei.study.dao.ISysUserDao;
import com.liaowei.study.entity.SysUser;
import com.liaowei.study.service.ISysUserService;

@Service("sysUserService")
public class SysUserServiceImpl extends ServiceImpl<SysUser, String> implements ISysUserService {

	@Resource(name = "sysUserDao")
	private ISysUserDao sysUserDao;

	@Override
	protected IDao<SysUser, String> getDao() {
	    return sysUserDao;
	}
}