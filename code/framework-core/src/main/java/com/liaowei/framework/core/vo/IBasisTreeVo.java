/**
 * framework-core
 * IBasisTreeVo.java
 */
package com.liaowei.framework.core.vo;

import java.util.Set;

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
public interface IBasisTreeVo<V> extends IBasisIdVo {


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
    public Set<V> getChildren();

    /**
     * 下级数据集合
     */
    public void setChildren(Set<V> children);

    /**
     * 是否有子节点
     */
    public Boolean getHasChild();
}