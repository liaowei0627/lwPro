/**
 * platform-web
 * MenuView.java
 */
package com.liaowei.platform.view;

import com.liaowei.platform.model.SiteModel;

import lombok.AllArgsConstructor;
import lombok.ToString;

/**
 * MenuView
 *
 * 菜单表单View
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-26 09:17:39
 * @since jdk1.8
 */
@AllArgsConstructor
@ToString
public class SiteView {

    private SiteModel m;

    public SiteView() {
        m = new SiteModel();
    }

    /**
     * 主键
     */
    public String getId() {
        return m.getId();
    }

    /**
     * 主键
     */
    public void setId(String id) {
        m.setId(id);
    }

    /**
     * 站点编号
     */
    public String getSiteCode() {
        return m.getSiteCode();
    }

    /**
     * 编号
     */
    public void setSiteCode(String siteCode) {
        m.setSiteCode(siteCode);
    }

    /**
     * 站点名称
     */
    public String getSiteName() {
        return m.getSiteName();
    }

    /**
     * 菜单文本
     */
    public void setSiteName(String siteName) {
        m.setSiteName(siteName);
    }

    public SiteModel toModel() {
        return m;
    }
}