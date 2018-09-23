/**
 * platform-service
 * LoginServiceImpl.java
 */
package com.flyhaze.platform.service.impl;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.flyhaze.framework.SessionUser;
import com.flyhaze.framework.core.exception.ApplicationException;
import com.flyhaze.framework.exception.LoginException;
import com.flyhaze.framework.mvc.view.TreeView;
import com.flyhaze.platform.entity.SysMenu;
import com.flyhaze.platform.service.ILoginService;
import com.flyhaze.platform.service.IMenuService;
import com.flyhaze.platform.service.IUserService;
import com.flyhaze.platform.vo.MenuVo;
import com.flyhaze.platform.vo.UserVo;
import com.flyhaze.utils.CryptoUtils;
import com.google.common.base.Objects;
import com.google.common.collect.Lists;

import lombok.extern.slf4j.Slf4j;

/**
 * LoginServiceImpl
 *
 * 登录相关接口实现
 *
 * @author liaowei
 * @date 创建时间：2018年4月6日 下午10:31:31 
 * @see com.flyhaze.platform.service.ILoginService
 * @since jdk1.8
 */
@Service("loginService")
@Slf4j
public class LoginServiceImpl implements ILoginService {

    @Resource(name = "userService")
    private IUserService userService;
    @Resource(name = "menuService")
    private IMenuService menuService;

    @Override
    public SessionUser<SysMenu, MenuVo> login(String userName, String password, String pwdSeed) throws ApplicationException {

        // 查询用户对象
        UserVo user = userService.findUserByUserName(userName);
        if (null == user) {
            log.info("用户登录：用户名=" + userName + "，用户不存在");
            return null;
        }

        // 比较密码
        String serverCiphertext = user.getPassword();
        try {
            serverCiphertext = CryptoUtils.toMD5(serverCiphertext + pwdSeed);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            log.error(e.getMessage(), e);
            throw new ApplicationException(e);
        }
        String clientCiphertext = CryptoUtils.base64Deconder(password);
        if (!Objects.equal(serverCiphertext, clientCiphertext)) {
            log.info("用户登录：用户名=" + userName + "，" + LoginException.PASSWORD_WRONG);
            throw new LoginException(LoginException.PASSWORD_WRONG);
        }

        // 用户信息
        String userId = user.getId();
        String siteCode = user.getSiteCode();
        SessionUser<SysMenu, MenuVo> sessionUser = new SessionUser<SysMenu, MenuVo>();
        sessionUser.setId(userId);
        sessionUser.setUserName(user.getUserName());
        sessionUser.setSiteCode(siteCode);

        List<MenuVo> menus = menuService.findSysMenusByUserId(userId, siteCode, true);
        if (null != menus && !menus.isEmpty()) {
            List<TreeView<SysMenu, MenuVo>> list = Lists.<TreeView<SysMenu, MenuVo>>newArrayList();
            for (MenuVo vo : menus) {
                list.add(new TreeView<SysMenu, MenuVo>(vo));
            }
            sessionUser.setMenuList(list);
        }

        return sessionUser;
    }
}