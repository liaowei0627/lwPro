/**
 * platform-web
 * SiteView.java
 */
package com.flyhaze.platform.view;

import com.flyhaze.core.view.IView;
import com.flyhaze.platform.vo.SiteVo;

import lombok.AllArgsConstructor;
import lombok.ToString;

/**
 * SiteView
 *
 * 站点表单View
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-26 09:17:39
 * @see com.flyhaze.core.view.IView
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
     * 站点编号
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
     * 站点名称
     */
    public void setSiteName(String siteName) {
        v.setSiteName(siteName);
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

    public SiteVo toVo() {
        return v;
    }
}