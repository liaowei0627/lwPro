/**
 * flyhaze-framework
 * TreeView.java
 */
package com.flyhaze.framework.mvc.view;

import java.util.List;

import com.flyhaze.framework.core.view.IView;
import com.flyhaze.framework.hibernate.entity.BaseTreeEntity;
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
public class TreeView<E extends BaseTreeEntity<E>, V extends BaseTreeVo<E, V>>
        implements IView {

    private V v;

    public String getId() {
        return v.getId();
    }

    public String getText() {
        return v.getText();
    }

    public String getState() {
        return v.getHasChild() ? "closed" : "open";
    }

    public List<TreeView<E, V>> getChildren() {
        List<TreeView<E, V>> list = null;

        List<V> children = v.getChildren();
        if (null != children && !children.isEmpty()) {
            list = Lists.<TreeView<E, V>>newArrayList();
            for (V child : children) {
                list.add(new TreeView<E, V>(child));
            }
        }

        return list;
    }

    public V getAttributes() {
        return v;
    }
}