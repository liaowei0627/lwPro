/**
 * platform-web
 * MenuListView.java
 */
package com.liaowei.platform.view;

import com.liaowei.framework.core.view.IView;
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
 * @see com.liaowei.framework.core.view.IView
 * @since jdk1.8
 */
@SuppressWarnings("serial")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@ToString
public class MenuListView implements IView {

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
        return m.getMenuType().getText();
    }

    /**
     * 分系统
     */
    public String getSubSystem() {
        return m.getSubSystem().getText();
    }

    /**
     * 全路径编号
     */
    public String getFullCode() {
        return m.getFullCode();
    }

    /**
     * 全路径文本
     */
    public String getFullText() {
        return m.getFullText();
    }

    /**
     * 排序号
     */
    public Integer getOrderNum() {
        return m.getOrderNum();
    }
}