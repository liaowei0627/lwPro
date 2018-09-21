/**
 * platform-service
 * UseServiceImpl.java
 */
package com.flyhaze.platform.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.flyhaze.framework.dao.IDao;
import com.flyhaze.framework.service.impl.ServiceImpl;
import com.flyhaze.platform.dao.IUserDao;
import com.flyhaze.platform.entity.SysUser;
import com.flyhaze.platform.service.IUserService;
import com.flyhaze.platform.vo.UserVo;

import lombok.extern.slf4j.Slf4j;

/**
 * UseServiceImpl
 *
 * 用户管理服务实现
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-30 18:49:03
 * @see com.flyhaze.platform.service.IUserService
 * @see com.flyhaze.framework.service.impl.ServiceImpl<SysUser, UserVo>
 * @since jdk1.8
 */
@Service("userService")
@Slf4j
public class UseServiceImpl extends ServiceImpl<SysUser, UserVo> implements IUserService {

    @Resource(name = "userDao")
    private IUserDao userDao;

    @Override
    protected IDao<SysUser> getDao() {
        return userDao;
    }

    @Override
    protected UserVo entityToVo(SysUser e) {
        log.debug("DEBUG：Entity转换Vo：" + e);

        UserVo v = new UserVo();
        v.copyForEntity(e);

        return v;
    }
}