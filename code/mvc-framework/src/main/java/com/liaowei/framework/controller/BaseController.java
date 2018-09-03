/**
 * mvc-framework
 * BaseController.java
 */
package com.liaowei.framework.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.liaowei.framework.SessionUser;
import com.liaowei.framework.core.controller.BasisController;
import com.liaowei.framework.entity.BaseIdEntity;
import com.liaowei.framework.model.BaseIdModel;
import com.liaowei.framework.query.Where;
import com.liaowei.framework.query.exception.WhereClauseException;
import com.liaowei.framework.query.operator.OneValueComparisonOperator;
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
public abstract class BaseController<E extends BaseIdEntity<E>, V extends BaseIdVo<E, V>, M extends BaseIdModel<E, V, M>>
        extends BasisController<E, V, M> {

    private static final String USER_SESSION_KEY = "USER_SESSION_KEY";

    @Resource
    protected HttpServletRequest request;

    protected Where configWhere(Where where, String[] keywords, String[] excKeys) throws WhereClauseException {
        Enumeration<String> names = request.getParameterNames();

        String value;
        if (null != keywords && keywords.length > 0) {
            value = request.getParameter("keyword");
            if (!Strings.isNullOrEmpty(value)) {
                for (String keyword : keywords) {
                    if (null == where) {
                        where = Where.rootWhere(keyword, OneValueComparisonOperator.LIKE, value);
                    } else {
                        where.childAndWhere(keyword, OneValueComparisonOperator.LIKE, value);
                    }
                }
            }
        }

        List<String> excKeyList = Lists.<String>newArrayList();
        excKeyList.add("keyword");
        excKeyList.add("page");
        excKeyList.add("rows");
        if (null != excKeys && excKeys.length > 0) {
            excKeyList.addAll(Lists.newArrayList(excKeys));
        }

        String key;
        while (names.hasMoreElements()) {
            key = names.nextElement();
            if (!excKeyList.contains(key)) {
                value = request.getParameter(key);
                if (!Strings.isNullOrEmpty(value)) {
                    if (null == where) {
                        if (key.endsWith("_l")) {
                            where = Where.rootWhere(key.replace("_l", ""), OneValueComparisonOperator.LIKE, value);
                        } else {
                            where = Where.rootWhere(key, OneValueComparisonOperator.EQ, value);
                        }
                    } else {
                        if (key.endsWith("_l")) {
                            where.childAndWhere(key.replace("_l", ""), OneValueComparisonOperator.LIKE, value);
                        } else {
                            where.childAndWhere(key, OneValueComparisonOperator.EQ, value);
                        }
                    }
                }
            }
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
     * @param response 
     */
    protected void responseJPEG(BufferedImage bi, HttpServletResponse response) {
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