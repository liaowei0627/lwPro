/**
 * platform-web
 * MenuListView.java
 */
package com.flyhaze.platform.view;

import com.flyhaze.framework.core.view.IView;
import com.flyhaze.platform.vo.SiteVo;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * MenuListView
 *
 * 菜单列表View
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-22 00:57:23
 * @see com.flyhaze.framework.core.view.IView
 * @since jdk1.8
 */
@SuppressWarnings("serial")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@ToString
public class SiteListView implements IView {

    private SiteVo v;

    /**
     * 主键
     */
    public String getId() {
        return v.getId();
    }

    /**
     * 站点编号
     */
    public String getSiteCode() {
        return v.getSiteCode();
    }

    /**
     * 站点名称
     */
    public String getSiteName() {
        return v.getSiteName();
    }
}