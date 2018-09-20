/**
 * platform-web
 * DictionaryListView.java
 */
package com.liaowei.platform.view;

import com.liaowei.framework.core.view.IView;
import com.liaowei.platform.model.DictionaryModel;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * DictionaryListView
 *
 * 数据字典列表View
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
public class DictionaryListView implements IView {

    private DictionaryModel m;

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
     * 文本
     */
    public String getText() {
        return m.getText();
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