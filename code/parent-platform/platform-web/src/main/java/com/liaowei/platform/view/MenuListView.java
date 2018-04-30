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

    public String getId() {
        return m.getId();
    }

    public String getText() {
        return m.getText();
    }

    public String getMenuUrl() {
        return m.getMenuUrl();
    }

    public String getMenuType() {
        return m.getMenuType().name();
    }
}