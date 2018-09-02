/**
 * mvc-framework
 * BaseController.java
 */
package com.liaowei.framework.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.liaowei.framework.SessionUser;
import com.liaowei.framework.core.controller.BasisController;
import com.liaowei.framework.model.BaseIdModel;
import com.liaowei.framework.query.Where;
import com.liaowei.framework.vo.BaseIdVo;

import lombok.extern.slf4j.Slf4j;

/**
 * BaseController
 *
 * spring mvc controller基类 处理异常，公共方法
 *
 * @author liaowei
 * @date 创建时间：2018年4月3日 下午10:42:34
 * @see com.liaowei.framework.core.controller.BasisController<M, V, E>
 * @since jdk1.8
 */
@Slf4j
public abstract class BaseController<M extends BaseIdModel, V extends BaseIdVo>
        extends BasisController<M, V> {

    private static final String USER_SESSION_KEY = "USER_SESSION_KEY";

    @Resource
    protected HttpServletRequest request;
    @Resource
    protected HttpServletResponse response;

    protected Where createWhere(Where where) {
        if (null == where) {
            
        }
        return where;
    }

    /**
     * session中添加当前登录用户信息
     * 
     * @param sessionUser
     */
    protected void setCurUser(SessionUser sessionUser) {
        request.getSession().setAttribute(USER_SESSION_KEY, sessionUser);
    }

    /**
     * session中获取当前登录用户信息
     * 
     * @return
     */
    protected SessionUser getCurUser() {
        SessionUser sessionUser = (SessionUser) request.getSession().getAttribute(USER_SESSION_KEY);
        return sessionUser;
    }

    /**
     * session中删除当前登录用户信息
     */
    protected void removeCurUser() {
        request.getSession().removeAttribute(USER_SESSION_KEY);
    }

    /**
     * 添加session属性
     * 
     * @param key
     * @param value
     */
    protected void setSessionAttr(String key, Object value) {
        request.getSession().setAttribute(key, value);
    }

    /**
     * 取得session属性
     * 
     * @param key
     * @return
     */
    protected Object getSessionAttr(String key) {
        return request.getSession().getAttribute(key);
    }

    /**
     * 返回图片
     * 
     * @param bi
     */
    protected void responseJPEG(BufferedImage bi) {
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");

        ServletOutputStream out = null;
        try {
            out = response.getOutputStream();
            ImageIO.write(bi, "jpg", out);
            out.flush();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        } finally {
            try {
                if (null != out) {
                    out.close();
                }
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
        }
    }
}