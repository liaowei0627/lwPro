/**
 * platform-web
 * RoleListView.java
 */
package com.flyhaze.platform.view;

import com.flyhaze.core.view.IView;
import com.flyhaze.platform.vo.RoleVo;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * RoleListView
 *
 * 角色列表View
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
public class RoleListView implements IView {

    private RoleVo v;

    /**
     * 主键
     */
    public String getId() {
        return v.getId();
    }

    /**
     * 角色编号
     */
    public String getRoleCode() {
        return v.getRoleCode();
    }

    /**
     * 角色名称
     */
    public String getRoleName() {
        return v.getRoleName();
    }

    /**
     * 备注
     */
    public String getRemark() {
        return v.getRemark();
    }

    /**
     * 角色类型
     */
    public String getRoleType() {
        return v.getRoleType().getText();
    }

    /**
     * 是否内置
     */
    public Boolean getBuiltIn() {
        return v.getBuiltIn();
    }
}