/**
 * framework-core
 * IBasisTreeEntity.java
 */
package com.liaowei.framework.core.model;

import java.util.Set;

/**
 * IBasisTreeEntity
 *
 * Model类顶层树形结构属性接口
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-17 03:39:01
 * @see com.liaowei.framework.core.entity.IBasisEntity
 * @since jdk1.8
 */
public interface IBasisTreeModel<M> extends IBasisModel {

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
    public Set<M> getChildren();

    /**
     * 下级数据集合
     */
    public void setChildren(Set<M> children);

    /**
     * 是否有子节点
     */
    public Boolean getHasChild();
}