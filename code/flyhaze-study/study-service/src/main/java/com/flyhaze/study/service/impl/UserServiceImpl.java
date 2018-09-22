package com.flyhaze.study.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.flyhaze.framework.hibernate.dao.IDao;
import com.flyhaze.framework.service.impl.ServiceImpl;
import com.flyhaze.study.dao.ISysUserDao;
import com.flyhaze.study.entity.SysUser;
import com.flyhaze.study.service.IUserService;
import com.flyhaze.study.vo.UserVo;

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