/**
 * platform-service
 * LoginDaoImpl.java
 */
package com.flyhaze.platform.dao.impl;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.flyhaze.platform.dao.ILoginDao;
import com.flyhaze.platform.entity.SysAuthMenu;
import com.flyhaze.platform.entity.SysMenu;
import com.flyhaze.platform.entity.SysRoleAuth;
import com.flyhaze.platform.entity.SysUser;
import com.flyhaze.platform.entity.SysUserAuth;
import com.flyhaze.platform.entity.SysUserRole;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

/**
 * LoginDaoImpl
 *
 * 登录相关DAO实现
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-15 11:19:19
 * @see com.flyhaze.platform.dao.ILoginDao
 * @since jdk1.8
 */
@Repository("loginDao")
public class LoginDaoImpl implements ILoginDao {

    @Resource(name = "sessionFactory")
    protected SessionFactory sessionFactory;

    @Override
    public SysUser findByUserName(String userName) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "from SysUser t where t.userName = :userName and t.valid = true";
        Query<SysUser> query = session.createQuery(hql, SysUser.class);
        query.setParameter("userName", userName);
        SysUser sysUser = query.uniqueResult();
        return sysUser;
    }

    @Override
    public List<SysMenu> findSysMenusByUserId(String userId, String siteCode, boolean isAdmin) {
        Session session = sessionFactory.getCurrentSession();

        StringBuilder menuHql = new StringBuilder();
        menuHql.append("from SysMenu t where t.parent is null and t.valid = true ");

        List<String> menuIds = null;
        if (!isAdmin) {
            List<String> authIds = null;
            // 当前用户拥有的角色
            String roleHql = "from SysUserRole t where t.sysUserId = :userId";
            Query<SysUserRole> roleQuery = session.createQuery(roleHql, SysUserRole.class);
            roleQuery.setParameter("userId", userId);
            List<SysUserRole> roles = roleQuery.list();
            if (!roles.isEmpty()) {
                List<String> roleIds = Lists.<String>newArrayList();
                for (SysUserRole userRole : roles) {
                    roleIds.add(userRole.getSysRoleId());
                }
                // 当前用户拥有的角色拥有的权限
                String authHql = "from SysRoleAuth t where t.sysRoleId in (:roleIds)";
                Query<SysRoleAuth> authQuery = session.createQuery(authHql, SysRoleAuth.class);
                authQuery.setParameterList("roleIds", roleIds);
                List<SysRoleAuth> authes = authQuery.list();
                if (!authes.isEmpty()) {
                    authIds = Lists.<String>newArrayList();
                    for (SysRoleAuth roleAuth : authes) {
                        authIds.add(roleAuth.getSysAuthId());
                    }
                }
            }

            // 当前用户拥有的权限
            String authHql = "from SysUserRole t where t.sysUserId = :userId";
            Query<SysUserAuth> authQuery = session.createQuery(authHql, SysUserAuth.class);
            authQuery.setParameter("userId", userId);
            List<SysUserAuth> authes = authQuery.list();
            if (!authes.isEmpty()) {
                if (null == authIds) {
                    authIds = Lists.<String>newArrayList();
                }
                for (SysUserAuth userAuth : authes) {
                    authIds.add(userAuth.getSysAuthId());
                }
            }

            if (null != authIds && !authIds.isEmpty()) {
                String authMenuHql = "from SysAuthMenu t where t.sysAuthId in (:authIds)";
                Query<SysAuthMenu> authMenuQuery = session.createQuery(authMenuHql, SysAuthMenu.class);
                authQuery.setParameterList("authIds", authIds);
                List<SysAuthMenu> authMenus = authMenuQuery.list();
                if (!authMenus.isEmpty()) {
                    menuIds = Lists.<String>newArrayList();
                    for (SysAuthMenu authMenu : authMenus) {
                        menuIds.add(authMenu.getSysMenuId());
                    }
                    menuHql.append("and t.id in (:menuIds) ");
                }
            }
        }

        menuHql.append("order by t.orderNum asc");
        Query<SysMenu> menuQuery = session.createQuery(menuHql.toString(), SysMenu.class);
        if (null != menuIds && !menuIds.isEmpty()) {
            menuQuery.setParameterList("menuIds", menuIds);
        }
        List<SysMenu> menuList = menuQuery.list();
        List<SysMenu> list = null;
        if (!menuList.isEmpty()) {
            list = Lists.<SysMenu>newArrayList();
            Set<SysMenu> children;
            for (SysMenu sysMenu : menuList) {
                if (sysMenu.getHasChild()) {
                    children = findMenuTree(menuIds, sysMenu.getId(), session);
                } else {
                    children = null;
                }
                list.add(new SysMenu(sysMenu.getId(), sysMenu.getMenuUrl(), sysMenu.getMenuType(), sysMenu.getSubSystem(),
                        sysMenu.getCode(), sysMenu.getText(), sysMenu.getFullCode(), sysMenu.getFullText(), null, children,
                        sysMenu.getOrderNum(), sysMenu.getValid(), sysMenu.getCreator(), sysMenu.getCreateTime(),
                        sysMenu.getReviser(), sysMenu.getModifyTime()));
            }
        }

        return list;
    }

    private Set<SysMenu> findMenuTree(List<String> menuIds, String parentId, Session session) {
        StringBuilder hql = new StringBuilder();
        hql.append("from SysMenu t where t.valid = true and t.parent.id = :parentId ");
        if (null != menuIds && !menuIds.isEmpty()) {
            hql.append("and id in (:menuIds) ");
        }
        hql.append("order by t.orderNum asc");
        Query<SysMenu> menuQuery = session.createQuery(hql.toString(), SysMenu.class);
        menuQuery.setParameter("parentId", parentId);
        if (null != menuIds && !menuIds.isEmpty()) {
            menuQuery.setParameterList("menuIds", menuIds);
        }
        List<SysMenu> menuList = menuQuery.list();
        Set<SysMenu> list = null;
        if (!menuList.isEmpty()) {
            list = Sets.<SysMenu>newLinkedHashSet();
            Set<SysMenu> children;
            for (SysMenu sysMenu : menuList) {
                if (sysMenu.getHasChild()) {
                    children = findMenuTree(menuIds, sysMenu.getId(), session);
                } else {
                    children = null;
                }
                list.add(new SysMenu(sysMenu.getId(), sysMenu.getMenuUrl(), sysMenu.getMenuType(), sysMenu.getSubSystem(),
                        sysMenu.getCode(), sysMenu.getText(), sysMenu.getFullCode(), sysMenu.getFullText(), null, children,
                        sysMenu.getOrderNum(), sysMenu.getValid(), sysMenu.getCreator(), sysMenu.getCreateTime(),
                        sysMenu.getReviser(), sysMenu.getModifyTime()));
            }
        }

        return list;
    }
}