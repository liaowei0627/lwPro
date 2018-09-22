/**
 * study-service
 * LoginServiceImpl.java
 */
package com.flyhaze.study.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.flyhaze.framework.core.exception.ApplicationException;
import com.flyhaze.study.dao.ISysUserDao;
import com.flyhaze.study.entity.SysUser;
import com.flyhaze.study.service.ILoginService;
import com.flyhaze.study.vo.UserVo;

/**
 * LoginServiceImpl
 *
 * 登录相关接口实现
 *
 * @author liaowei
 * @date 创建时间：2018年4月6日 下午10:31:31 
 * @see com.flyhaze.study.service.ILoginService
 * @since jdk1.8
 */
@Service("loginService")
public class LoginServiceImpl implements ILoginService {

    @Resource(name = "sysUserDao")
    private ISysUserDao sysUserDao;

    @Override
    public UserVo findByUserName(String userName) throws ApplicationException {
        SysUser sysUser = sysUserDao.findByUserName(userName);
        UserVo vo = new UserVo();
        vo.copyForEntity(sysUser);
        return vo;
    }
}