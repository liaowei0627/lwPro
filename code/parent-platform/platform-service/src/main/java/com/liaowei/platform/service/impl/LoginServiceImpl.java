/**
 * platform-service
 * LoginServiceImpl.java
 */
package com.liaowei.platform.service.impl;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.liaowei.framework.core.exception.ApplicationException;
import com.liaowei.platform.dao.ILoginDao;
import com.liaowei.platform.entity.SysUser;
import com.liaowei.platform.service.ILoginService;
import com.liaowei.platform.vo.UserVo;

/**
 * LoginServiceImpl
 *
 * 登录相关接口实现
 *
 * @author liaowei
 * @date 创建时间：2018年4月6日 下午10:31:31 
 * @see com.liaowei.platform.service.ILoginService
 * @since jdk1.8
 */
@Service("loginService")
public class LoginServiceImpl implements ILoginService {

    @Resource(name = "loginDao")
    private ILoginDao loginDao;

    @Override
    public UserVo findByUserName(String userName) throws ApplicationException {
        SysUser sysUser = loginDao.findByUserName(userName);
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(sysUser, userVo);
        return userVo;
    }
}