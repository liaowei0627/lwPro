/**
 * platform-web
 * MenuListView.java
 */
package com.flyhaze.platform.view;

import com.flyhaze.framework.core.view.IView;
import com.flyhaze.platform.vo.MenuVo;

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
 * @see com.flyhaze.framework.core.view.IView
 * @since jdk1.8
 */
@SuppressWarnings("serial")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@ToString
public class MenuListView implements IView {

    private MenuVo v;

    /**
     * 主键
     */
    public String getId() {
        return v.getId();
    }

    /**
     * 编号
     */
    public String getCode() {
        return v.getCode();
    }

    /**
     * 菜单文本
     */
    public String getText() {
        return v.getText();
    }

    /**
     * 备注
     */
    public String getRemark() {
        return v.getRemark();
    }

    /**
     * 菜单地址
     */
    public String getMenuUrl() {
        return v.getMenuUrl();
    }

    /**
     * 菜单类型
     */
    public String getMenuType() {
        return v.getMenuType().getText();
    }

    /**
     * 分系统
     */
    public String getSubSystem() {
        return v.getSubSystem().getText();
    }

    /**
     * 全路径编号
     */
    public String getFullCode() {
        return v.getFullCode();
    }

    /**
     * 全路径文本
     */
    public String getFullText() {
        return v.getFullText();
    }

    /**
     * 排序号
     */
    public Integer getOrderNum() {
        return v.getOrderNum();
    }
}