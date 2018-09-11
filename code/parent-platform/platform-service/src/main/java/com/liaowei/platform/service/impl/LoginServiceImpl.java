/**
 * platform-service
 * LoginServiceImpl.java
 */
package com.liaowei.platform.service.impl;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.liaowei.framework.core.exception.ApplicationException;
import com.liaowei.platform.dao.ILoginDao;
import com.liaowei.platform.entity.SysMenu;
import com.liaowei.platform.entity.SysUser;
import com.liaowei.platform.service.ILoginService;
import com.liaowei.platform.vo.MenuVo;
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
    public UserVo findUserByUserName(String userName) throws ApplicationException {
        SysUser sysUser = loginDao.findByUserName(userName);
        UserVo userVo = null;
        if (null != sysUser) {
            userVo = new UserVo();
            BeanUtils.copyProperties(sysUser, userVo);
        }
        return userVo;
    }

    @Override
    public List<MenuVo> findSysMenusByUserId(String userId, String siteCode, boolean isAdmin) throws ApplicationException {
        List<MenuVo> list = null;

        List<SysMenu> menuList = loginDao.findSysMenusByUserId(userId, siteCode, isAdmin);
        if (null != menuList && !menuList.isEmpty()) {
            list = Lists.<MenuVo>newArrayList();
            MenuVo vo;
            Set<SysMenu> children;
            for (SysMenu menu : menuList) {
                vo = new MenuVo();
                vo.copyForEntity(menu);
                if (menu.getHasChild()) {
                    children = menu.getChildren();
                    vo.setChildren(menuChilrenEntityToVo(children));
                }
                list.add(vo);
            }
        }

        return list;
    }

    private List<MenuVo> menuChilrenEntityToVo(Set<SysMenu> children) {
        List<MenuVo> list = Lists.<MenuVo>newArrayList();

        MenuVo childVo;
        Set<SysMenu> c;
        for (SysMenu child : children) {
            childVo = new MenuVo();
            childVo.copyForEntity(child);
            if (child.getHasChild()) {
                c = child.getChildren();
                childVo.setChildren(menuChilrenEntityToVo(c));
            }
            list.add(childVo);
        }

        return list;
    }
}