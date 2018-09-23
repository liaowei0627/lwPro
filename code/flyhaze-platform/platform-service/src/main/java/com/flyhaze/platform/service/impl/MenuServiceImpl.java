/**
 * platform-service
 * MenuServiceImpl.java
 */
package com.flyhaze.platform.service.impl;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.flyhaze.framework.core.exception.ApplicationException;
import com.flyhaze.framework.service.impl.ServiceImpl;
import com.flyhaze.platform.dao.IMenuDao;
import com.flyhaze.platform.entity.SysMenu;
import com.flyhaze.platform.service.IMenuService;
import com.flyhaze.platform.vo.MenuVo;
import com.google.common.collect.Lists;

/**
 * MenuServiceImpl
 *
 * 菜单管理服务实现
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-21 23:40:19
 * @see com.flyhaze.platform.service.IMenuService
 * @see com.flyhaze.framework.service.impl.ServiceImpl<MenuVo, SysMenu>
 * @since jdk1.8
 */
@Service("menuService")
public class MenuServiceImpl extends ServiceImpl<SysMenu, MenuVo> implements IMenuService {

    @Resource(name = "menuDao")
    private IMenuDao menuDao;

    @Override
    protected IMenuDao getDao() {
        return menuDao;
    }

    @Override
    protected MenuVo entityToVo(SysMenu e) {
        MenuVo v = new MenuVo();
        v.copyForEntity(e);
        return v;
    }

    @Override
    public List<MenuVo> findSysMenusByUserId(String userId, String siteCode, boolean isAdmin) throws ApplicationException {
        List<MenuVo> list = null;

        List<SysMenu> menuList = menuDao.findSysMenusByUserId(userId, siteCode, isAdmin);
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