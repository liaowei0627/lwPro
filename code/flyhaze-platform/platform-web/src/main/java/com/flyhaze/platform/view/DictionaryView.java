/**
 * platform-web
 * DictionaryView.java
 */
package com.flyhaze.platform.view;

import com.flyhaze.framework.core.view.IView;
import com.flyhaze.platform.vo.DictionaryVo;
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

    private DictionaryVo v;

    public DictionaryView() {
        v = new DictionaryVo();
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
     * 编号
     */
    public String getCode() {
        return v.getCode();
    }

    /**
     * 编号
     */
    public void setCode(String code) {
        v.setCode(code);
    }

    /**
     * 文本
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
     * 父菜单ID
     */
    public DictionaryView getParent() {
        DictionaryView view = null;
        DictionaryVo p = v.getParent();
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
            DictionaryVo p = v.getParent();
            if (null == p) {
                p = new DictionaryVo();
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

    public DictionaryVo toVo() {
        return v;
    }
}