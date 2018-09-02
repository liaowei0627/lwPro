/**
 * platform-service
 * UseServiceImpl.java
 */
package com.liaowei.platform.service.impl;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.liaowei.framework.dao.IDao;
import com.liaowei.framework.service.impl.ServiceImpl;
import com.liaowei.platform.dao.IUserDao;
import com.liaowei.platform.entity.SysUser;
import com.liaowei.platform.entity.SysUserAuth;
import com.liaowei.platform.entity.SysUserRole;
import com.liaowei.platform.service.IUserService;
import com.liaowei.platform.vo.AuthDataTypeVo;
import com.liaowei.platform.vo.AuthorityVo;
import com.liaowei.platform.vo.MenuVo;
import com.liaowei.platform.vo.RoleVo;
import com.liaowei.platform.vo.SiteVo;
import com.liaowei.platform.vo.UserVo;

import lombok.extern.slf4j.Slf4j;

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
@Slf4j
public class UseServiceImpl extends ServiceImpl<UserVo, SysUser, String> implements IUserService {

    @Resource(name = "userDao")
    private IUserDao userDao;

    @Override
    protected IDao<SysUser, String> getDao() {
        return userDao;
    }

    @Override
    protected UserVo entityToVo(SysUser e) {
        log.debug("Entity转换VoId：" + e.getId());

        Set<SysUserRole> sysUserRoles = e.getSysUserRoles();
        List<RoleVo> roles = null;
        if (null != sysUserRoles && !sysUserRoles.isEmpty()) {
            roles = Lists.<RoleVo>newArrayList();
            for (SysUserRole sysUserRole : sysUserRoles) {
                
            }
        }

        Set<SysUserAuth> sysUserAuths = e.getSysUserAuths();
        List<AuthorityVo> authorities = null;
        if (null != sysUserAuths && !sysUserAuths.isEmpty()) {
            
        }

        List<SiteVo> sites = null;

        List<AuthDataTypeVo> authDataTypes = null;

        Set<MenuVo> menus = null;

        UserVo v = new UserVo(e.getId(), e.getUserName(), e.getPassword(), roles, authorities, sites, authDataTypes, menus, e.getValid(), e.getCreator(), e.getCreateTime(), e.getReviser(), e.getModifyTime());

        return v;
    }

    @Override
    protected SysUser voToEntity(UserVo v) {
        log.debug("Vo转换Entity：" + v.toString());
        SysUser e = new SysUser();
        BeanUtils.copyProperties(v, e);
        return e;
    }
}