/**
 * study-service
 * LoginServiceImpl.java
 */
package com.liaowei.study.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.liaowei.framework.core.exception.ApplicationException;
import com.liaowei.study.dao.ISysUserDao;
import com.liaowei.study.entity.SysUser;
import com.liaowei.study.service.ILoginService;
import com.liaowei.study.vo.UserVo;

/**
 * LoginServiceImpl
 *
 * 登录相关接口实现
 *
 * @author liaowei
 * @date 创建时间：2018年4月6日 下午10:31:31 
 * @see com.liaowei.study.service.ILoginService
 * @since jdk1.8
 */
@Service("loginService")
public class LoginServiceImpl implements ILoginService {

    @Resource(name = "sysUserDao")
    private ISysUserDao sysUserDao;

    public UserVo voCopy(SysUser entity) {
        return UserVo.forEntity(entity);
    }

    @Override
    public UserVo findByUserName(String userName) throws ApplicationException {
        SysUser sysUser = sysUserDao.findByUserName(userName);
        return UserVo.forEntity(sysUser);
    }
}