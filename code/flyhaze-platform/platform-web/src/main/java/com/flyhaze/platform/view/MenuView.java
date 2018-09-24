/**
 * platform-web
 * MenuView.java
 */
package com.flyhaze.platform.view;

import com.flyhaze.framework.core.view.IView;
import com.flyhaze.platform.enums.MenuTypeEnum;
import com.flyhaze.platform.enums.SubSystemEnum;
import com.flyhaze.platform.vo.MenuVo;
import com.google.common.base.Strings;

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
public class MenuView implements IView {

    private MenuVo v;

    public MenuView() {
        v = new MenuVo();
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
     * 菜单编号
     */
    public String getCode() {
        return v.getCode();
    }

    /**
     * 菜单编号
     */
    public void setCode(String code) {
        v.setCode(code);
    }

    /**
     * 菜单文本
     */
    public String getText() {
        return v.getText();
    }

    /**
     * 菜单文本
     */
    public void setText(String text) {
        v.setText(text);
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
     * 菜单地址
     */
    public String getMenuUrl() {
        return v.getMenuUrl();
    }

    /**
     * 菜单地址
     */
    public void setMenuUrl(String menuUrl) {
        v.setMenuUrl(menuUrl);
    }

    /**
     * 菜单类型
     */
    public String getMenuType() {
        return v.getMenuType().name();
    }

    /**
     * 菜单类型
     */
    public void setMenuType(String menuType) {
        v.setMenuType(MenuTypeEnum.valueOf(menuType));
    }

    /**
     * 分系统
     */
    public String getSubSystem() {
        return v.getSubSystem().name();
    }

    /**
     * 分系统
     */
    public void setSubSystem(String subSystem) {
        v.setSubSystem(SubSystemEnum.valueOf(subSystem));
    }

    /**
     * 父菜单ID
     */
    public MenuView getParent() {
        MenuView view = null;
        MenuVo p = v.getParent();
        if (null != p) {
            view = new MenuView(p);
        }
        return view;
    }

    /**
     * 父菜单ID
     */
    public void setParentId(String parentId) {
        if (!Strings.isNullOrEmpty(parentId)) {
            MenuVo p = v.getParent();
            if (null == p) {
                p = new MenuVo();
            }
            p.setId(parentId);
            v.setParent(p);
        }
    }

    /**
     * 排序号
     */
    public Integer getOrderNum() {
        return v.getOrderNum();
    }

    /**
     * 排序号
     */
    public void setOrderNum(Integer orderNum) {
        v.setOrderNum(orderNum);
    }

    public MenuVo toVo() {
        return v;
    }
}