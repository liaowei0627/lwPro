/**
 * platform-service
 * DictionaryVo.java
 */
package com.liaowei.platform.vo;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.liaowei.framework.vo.BaseTreeVo;
import com.liaowei.platform.enums.DictTypeEnum;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * DictionaryVo
 *
 * 字典信息
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-19 22:48:56
 * @see com.liaowei.framework.vo.BaseTreeVo<DictionaryVo>
 * @since jdk1.8
 */
@SuppressWarnings("serial")
@Getter
@Setter
@ToString(callSuper = true)
public class DictionaryVo extends BaseTreeVo<DictionaryVo> {

    /**
     * 字典编号
     */
    private String dictCode;

    /**
     * 字典名称
     */
    private String dictName;

    /**
     * 字典类型
     */
    @Enumerated(value = EnumType.STRING)
    private DictTypeEnum dictType;

    public DictionaryVo() {
        super();
    }

    public DictionaryVo(String id, DictTypeEnum dictType, String code, String text, String fullCode, String fullText,
            DictionaryVo parent, Set<DictionaryVo> children, Integer orderNum, Boolean valid, String creator,
            LocalDateTime createTime, String reviser, LocalDateTime modifyTime) {
        super(id, code, text, fullCode, fullText, parent, children, orderNum, valid, creator, createTime, reviser, modifyTime);
        this.dictType = dictType;
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
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof DictionaryVo))
            return false;
        DictionaryVo other = (DictionaryVo) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}