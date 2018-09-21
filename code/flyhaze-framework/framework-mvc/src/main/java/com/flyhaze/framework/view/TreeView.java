/**
 * framework-mvc
 * TreeView.java
 */
package com.flyhaze.framework.view;

import java.util.List;

import com.flyhaze.framework.core.view.IView;
import com.flyhaze.framework.entity.BaseTreeEntity;
import com.flyhaze.framework.model.BaseTreeModel;
import com.flyhaze.framework.vo.BaseTreeVo;
import com.google.common.collect.Lists;

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
 * @see com.flyhaze.framework.core.view.IView
 * @since jdk1.8
 */
@SuppressWarnings("serial")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@ToString
public class TreeView<E extends BaseTreeEntity<E>, V extends BaseTreeVo<E, V>, M extends BaseTreeModel<E, V, M>>
        implements IView {

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

    public List<TreeView<E, V, M>> getChildren() {
        List<TreeView<E, V, M>> list = null;

        List<M> children = m.getChildren();
        if (null != children && !children.isEmpty()) {
            list = Lists.<TreeView<E, V, M>>newArrayList();
            for (M child : children) {
                list.add(new TreeView<E, V, M>(child));
            }
        }

        return list;
    }

    public M getAttributes() {
        return m;
    }
}