/**
 * platform-web
 * MenuView.java
 */
package com.liaowei.platform.view;

import com.google.common.base.Strings;
import com.liaowei.platform.enums.MenuTypeEnum;
import com.liaowei.platform.enums.SubSystemEnum;
import com.liaowei.platform.model.MenuModel;

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
public class MenuView {

    private MenuModel m;

    public MenuView() {
        m = new MenuModel();
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
     * 编号
     */
    public String getCode() {
        return m.getCode();
    }

    /**
     * 编号
     */
    public void setCode(String code) {
        m.setCode(code);
    }

    /**
     * 菜单文本
     */
    public String getText() {
        return m.getText();
    }

    /**
     * 菜单文本
     */
    public void setText(String text) {
        m.setText(text);
    }

    /**
     * 菜单地址
     */
    public String getMenuUrl() {
        return m.getMenuUrl();
    }

    /**
     * 菜单地址
     */
    public void setMenuUrl(String menuUrl) {
        m.setMenuUrl(menuUrl);
    }

    /**
     * 菜单类型
     */
    public String getMenuType() {
        return m.getMenuType().name();
    }

    /**
     * 菜单类型
     */
    public void setMenuType(String menuType) {
        m.setMenuType(MenuTypeEnum.valueOf(menuType));
    }

    /**
     * 分系统
     */
    public String getSubSystem() {
        return m.getSubSystem().name();
    }

    /**
     * 分系统
     */
    public void setSubSystem(String subSystem) {
        m.setSubSystem(SubSystemEnum.valueOf(subSystem));
    }

    /**
     * 父菜单ID
     */
    public MenuView getParent() {
        MenuView view = null;
        MenuModel p = m.getParent();
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
            MenuModel p = m.getParent();
            if (null == p) {
                p = new MenuModel();
            }
            p.setId(parentId);
            m.setParent(p);
        }
    }

    /**
     * 排序号
     */
    public Integer getOrderNum() {
        return m.getOrderNum();
    }

    /**
     * 排序号
     */
    public void setOrderNum(Integer orderNum) {
        m.setOrderNum(orderNum);
    }

    public MenuModel toModel() {
        return m;
    }
}