/**
 * mvc-framework
 * BaseTreeModel.java
 */
package com.liaowei.framework.model;

import java.time.LocalDateTime;
import java.util.Set;

import com.liaowei.framework.core.model.IBasisTreeModel;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * BaseTreeModel
 *
 * Spring MVC 控制层向前端页面传递属性结构数据用封装类的基类
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-21 23:55:17
 * @see com.liaowei.framework.core.model.IBasisTreeModel<M>
 * @see com.liaowei.framework.model.BaseModel
 * @since jdk1.8
 */
@SuppressWarnings("serial")
@ToString(callSuper = true)
public abstract class BaseTreeModel<M> extends BaseModel implements IBasisTreeModel<M> {

    /**
     * 上级数据
     */
    @Getter
    @Setter
    private M parent;

    /**
     * 下级数据集合
     */
    @Getter
    @Setter
    private Set<M> children;

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

    public BaseTreeModel() {
        super();
    }

    public BaseTreeModel(String id, M parent, Set<M> children, Integer orderNum, Boolean valid, String creator, LocalDateTime createTime, String reviser, LocalDateTime modifyTime) {
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