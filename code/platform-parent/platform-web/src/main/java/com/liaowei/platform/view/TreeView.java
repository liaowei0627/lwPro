/**
 * platform-web
 * TreeView.java
 */
package com.liaowei.platform.view;

import com.liaowei.framework.entity.BaseTreeEntity;
import com.liaowei.framework.model.BaseTreeModel;
import com.liaowei.framework.vo.BaseTreeVo;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * TreeView
 *
 * 前端tree数据View
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-26 21:05:48
 * @since jdk1.8
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@ToString
public class TreeView<E extends BaseTreeEntity<E>, V extends BaseTreeVo<E, V>, M extends BaseTreeModel<E, V, M>> {

    private M m;

    public String getId() {
        return m.getId();
    }

    public String getText() {
        return m.getText();
    }

    public String getState() {
        return m.getHasChild() ? "closed" : "open";
    }

    public M getAttributes() {
        return m;
    }
}