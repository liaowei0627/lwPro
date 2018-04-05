package com.liaowei.study.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.liaowei.framework.dao.IDao;
import com.liaowei.framework.service.impl.SpringServiceImpl;
import com.liaowei.study.dao.ISysUserDao;
import com.liaowei.study.entity.SysUser;
import com.liaowei.study.service.ISysUserService;
import com.liaowei.study.vo.UserVo;

@Service("sysUserService")
public class SysUserServiceImpl extends SpringServiceImpl<UserVo, SysUser, String> implements ISysUserService {

	@Resource(name = "sysUserDao")
	private ISysUserDao sysUserDao;

	@Override
	protected IDao<SysUser, String> getDao() {
	    return sysUserDao;
	}

	@Override
	protected String getClassName() {
	    return SysUserServiceImpl.class.getName();
	}

    @Override
    protected UserVo voCopy(SysUser entity) {
        UserVo vo = new UserVo();
        vo.copyEntity(entity);
        return vo;
    }
}