/**
 * platform-web
 * MenuListView.java
 */
package com.liaowei.platform.view;

import com.liaowei.platform.model.SiteModel;

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
 * @since jdk1.8
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@ToString
public class SiteListView {

    private SiteModel m;

    /**
     * 主键
     */
    public String getId() {
        return m.getId();
    }

    /**
     * 站点编号
     */
    public String getSiteCode() {
        return m.getSiteCode();
    }

    /**
     * 站点名称
     */
    public String getSiteName() {
        return m.getSiteName();
    }
}