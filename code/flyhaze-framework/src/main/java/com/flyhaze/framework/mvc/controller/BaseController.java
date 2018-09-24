/**
 * flyhaze-framework
 * BaseController.java
 */
package com.flyhaze.framework.mvc.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.support.RequestContextUtils;

import com.flyhaze.framework.SessionUser;
import com.flyhaze.framework.core.controller.BasisController;
import com.flyhaze.framework.core.page.Pagination;
import com.flyhaze.framework.core.query.Where;
import com.flyhaze.framework.core.query.exception.WhereClauseException;
import com.flyhaze.framework.core.query.operator.OneValueComparisonOperator;
import com.flyhaze.framework.hibernate.entity.BaseIdEntity;
import com.flyhaze.framework.vo.BaseIdVo;
import com.flyhaze.framework.vo.BaseVo;
import com.flyhaze.utils.SeedUtils;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;

import lombok.extern.slf4j.Slf4j;

/**
 * BaseController
 *
 * spring mvc controller基类<br>
 * 处理异常，公共方法
 *
 * @author liaowei
 * @date 创建时间：2018年4月3日 下午10:42:34
 * @see com.flyhaze.framework.core.controller.BasisController<E, V>
 * @since jdk1.8
 */
@Slf4j
public abstract class BaseController<E extends BaseIdEntity<E>, V extends BaseIdVo<E, V>> extends BasisController<E, V> {

    protected static final String OPT_COPY = "copy";

    @Resource
    protected HttpServletRequest request;

    /**
     * 从国际化资源配置文件中根据key获取value
     * 
     * @param request
     * @param key
     * @return
     */
    protected String getMessage(String basename, String key) {
        Locale currentLocale = RequestContextUtils.getLocale(request);
        ResourceBundle bundle = ResourceBundle.getBundle(basename, currentLocale);
        return bundle.getString(key);
    }

    /**
     * 配置Model对象，添加当前用户信息
     * 
     * @param v 要配置的Vo对象
     */
    @SuppressWarnings("rawtypes")
    protected void configVo(V v) {
        SessionUser sessionUser = getCurUser();
        String userName = sessionUser.getUserName();
        if (Strings.isNullOrEmpty(v.getId())) {
            v.setId(null);
            if (v instanceof BaseVo) {
                BaseVo vo = (BaseVo) v;
                vo.setValid(Boolean.TRUE);
                vo.setCreator(userName);
            }
        }
        if (v instanceof BaseVo) {
            BaseVo vo = (BaseVo) v;
            vo.setReviser(userName);
        }
    }

    /**
     * 配置Where对象
     * 
     * @param where 要配置的Where对象
     * @param keywords 要做为关键字模糊查询的字段名数组
     * @return
     * @throws WhereClauseException
     */
    protected Where configWhere(Where where, String[] keywords) throws WhereClauseException {
        return configWhere(where, keywords, null);
    }

    /**
     * 配置Where对象
     * 
     * @param where 要配置的Where对象
     * @param keywords 要做为关键字模糊查询的字段名数组
     * @param excKeys 要从request parameter中排除做为查询条件的参数名数组
     * @return
     * @throws WhereClauseException
     */
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
        excKeyList.add("_");
        if (null != excKeys && excKeys.length > 0) {
            excKeyList.addAll(Lists.newArrayList(excKeys));
        }

        String key;
        while (names.hasMoreElements()) {
            key = names.nextElement();
            if (!excKeyList.contains(key)) {
                value = request.getParameter(key);
                if (!Strings.isNullOrEmpty(value)) {
                    if (key.endsWith("_l")) {
                        if (null == where) {
                            where = Where.rootWhere(key.replace("_l", ""), OneValueComparisonOperator.LIKE, value);
                        } else {
                            where.childAndWhere(key.replace("_l", ""), OneValueComparisonOperator.LIKE, value);
                        }
                    } else if (key.endsWith("_s")) {
                        if (null == where) {
                            where = Where.rootWhere(key.replace("_s", ""), OneValueComparisonOperator.GE, value);
                        } else {
                            where.childAndWhere(key.replace("_s", ""), OneValueComparisonOperator.GE, value);
                        }
                    } else if (key.endsWith("_e")) {
                        if (null == where) {
                            where = Where.rootWhere(key.replace("_e", ""), OneValueComparisonOperator.LE, value);
                        } else {
                            where.childAndWhere(key.replace("_e", ""), OneValueComparisonOperator.LE, value);
                        }
                    } else {
                        if (null == where) {
                            where = Where.rootWhere(key, OneValueComparisonOperator.EQ, value);
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
     * 配置分页对象
     * 
     * @return
     */
    protected Pagination<?> configPagination() {
        Pagination<?> p = new Pagination<>();

        String page = request.getParameter("page");
        if (!Strings.isNullOrEmpty(page)) {
            p.setPage(Integer.valueOf(page).intValue());
        }
        String rows = request.getParameter("rows");
        if (!Strings.isNullOrEmpty(rows)) {
            p.setRows(Integer.valueOf(rows).intValue());
        }

        return p;
    }

    /**
     * session中添加当前登录用户信息
     * 
     * @param sessionUser
     */
    protected void setCurUser(@SuppressWarnings("rawtypes") SessionUser sessionUser) {
        request.getSession().setAttribute(SessionUser.USER_SESSION_KEY, sessionUser);
    }

    /**
     * session中获取当前登录用户信息
     * 
     * @return
     */
    @SuppressWarnings("rawtypes")
    protected SessionUser getCurUser() {
        SessionUser sessionUser = (SessionUser) request.getSession().getAttribute(SessionUser.USER_SESSION_KEY);
        return sessionUser;
    }

    /**
     * session中删除当前登录用户信息
     */
    protected void removeCurUser() {
        request.getSession().removeAttribute(SessionUser.USER_SESSION_KEY);
    }

    /**
     * session中添加当前登录用户信息
     * 
     * @param sessionUser
     */
    protected String setPwdSeed() {
        String seed = SeedUtils.getRandomString(6);
        request.getSession().setAttribute(SeedUtils.PWD_SEED, seed);
        return seed;
    }

    /**
     * session中获取当前登录用户信息
     * 
     * @return
     */
    protected String getPwdSeed() {
        String pwdSeed = String.valueOf(request.getSession().getAttribute(SeedUtils.PWD_SEED));
        return pwdSeed;
    }

    /**
     * session中删除当前登录用户信息
     */
    protected void removePwdSeed() {
        request.getSession().removeAttribute(SeedUtils.PWD_SEED);
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
     * 删除session属性
     * 
     * @param key
     */
    protected void removeSessionAttr(String key) {
        request.getSession().removeAttribute(key);
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