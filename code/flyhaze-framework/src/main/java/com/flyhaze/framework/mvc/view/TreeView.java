/**
 * flyhaze-framework
 * TreeView.java
 */
package com.flyhaze.framework.mvc.view;

import java.util.List;

import com.flyhaze.core.view.ITreeView;
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
 * @see com.flyhaze.core.view.IView
 * @since jdk1.8
 */
@SuppressWarnings("serial")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@ToString
public class TreeView<E extends BaseTreeEntity<E>, V extends BaseTreeVo<E, V>>
        implements ITreeView<E, V> {

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

    public List<ITreeView<E, V>> getChildren() {
        List<ITreeView<E, V>> list = null;

        List<V> children = v.getChildren();
        if (null != children && !children.isEmpty()) {
            list = Lists.<ITreeView<E, V>>newArrayList();
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