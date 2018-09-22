/**
 * platform-service
 * MenuServiceImpl.java
 */
package com.flyhaze.platform.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.flyhaze.framework.service.impl.ServiceImpl;
import com.flyhaze.platform.dao.IMenuDao;
import com.flyhaze.platform.entity.SysMenu;
import com.flyhaze.platform.service.IMenuService;
import com.flyhaze.platform.vo.MenuVo;

import lombok.extern.slf4j.Slf4j;

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
@Slf4j
public class MenuServiceImpl extends ServiceImpl<SysMenu, MenuVo> implements IMenuService {

    @Resource(name = "menuDao")
    private IMenuDao menuDao;

    @Override
    protected IMenuDao getDao() {
        return menuDao;
    }

    @Override
    protected MenuVo entityToVo(SysMenu e) {
        log.debug("DEBUG：Entity转换Vo：" + e);

        MenuVo v = new MenuVo();
        v.copyForEntity(e);

        return v;
    }
}