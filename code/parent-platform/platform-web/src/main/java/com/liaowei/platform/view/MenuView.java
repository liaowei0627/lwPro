/**
 * platform-web
 * MenuView.java
 */
package com.liaowei.platform.view;

import java.time.LocalDateTime;

import com.google.common.base.Strings;
import com.liaowei.platform.enums.MenuTypeEnum;
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
     * 父菜单ID
     */
    public String getParentId() {
        MenuModel p = m.getParent();
        String parentId;
        if (null != p) {
            parentId = p.getParent().getId();
        } else {
            parentId = "";
        }
        return parentId;
    }

    /**
     * 父菜单ID
     */
    public void setParentId(String parentId) {
        MenuModel p = m.getParent();
        if (null == p) {
            p = new MenuModel();
        }
        p.setId(parentId);
        m.setParent(p);
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
        if (Strings.isNullOrEmpty(m.getId())) {
            m.setId(null);
            m.setValid(Boolean.TRUE);
            m.setCreateTime(LocalDateTime.now());
        }
        m.setModifyTime(LocalDateTime.now());
        return m;
    }
}