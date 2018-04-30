/**
 * mvc-framework
 * BaseController.java
 */
package com.liaowei.framework.controller;

import javax.servlet.http.HttpServletRequest;

import com.liaowei.framework.SessionUser;
import com.liaowei.framework.core.controller.BasisController;
import com.liaowei.framework.model.BaseModel;
import com.liaowei.framework.vo.BaseVo;

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
public abstract class BaseController<M extends BaseModel, V extends BaseVo>
        extends BasisController<M, V> {

    protected void setCurUser(SessionUser sessionUser, HttpServletRequest request) {
        request.getSession().setAttribute("sessionUser", sessionUser);
    }

    protected SessionUser getCurUser(HttpServletRequest request) {
        SessionUser sessionUser = (SessionUser) request.getSession().getAttribute("sessionUser");
        return sessionUser;
    }

    protected void removeCurUser(HttpServletRequest request) {
        request.getSession().removeAttribute("sessionUser");
    }

    protected void setSessionAttr(String key, Object value, HttpServletRequest request) {
        request.getSession().setAttribute(key, value);
    }

    protected Object getSessionAttr(String key, HttpServletRequest request) throws InstantiationException, IllegalAccessException {
        return request.getSession().getAttribute(key);
    }
}