package com.flyhaze.study.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.flyhaze.core.exception.ApplicationException;
import com.flyhaze.framework.hibernate.dao.IDao;
import com.flyhaze.framework.service.impl.ServiceImpl;
import com.flyhaze.study.dao.ISysUserDao;
import com.flyhaze.study.entity.SysUser;
import com.flyhaze.study.service.IUserService;
import com.flyhaze.study.vo.UserVo;

@Service("userService")
public class UserServiceImpl extends ServiceImpl<SysUser, UserVo> implements IUserService {

	@Resource(name = "sysUserDao")
	private ISysUserDao sysUserDao;

	@Override
	protected IDao<SysUser> getDao() {
	    return sysUserDao;
	}

    @Override
    protected UserVo entityToVo(SysUser e) {

        UserVo v = new UserVo();
        v.copyForEntity(e);

        return v;
    }

    @Override
    protected boolean validSave(UserVo vo) throws ApplicationException {
        return true;
    }

    @Override
    protected boolean validDel(String[] ids) throws ApplicationException {
        return true;
    }
}