/**
 * platform-service
 * ILoginService.java
 */
package com.flyhaze.platform.service;

import java.util.List;

import com.flyhaze.framework.core.exception.ApplicationException;
import com.flyhaze.platform.vo.MenuVo;
import com.flyhaze.platform.vo.UserVo;

/**
 * ILoginService
 *
 * 登录相关服务接口
 *
 * @author liaowei
 * @date 创建时间：2018年4月6日 下午10:29:16 
 * @since jdk1.8
 */
public interface ILoginService {

    /**
     * 根据用户名取得用户信息
     * 
     * @param userName
     * @return
     * @throws ApplicationException
     */
    UserVo findUserByUserName(String userName) throws ApplicationException;

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