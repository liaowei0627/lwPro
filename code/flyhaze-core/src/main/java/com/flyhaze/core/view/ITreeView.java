/**
 * flyhaze-core
 * ITreeView.java
 */
package com.flyhaze.core.view;

import java.util.List;

import com.flyhaze.core.entity.IBasisTreeEntity;
import com.flyhaze.core.vo.IBasisTreeVo;

/**
 * ITreeView
 *
 * 前端tree数据View
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-26 21:05:48
 * @see com.flyhaze.core.view.IView
 * @since jdk1.8
 */
public interface ITreeView<E extends IBasisTreeEntity<E>, V extends IBasisTreeVo<E, V>>
        extends IView {

    public String getText();

    public String getState();

    public List<ITreeView<E, V>> getChildren();

    public V getAttributes();
}