/**
 * platform-web
 * MenuListView.java
 */
package com.liaowei.platform.view;

import com.liaowei.platform.model.MenuModel;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * MenuListView
 *
 * 菜单列表View
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-22 00:57:23
 * @since jdk1.8
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@ToString
public class MenuListView {

    private MenuModel m;

    /**
     * 主键
     */
    public String getId() {
        return m.getId();
    }

    /**
     * 编号
     */
    public String getCode() {
        return m.getCode();
    }

    /**
     * 菜单文本
     */
    public String getText() {
        return m.getText();
    }

    /**
     * 菜单地址
     */
    public String getMenuUrl() {
        return m.getMenuUrl();
    }

    /**
     * 菜单类型
     */
    public String getMenuType() {
        return m.getMenuType().name();
    }

    /**
     * 分系统
     */
    public String getSubSystem() {
        return m.getSubSystem().name();
    }

    /**
     * 排序号
     */
    public Integer getOrderNum() {
        return m.getOrderNum();
    }
}