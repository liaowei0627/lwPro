/**
 * flyhaze-framework
 * BaseTreeVo.java
 */
package com.flyhaze.framework.vo;

import java.time.LocalDateTime;
import java.util.List;

import com.flyhaze.framework.core.vo.IBasisTreeVo;
import com.flyhaze.framework.hibernate.entity.BaseTreeEntity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * BaseTreeVo
 *
 * Spring服务层向控制层传递树形数据的封装类基类
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-19 22:51:07
 * @see com.flyhaze.framework.core.vo.IBasisTreeVo<E, V>
 * @see com.flyhaze.framework.vo.BaseVo<E, V>
 * @since jdk1.8
 */
@SuppressWarnings("serial")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(callSuper = true)
public abstract class BaseTreeVo<E extends BaseTreeEntity<E>, V extends BaseTreeVo<E, V>> extends BaseVo<E, V>
        implements IBasisTreeVo<E, V> {

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
    protected V parent;

    /**
     * 下级数据集合
     */
    @Getter
    @Setter
    protected List<V> children;

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

    protected BaseTreeVo(String id, String code, String text, String fullCode, String fullText, V parent, List<V> children,
            Integer orderNum, Boolean hasChild, Boolean valid, String creator, LocalDateTime createTime, String reviser,
            LocalDateTime modifyTime) {
        super(id, valid, creator, createTime, reviser, modifyTime);
        this.code = code;
        this.text = text;
        this.fullCode = fullCode;
        this.fullText = fullText;
        this.parent = parent;
        this.children = children;
        this.orderNum = orderNum;
        this.hasChild = hasChild;
    }
}