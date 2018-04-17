/**
 * platform-web
 * MenuListView.java
 */
package com.liaowei.platform.view;

import com.liaowei.platform.model.MenuModel;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * MenuListView
 *
 * 菜单列表View
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-22 00:57:23
 * @since jdk1.8
 */
@NoArgsConstructor
@AllArgsConstructor
public class MenuListView {

    private MenuModel o;

    public String getMenuText() {
        return o.getMenuText();
    }

    public String getMenuUrl() {
        return o.getMenuUrl();
    }

    public String getMenuType() {
        return o.getMenuType().name();
    }
}