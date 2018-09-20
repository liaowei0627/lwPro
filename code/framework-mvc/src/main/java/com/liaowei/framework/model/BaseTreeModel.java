/**
 * framework-mvc
 * BaseTreeModel.java
 */
package com.liaowei.framework.model;

import java.util.List;

import com.liaowei.framework.core.model.IBasisTreeModel;
import com.liaowei.framework.entity.BaseTreeEntity;
import com.liaowei.framework.vo.BaseTreeVo;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * BaseTreeModel
 *
 * Spring MVC 控制层向前端页面传递属性结构数据用封装类的基类
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-21 23:55:17
 * @see com.liaowei.framework.core.model.IBasisTreeModel<E, V, M>
 * @see com.liaowei.framework.model.BaseModel<E, V, M>
 * @since jdk1.8
 */
@SuppressWarnings("serial")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(callSuper = true)
public abstract class BaseTreeModel<E extends BaseTreeEntity<E>, V extends BaseTreeVo<E, V>, M extends BaseTreeModel<E, V, M>>
        extends BaseModel<E, V, M> implements IBasisTreeModel<E, V, M> {

    /**
     * 编号
     */
    @Getter
    @Setter
    protected String code;
    /**
     * 名称
     */
    @Getter
    @Setter
    protected String text;

    /**
     * 全路径编号<br>
     * 上级全路径编号-编号
     */
    @Getter
    @Setter
    protected String fullCode;

    /**
     * 全路径名称<br>
     * 上级全路径名称|名称
     */
    @Getter
    @Setter
    protected String fullText;

    /**
     * 上级数据
     */
    @Getter
    @Setter
    protected M parent;

    /**
     * 下级数据集合
     */
    @Getter
    @Setter
    protected List<M> children;

    /**
     * 顺序
     */
    @Getter
    @Setter
    protected Integer orderNum;

    /**
     * 是否有子节点
     */
    @Getter
    protected Boolean hasChild;
}