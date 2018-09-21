/**
 * platform-service
 * ILoginDao.java
 */
package com.flyhaze.platform.dao;

import java.util.List;

import com.flyhaze.platform.entity.SysMenu;
import com.flyhaze.platform.entity.SysUser;

/**
 * ILoginDao
 *
 * 登录相关DAO接口
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-15 11:16:50
 * @since jdk1.8
 */
public interface ILoginDao {

    /**
     * 根据用户名取得用户信息
     * 
     * @param userName
     * @return
     * @throws ApplicationException
     */
    SysUser findByUserName(String userName);

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