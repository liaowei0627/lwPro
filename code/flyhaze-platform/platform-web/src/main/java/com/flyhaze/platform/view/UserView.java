/**
 * platform-web
 * UserView.java
 */
package com.flyhaze.platform.view;

import com.flyhaze.core.view.IView;
import com.flyhaze.platform.enums.SubSystemEnum;
import com.flyhaze.platform.vo.SiteVo;
import com.flyhaze.platform.vo.UserVo;
import com.google.common.base.Strings;

import lombok.AllArgsConstructor;
import lombok.ToString;

/**
 * UserView
 *
 * 用户表单View
 *
 * @useror 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-26 09:17:39
 * @see com.flyhaze.core.view.IView
 * @since jdk1.8
 */
@SuppressWarnings("serial")
@AllArgsConstructor
@ToString
public class UserView implements IView {

    private UserVo v;

    public UserView() {
        v = new UserVo();
    }

    /**
     * 主键
     */
    public String getId() {
        return v.getId();
    }

    /**
     * 主键
     */
    public void setId(String id) {
        v.setId(id);
    }

    /**
     * 用户名
     */
    public String getUserName() {
        return v.getUserName();
    }

    /**
     * 用户名
     */
    public void setUserName(String userName) {
        v.setUserName(userName);
    }

    /**
     * 密码
     */
    public void setPassword(String password) {
        v.setPassword(password);
    }

    /**
     * 备注
     */
    public String getRemark() {
        return v.getRemark();
    }

    /**
     * 备注
     */
    public void setRemark(String remark) {
        v.setRemark(remark);
    }

    /**
     * 分系统
     */
    public String getSubSystem() {
        return v.getSubSystem().name();
    }

    /**
     * 分系统
     */
    public void setSubSystem(String subSystem) {
        v.setSubSystem(SubSystemEnum.valueOf(subSystem));
    }

    /**
     * 站点
     */
    public SiteView getSite() {
        SiteView siteView = null;
        SiteVo site = v.getSite();
        if (null != site) {
            siteView = new SiteView(site);
        }
        return siteView;
    }

    /**
     * 站点编号
     */
    public void setSiteCode(String siteCode) {
        if (!Strings.isNullOrEmpty(siteCode)) {
            SiteVo site = v.getSite();
            if (null == site) {
                site = new SiteVo();
            }
            site.setSiteCode(siteCode);
            v.setSite(site);
        }
    }

    /**
     * 是否内置
     */
    public Boolean getBuiltIn() {
        return v.getBuiltIn();
    }

    public UserVo toVo() {
        return v;
    }
}