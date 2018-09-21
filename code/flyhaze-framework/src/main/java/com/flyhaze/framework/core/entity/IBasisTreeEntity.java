/**
 * flyhaze-framework
 * IBasisTreeEntity.java
 */
package com.flyhaze.framework.core.entity;

import java.util.Set;

/**
 * IBasisTreeEntity
 *
 * 实体类顶层树形结构接口<br>
 * Entity用于数据层与服务层传递数据<br>
 * 不论用何种实体层框架，其自链接树形结构实体类均需继承此接口
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-17 03:39:01
 * @see com.flyhaze.framework.core.entity.IBasisEntity<E>
 * @since jdk1.8
 */
public interface IBasisTreeEntity<E extends IBasisTreeEntity<E>> extends IBasisEntity<E> {

    /**
     * 上级数据
     */
    E getParent();

    /**
     * 上级数据
     */
    void setParent(E parent);

    /**
     * 下级数据集合
     */
    public Set<E> getChildren();

    /**
     * 下级数据集合
     */
    public void setChildren(Set<E> children);

    /**
     * 是否有子节点
     */
    public Boolean getHasChild();
}