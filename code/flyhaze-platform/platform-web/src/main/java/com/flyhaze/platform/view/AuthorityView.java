/**
 * platform-web
 * MenuView.java
 */
package com.flyhaze.platform.view;

import com.flyhaze.framework.core.view.IView;
import com.flyhaze.platform.enums.AuthTypeEnum;
import com.flyhaze.platform.vo.AuthorityVo;

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
public class AuthorityView implements IView {

    private AuthorityVo v;

    public AuthorityView() {
        v = new AuthorityVo();
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
     * 权限编号
     */
    public String getAuthCode() {
        return v.getAuthCode();
    }

    /**
     * 权限编号
     */
    public void setAuthCode(String authCode) {
        v.setAuthCode(authCode);
    }

    /**
     * 权限名称
     */
    public String getAuthName() {
        return v.getAuthName();
    }

    /**
     * 权限名称
     */
    public void setAuthName(String authName) {
        v.setAuthName(authName);
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
     * 权限类型
     */
    public String getAuthType() {
        return v.getAuthType().name();
    }

    /**
     * 权限类型
     */
    public void setAuthType(String authType) {
        v.setAuthType(AuthTypeEnum.valueOf(authType));
    }

    /**
     * 是否内置
     */
    public Boolean getBuiltIn() {
        return v.getBuiltIn();
    }

    public AuthorityVo toVo() {
        return v;
    }
}