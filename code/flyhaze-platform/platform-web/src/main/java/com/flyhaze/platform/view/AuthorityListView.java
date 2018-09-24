/**
 * platform-web
 * AuthorityListView.java
 */
package com.flyhaze.platform.view;

import com.flyhaze.framework.core.view.IView;
import com.flyhaze.platform.vo.AuthorityVo;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * AuthorityListView
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
public class AuthorityListView implements IView {

    private AuthorityVo v;

    /**
     * 主键
     */
    public String getId() {
        return v.getId();
    }

    /**
     * 权限编号
     */
    public String getAuthCode() {
        return v.getAuthCode();
    }

    /**
     * 权限名称
     */
    public String getAuthName() {
        return v.getAuthName();
    }

    /**
     * 备注
     */
    public String getRemark() {
        return v.getRemark();
    }

    /**
     * 权限类型
     */
    public String getAuthType() {
        return v.getAuthType().getText();
    }

    /**
     * 是否内置
     */
    public Boolean getBuiltIn() {
        return v.getBuiltIn();
    }
}