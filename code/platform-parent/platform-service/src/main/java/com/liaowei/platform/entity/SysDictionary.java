/**
 * platform-service
 * SysDictionary.java
 */
package com.liaowei.platform.entity;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.google.common.base.Strings;
import com.liaowei.framework.entity.BaseTreeEntity;
import com.liaowei.platform.enums.DictTypeEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * SysDictionary
 *
 * 系统数据字典表实体类
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-17 04:04:38
 * @see com.liaowei.framework.entity.BaseTreeEntity<SysDictionary>
 * @since jdk1.8
 */
@SuppressWarnings("serial")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
@Entity
@Table(name = "SYS_DICTIONARIES")
public class SysDictionary extends BaseTreeEntity<SysDictionary> {

    /**
     * 字典类型
     * 
     * @see com.liaowei.platform.enums.DictTypeEnum
     */
    @Enumerated(value = EnumType.STRING)
    private DictTypeEnum dictType;

    public SysDictionary(String id, DictTypeEnum dictType, String code, String text, String fullCode, String fullText,
            SysDictionary parent, Set<SysDictionary> children, Integer orderNum, Boolean valid, String creator,
            LocalDateTime createTime, String reviser, LocalDateTime modifyTime) {
        super(id, code, text, fullCode, fullText, parent, children, orderNum, valid, creator, createTime, reviser, modifyTime);
        this.dictType = dictType;
    }

    @Override
    public void setEntity(SysDictionary e) {
        String id = e.getId();
        if (!Strings.isNullOrEmpty(id)) {
            this.id = id;
        }
        String code = e.getCode();
        if (!Strings.isNullOrEmpty(code)) {
            this.code = code;
        }
        String text = e.getText();
        if (!Strings.isNullOrEmpty(text)) {
            this.text = text;
        }
        DictTypeEnum dictType = e.getDictType();
        if (null != dictType) {
            this.dictType = dictType;
        }
        Boolean valid = e.getValid();
        if (null != valid) {
            this.valid = valid;
        }
        String creator = e.getCreator();
        if (!Strings.isNullOrEmpty(creator)) {
            this.creator = creator;
        }
        LocalDateTime createTime = e.getCreateTime();
        if (null != createTime) {
            this.createTime = createTime;
        }
        String reviser = e.getReviser();
        if (!Strings.isNullOrEmpty(reviser)) {
            this.reviser = reviser;
        }
        LocalDateTime modifyTime = e.getModifyTime();
        if (null != modifyTime) {
            this.modifyTime = modifyTime;
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof SysDictionary)) {
            return false;
        }
        SysDictionary other = (SysDictionary) obj;
        if (id == null) {
            return false;
        } else if (!id.equals(other.id)) {
            return false;
        }
        return true;
    }
}