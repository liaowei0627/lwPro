/**
 * platform-service
 * IMenuService.java
 */
package com.flyhaze.platform.service;

import java.util.List;

import com.flyhaze.core.exception.ApplicationException;
import com.flyhaze.framework.service.IService;
import com.flyhaze.platform.entity.SysMenu;
import com.flyhaze.platform.vo.MenuVo;

/**
 * IMenuService
 *
 * 菜单管理服务接口
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-21 23:38:17
 * @see com.flyhaze.framework.service.IService<SysMenu, MenuVo>
 * @since jdk1.8
 */
public interface IMenuService extends IService<SysMenu, MenuVo> {

    /**
     * 根据用户id取得所授权的菜单树
     * 
     * @param userId 用户id
     * @param siteCode 站点编号
     * @param isAdmin 是否系统管理员
     * @return
     * @throws ApplicationException
     */
    List<MenuVo> findSysMenusByUserId(String userId, String siteCode, boolean isAdmin) throws ApplicationException;
}