/**
 * platform-service
 * ILoginService.java
 */
package com.flyhaze.platform.service;

import com.flyhaze.framework.SessionUser;
import com.flyhaze.framework.core.exception.ApplicationException;
import com.flyhaze.platform.entity.SysMenu;
import com.flyhaze.platform.vo.MenuVo;

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
     * 取得用户登录信息
     * 
     * @param userName
     * @param pwdSeed 
     * @return
     * @throws ApplicationException
     */
    SessionUser<SysMenu, MenuVo> login(String userName, String password, String pwdSeed) throws ApplicationException;
}