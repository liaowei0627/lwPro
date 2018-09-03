package com.liaowei.study.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.liaowei.framework.dao.IDao;
import com.liaowei.framework.service.impl.ServiceImpl;
import com.liaowei.study.dao.ISysUserDao;
import com.liaowei.study.entity.SysUser;
import com.liaowei.study.service.IUserService;
import com.liaowei.study.vo.UserVo;

import lombok.extern.slf4j.Slf4j;

@Service("userService")
@Slf4j
public class UserServiceImpl extends ServiceImpl<SysUser, UserVo> implements IUserService {

	@Resource(name = "sysUserDao")
	private ISysUserDao sysUserDao;

	@Override
	protected IDao<SysUser> getDao() {
	    return sysUserDao;
	}

    @Override
    protected UserVo entityToVo(SysUser e) {
        log.debug("DEBUG：Entity转换Vo：" + e);

        UserVo v = new UserVo();
        v.copyForEntity(e);

        return v;
    }
}