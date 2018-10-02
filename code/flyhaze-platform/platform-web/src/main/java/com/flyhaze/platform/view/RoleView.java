/**
 * platform-web
 * RoleView.java
 */
package com.flyhaze.platform.view;

import com.flyhaze.core.view.IView;
import com.flyhaze.platform.enums.RoleTypeEnum;
import com.flyhaze.platform.vo.RoleVo;

import lombok.AllArgsConstructor;
import lombok.ToString;

/**
 * RoleView
 *
 * 角色表单View
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-26 09:17:39
 * @see com.flyhaze.core.view.IView
 * @since jdk1.8
 */
@SuppressWarnings("serial")
@AllArgsConstructor
@ToString
public class RoleView implements IView {

    private RoleVo v;

    public RoleView() {
        v = new RoleVo();
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
     * 角色编号
     */
    public String getRoleCode() {
        return v.getRoleCode();
    }

    /**
     * 角色编号
     */
    public void setRoleCode(String roleCode) {
        v.setRoleCode(roleCode);
    }

    /**
     * 角色名称
     */
    public String getRoleName() {
        return v.getRoleName();
    }

    /**
     * 角色名称
     */
    public void setRoleName(String roleName) {
        v.setRoleName(roleName);
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
     * 角色类型
     */
    public String getRoleType() {
        return v.getRoleType().name();
    }

    /**
     * 角色类型
     */
    public void setRoleType(String roleType) {
        v.setRoleType(RoleTypeEnum.valueOf(roleType));
    }

    /**
     * 是否内置
     */
    public Boolean getBuiltIn() {
        return v.getBuiltIn();
    }

    public RoleVo toVo() {
        return v;
    }
}