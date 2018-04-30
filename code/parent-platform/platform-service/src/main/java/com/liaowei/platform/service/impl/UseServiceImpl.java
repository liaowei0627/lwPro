/**
 * platform-service
 * UseServiceImpl.java
 */
package com.liaowei.platform.service.impl;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.liaowei.framework.core.dao.IBasisDao;
import com.liaowei.framework.service.impl.ServiceImpl;
import com.liaowei.platform.dao.IUserDao;
import com.liaowei.platform.entity.SysUser;
import com.liaowei.platform.service.IUserService;
import com.liaowei.platform.vo.UserVo;

/**
 * UseServiceImpl
 *
 * 用户管理服务实现
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-30 18:49:03
 * @see com.liaowei.platform.service.IUserService
 * @see com.liaowei.framework.service.impl.ServiceImpl<UserVo, SysUser, String>
 * @since jdk1.8
 */
@Service("userService")
public class UseServiceImpl extends ServiceImpl<UserVo, SysUser, String> implements IUserService {

    @Resource(name = "userDao")
    private IUserDao userDao;

    @Override
    protected IBasisDao<SysUser, String> getDao() {
        return userDao;
    }

    @Override
    protected UserVo entityToVo(SysUser entity) {
        UserVo vo = new UserVo();
        BeanUtils.copyProperties(entity, vo);
        return vo;
    }

    @Override
    protected SysUser voToEntity(UserVo vo) {
        SysUser entity = new SysUser();
        BeanUtils.copyProperties(vo, entity);
        return entity;
    }
}