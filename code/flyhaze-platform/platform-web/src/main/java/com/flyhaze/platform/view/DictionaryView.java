/**
 * platform-web
 * DictionaryView.java
 */
package com.flyhaze.platform.view;

import com.flyhaze.framework.core.view.IView;
import com.flyhaze.platform.model.DictionaryModel;
import com.google.common.base.Strings;

import lombok.AllArgsConstructor;
import lombok.ToString;

/**
 * DictionaryView
 *
 * 数据字典表单View
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-26 09:17:39
 * @see com.flyhaze.framework.core.view.IView
 * @since jdk1.8
 */
@SuppressWarnings("serial")
@AllArgsConstructor
@ToString
public class DictionaryView implements IView {

    private DictionaryModel m;

    public DictionaryView() {
        m = new DictionaryModel();
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
     * 文本
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
     * 父菜单ID
     */
    public DictionaryView getParent() {
        DictionaryView view = null;
        DictionaryModel p = m.getParent();
        if (null != p) {
            view = new DictionaryView(p);
        }
        return view;
    }

    /**
     * 父菜单ID
     */
    public void setParentId(String parentId) {
        if (!Strings.isNullOrEmpty(parentId)) {
            DictionaryModel p = m.getParent();
            if (null == p) {
                p = new DictionaryModel();
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

    public DictionaryModel toModel() {
        return m;
    }
}