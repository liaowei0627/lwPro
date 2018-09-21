/**
 * platform-web
 * DictionaryListView.java
 */
package com.flyhaze.platform.view;

import com.flyhaze.framework.core.view.IView;
import com.flyhaze.platform.vo.DictionaryVo;

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
 * @see com.flyhaze.framework.core.view.IView
 * @since jdk1.8
 */
@SuppressWarnings("serial")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@ToString
public class DictionaryListView implements IView {

    private DictionaryVo v;

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
     * 文本
     */
    public String getText() {
        return v.getText();
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