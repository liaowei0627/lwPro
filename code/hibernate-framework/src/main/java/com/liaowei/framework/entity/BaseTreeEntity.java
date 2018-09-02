/**
 * hibernate-framework
 * BaseTreeEntity.java
 */
package com.liaowei.framework.entity;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Transient;

import com.google.common.collect.Sets;
import com.liaowei.framework.core.entity.IBasisTreeEntity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * BaseTreeEntity
 *
 * hibernate实体类的树形结构超类
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-17 03:44:16
 * @see com.liaowei.framework.core.entity.IBasisTreeEntity<E>
 * @see com.liaowei.framework.entity.BaseEntity
 * @since jdk1.8
 */
@SuppressWarnings("serial")
@ToString(callSuper = true)
@MappedSuperclass
public class BaseTreeEntity<E> extends BaseEntity implements IBasisTreeEntity<E> {

    /**
     * 编号
     */
    @Getter
    @Setter
    private String code;
    /**
     * 名称
     */
    @Getter
    @Setter
    private String text;

    /**
     * 全路径编号：上级全路径编号-编号
     */
    @Getter
    @Setter
    private String fullCode;

    /**
     * 全路径名称：上级全路径名称|名称
     */
    @Getter
    @Setter
    private String fullText;

    /**
     * 上级数据
     */
    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PARENT_ID")
    private E parent;

    /**
     * 下级数据集合
     */
    @Getter
    @Setter
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy="parent")
    @Column(name = "parent")
    @OrderBy("orderNum asc")
    private Set<E> children = Sets.<E>newHashSet();

    /**
     * 顺序
     */
    @Getter
    @Setter
    private Integer orderNum;

    /**
     * 是否有子节点
     */
    @Transient
    private Boolean hasChild;

    public BaseTreeEntity() {
        super();
    }

    public BaseTreeEntity(String id, String code, String text, String fullCode, String fullText, E parent, Set<E> children, Integer orderNum, Boolean valid, String creator, LocalDateTime createTime, String reviser, LocalDateTime modifyTime) {
        super(id, valid, creator, createTime, reviser, modifyTime);
        this.code = code;
        this.text = text;
        this.fullCode = fullCode;
        this.fullText = fullText;
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