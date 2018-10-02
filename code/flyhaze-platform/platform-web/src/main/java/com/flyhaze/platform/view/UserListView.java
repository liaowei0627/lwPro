/**
 * platform-web
 * UserListView.java
 */
package com.flyhaze.platform.view;

import com.flyhaze.core.view.IView;
import com.flyhaze.platform.vo.SiteVo;
import com.flyhaze.platform.vo.UserVo;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * UserListView
 *
 * 用户列表View
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-22 00:57:23
 * @see com.flyhaze.core.view.IView
 * @since jdk1.8
 */
@SuppressWarnings("serial")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@ToString
public class UserListView implements IView {

    private UserVo v;

    /**
     * 主键
     */
    public String getId() {
        return v.getId();
    }

    /**
     * 用户名
     */
    public String getUserName() {
        return v.getUserName();
    }

    /**
     * 备注
     */
    public String getRemark() {
        return v.getRemark();
    }

    /**
     * 分系统
     */
    public String getSubSystem() {
        return v.getSubSystem().getText();
    }

    /**
     * 站点名称
     */
    public String getSiteName() {
        String siteName = null;
        SiteVo site = v.getSite();
        if (null != site) {
            siteName = site.getSiteName();
        }
        return siteName;
    }

    /**
     * 是否内置
     */
    public Boolean getBuiltIn() {
        return v.getBuiltIn();
    }
}