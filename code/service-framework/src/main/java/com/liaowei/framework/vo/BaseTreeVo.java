/**
 * service-framework
 * BaseTreeVo.java
 */
package com.liaowei.framework.vo;

import java.time.LocalDateTime;
import java.util.Set;

import com.liaowei.framework.core.vo.IBasisTreeVo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * BaseTreeVo
 *
 * Spring服务层向控制层传递树形数据的封装类基类
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-19 22:51:07
 * @see com.liaowei.framework.vo.BaseVo
 * @since jdk1.8
 */
@SuppressWarnings("serial")
@ToString(callSuper = true)
public abstract class BaseTreeVo<V> extends BaseVo implements IBasisTreeVo<V> {

    /**
     * 上级数据
     */
    @Getter
    @Setter
    private V parent;

    /**
     * 下级数据集合
     */
    @Getter
    @Setter
    private Set<V> children;

    /**
     * 顺序
     */
    @Getter
    @Setter
    private Integer orderNum;

    /**
     * 是否有子节点
     */
    private Boolean hasChild;

    public BaseTreeVo() {
        super();
    }

    public BaseTreeVo(String id, V parent, Set<V> children, Integer orderNum, Boolean valid, String creator, LocalDateTime createTime, String reviser, LocalDateTime modifyTime) {
        super(id, valid, creator, createTime, reviser, modifyTime);
        this.parent = parent;
        this.children = children;
        this.orderNum = orderNum;
    }

    /**
     * 是否有子节点
     */
    public Boolean getHasChild() {
        if (null == children || children.isEmpty()) {
            this.hasChild = false;
        } else {
            this.hasChild = true;
        }
        return hasChild;
    }
}