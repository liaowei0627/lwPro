/**
 * platform-service
 * MenuServiceImpl.java
 */
package com.liaowei.platform.service.impl;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.google.common.base.Strings;
import com.liaowei.framework.core.exception.ApplicationException;
import com.liaowei.framework.dao.IDao;
import com.liaowei.framework.service.impl.ServiceImpl;
import com.liaowei.platform.dao.IMenuDao;
import com.liaowei.platform.entity.SysMenu;
import com.liaowei.platform.service.IMenuService;
import com.liaowei.platform.vo.MenuVo;

import lombok.extern.slf4j.Slf4j;

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
@Slf4j
public class MenuServiceImpl extends ServiceImpl<MenuVo, SysMenu, String> implements IMenuService {

    @Resource(name = "menuDao")
    private IMenuDao menuDao;

    @Override
    protected IDao<SysMenu, String> getDao() {
        return menuDao;
    }

    @Override
    protected MenuVo entityToVo(SysMenu e) {
        log.debug("Entity转换Vo，Id：" + e.getId());

        SysMenu entityParent = e.getParent();
        MenuVo parent = null;
        if (null != entityParent) {
            parent = entityToVo(entityParent);
        }
        Set<MenuVo> children = null;
        Set<SysMenu> entityChildren = e.getChildren();
        if (null != entityChildren && !entityChildren.isEmpty()) {
            children = new HashSet<>();
            for (SysMenu entity : entityChildren) {
                children.add(entityToVo(entity));
            }
        }
        MenuVo v = new MenuVo(e.getId(), e.getMenuUrl(), e.getMenuType(), e.getCode(), e.getText(), e.getFullCode(),
                e.getFullText(), parent, children, e.getOrderNum(), e.getValid(), e.getCreator(), e.getCreateTime(),
                e.getReviser(), e.getModifyTime());

        return v;
    }

    @Override
    protected SysMenu voToEntity(MenuVo v) {
        log.debug("Vo转换Entity：" + v.toString());

        MenuVo voParent = v.getParent();
        SysMenu parent = null;
        if (null != voParent) {
            parent = voToEntity(voParent);
        }
        Set<SysMenu> children = null;
        Set<MenuVo> voChildren = v.getChildren();
        if (null != voChildren && !voChildren.isEmpty()) {
            children = new HashSet<>();
            for (MenuVo vo : voChildren) {
                children.add(voToEntity(vo));
            }
        }
        SysMenu e = new SysMenu(v.getId(), v.getMenuUrl(), v.getMenuType(), v.getCode(), v.getText(), v.getFullCode(),
                v.getFullText(), parent, children, v.getOrderNum(), v.getValid(), v.getCreator(), v.getCreateTime(),
                v.getReviser(), v.getModifyTime());

        return e;
    }

    @Override
    public MenuVo addVo(MenuVo vo) throws ApplicationException {
        log.debug("新增菜单：" + vo.toString());

        MenuVo parent = vo.getParent();
        StringBuilder fullCode = new StringBuilder(200);
        fullCode.append("");
        StringBuilder fullText = new StringBuilder(200);
        fullText.append("");
        if (null != parent) {
            String parentId = parent.getId();
            if (!Strings.isNullOrEmpty(parentId)) {
                SysMenu parentEntity = menuDao.findEntity(parentId);
                if (null != parentEntity) {
                    fullCode.append(parentEntity.getFullCode());
                    fullCode.append("-");
                    fullText.append(parentEntity.getFullText());
                    fullText.append("|");
                }
            }
        }
        fullCode.append(vo.getCode());
        fullText.append(vo.getText());
        vo.setFullCode(fullCode.toString());
        vo.setFullText(fullText.toString());
        return super.addVo(vo);
    }
}