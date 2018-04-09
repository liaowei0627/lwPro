/**
 * study-service
 * LoginServiceImpl.java
 */
package com.liaowei.study.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.liaowei.framework.core.dao.IBasisDao;
import com.liaowei.framework.service.impl.ServiceImpl;
import com.liaowei.study.dao.ISysUserDao;
import com.liaowei.study.entity.SysUser;
import com.liaowei.study.service.ILoginService;
import com.liaowei.study.vo.UserVo;

/**
 * LoginServiceImpl
 *
 * TODO
 *
 * @author liaowei
 * @date 创建时间：2018年4月6日 下午10:31:31 
 * @see TODO
 * @since jdk1.8
 */
@Service("loginService")
public class LoginServiceImpl extends ServiceImpl<UserVo, SysUser, String> implements ILoginService {

    @Resource(name = "sysUserDao")
    private ISysUserDao sysUserDao;

    @Override
    protected String getClassName() {
        return SysUserServiceImpl.class.getName();
    }

    @Override
    protected IBasisDao<SysUser, String> getDao() {
        return sysUserDao;
    }

    @Override
    protected UserVo voCopy(SysUser entity) {
        UserVo vo = new UserVo();
        vo.copyEntity(entity);
        return vo;
    }
}