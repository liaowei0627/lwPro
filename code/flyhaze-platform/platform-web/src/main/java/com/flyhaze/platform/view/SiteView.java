/**
 * platform-web
 * MenuView.java
 */
package com.flyhaze.platform.view;

import com.flyhaze.framework.core.view.IView;
import com.flyhaze.platform.vo.SiteVo;

import lombok.AllArgsConstructor;
import lombok.ToString;

/**
 * MenuView
 *
 * 菜单表单View
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-26 09:17:39
 * @see com.flyhaze.framework.core.view.IView
 * @since jdk1.8
 */
@SuppressWarnings("serial")
@AllArgsConstructor
@ToString
public class SiteView implements IView {

    private SiteVo v;

    public SiteView() {
        v = new SiteVo();
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
     * 站点编号
     */
    public String getSiteCode() {
        return v.getSiteCode();
    }

    /**
     * 编号
     */
    public void setSiteCode(String siteCode) {
        v.setSiteCode(siteCode);
    }

    /**
     * 站点名称
     */
    public String getSiteName() {
        return v.getSiteName();
    }

    /**
     * 菜单文本
     */
    public void setSiteName(String siteName) {
        v.setSiteName(siteName);
    }

    public SiteVo toVo() {
        return v;
    }
}