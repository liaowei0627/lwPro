/**
 * framework-core
 * IBasisTreeVo.java
 */
package com.liaowei.framework.core.vo;

import java.util.List;

import com.liaowei.framework.core.entity.IBasisTreeEntity;

/**
 * IBasisTreeVo
 *
 * 用于服务层向控制层传递数据的树形结构属性接口
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-21 01:01:25
 * @see com.liaowei.framework.core.vo.IBasisIdVo
 * @since jdk1.8
 */
public interface IBasisTreeVo<E extends IBasisTreeEntity<E>, V extends IBasisTreeVo<E, V>> extends IBasisVo<E, V> {

    /**
     * 将Vo属性复制到新的Entity对象
     */
    E copyToEntity(V v);

    /**
     * 上级数据
     */
    V getParent();

    /**
     * 上级数据
     */
    void setParent(V parent);

    /**
     * 下级数据集合
     */
    public List<V> getChildren();

    /**
     * 下级数据集合
     */
    public void setChildren(List<V> children);

    /**
     * 是否有子节点
     */
    public Boolean getHasChild();
}