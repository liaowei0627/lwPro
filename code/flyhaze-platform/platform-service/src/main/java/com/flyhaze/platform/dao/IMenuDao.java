/**
 * platform-service
 * IMenuDao.java
 */
package com.flyhaze.platform.dao;

import java.util.List;

import com.flyhaze.framework.core.exception.ApplicationException;
import com.flyhaze.framework.hibernate.dao.IDao;
import com.flyhaze.platform.entity.SysMenu;

/**
 * IMenuDao
 *
 * 菜单管理DAO接口
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-21 23:41:12
 * @see com.flyhaze.framework.dao.IDao<SysMenu>
 * @since jdk1.8
 */
public interface IMenuDao extends IDao<SysMenu> {

    /**
     * 根据用户id取得所授权的菜单树
     * 
     * @param userId 用户id
     * @param siteCode 站点编号
     * @param isAdmin 是否系统管理员
     * @return
     * @throws ApplicationException
     */
    List<SysMenu> findSysMenusByUserId(String userId, String siteCode, boolean isAdmin);
}