/**
 * platform-service
 * MenuServiceImpl.java
 */
package com.liaowei.platform.service.impl;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.liaowei.framework.core.dao.IBasisDao;
import com.liaowei.framework.service.impl.ServiceImpl;
import com.liaowei.platform.dao.IMenuDao;
import com.liaowei.platform.entity.SysMenu;
import com.liaowei.platform.service.IMenuService;
import com.liaowei.platform.vo.MenuVo;

/**
 * MenuServiceImpl
 *
 * 菜单管理服务实现
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-21 23:40:19
 * @see com.liaowei.platform.service.IMenuService
 * @see com.liaowei.framework.service.impl.ServiceImpl<MenuVo, SysMenu, String>
 * @since jdk1.8
 */
@Service("menuService")
public class MenuServiceImpl extends ServiceImpl<MenuVo, SysMenu, String> implements IMenuService {

    @Resource(name = "menuDao")
    private IMenuDao menuDao;

    @Override
    protected IBasisDao<SysMenu, String> getDao() {
        return menuDao;
    }

    @Override
    protected MenuVo entityToVo(SysMenu entity) {
        MenuVo vo = new MenuVo();
        BeanUtils.copyProperties(entity, vo);
        return vo;
    }

    @Override
    protected SysMenu voToEntity(MenuVo vo) {
        SysMenu entity = new SysMenu();
        BeanUtils.copyProperties(vo, entity);
        return entity;
    }
}