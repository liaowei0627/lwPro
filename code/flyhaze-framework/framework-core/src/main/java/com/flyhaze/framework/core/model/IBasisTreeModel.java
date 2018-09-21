/**
 * framework-core
 * IBasisTreeEntity.java
 */
package com.flyhaze.framework.core.model;

import java.util.List;

import com.flyhaze.framework.core.entity.IBasisTreeEntity;
import com.flyhaze.framework.core.vo.IBasisTreeVo;

/**
 * IBasisTreeEntity
 *
 * Model类顶层树形结构属性接口
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-17 03:39:01
 * @see com.flyhaze.framework.core.model.IBasisModel<E, V, M>
 * @since jdk1.8
 */
public interface IBasisTreeModel<E extends IBasisTreeEntity<E>, V extends IBasisTreeVo<E, V>, M extends IBasisTreeModel<E, V, M>>
        extends IBasisModel<E, V, M> {

    /**
     * 将Model属性复制到新的Vo对象
     */
    V copyToVo(M m);

    /**
     * 上级数据
     */
    M getParent();

    /**
     * 上级数据
     */
    void setParent(M parent);

    /**
     * 下级数据集合
     */
    public List<M> getChildren();

    /**
     * 下级数据集合
     */
    public void setChildren(List<M> children);

    /**
     * 是否有子节点
     */
    public Boolean getHasChild();
}