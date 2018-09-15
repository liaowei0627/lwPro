/**
 * platform-service
 * DictionaryVo.java
 */
package com.liaowei.platform.vo;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.liaowei.framework.vo.BaseTreeVo;
import com.liaowei.platform.entity.SysDictionary;
import com.liaowei.platform.enums.DictTypeEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
public class DictionaryVo extends BaseTreeVo<SysDictionary, DictionaryVo> {

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
     * 
     * @see com.liaowei.platform.enums.DictTypeEnum
     */
    @Enumerated(value = EnumType.STRING)
    private DictTypeEnum dictType;

    public DictionaryVo(String id, String dictCode, String dictName,
            DictTypeEnum dictType, String code, String text, String fullCode, String fullText, DictionaryVo parent,
            List<DictionaryVo> children, Integer orderNum, Boolean hasChild, Boolean valid, String creator,
            LocalDateTime createTime, String reviser, LocalDateTime modifyTime) {
        super(id, code, text, fullCode, fullText, parent, children, orderNum, hasChild, valid, creator, createTime, reviser,
                modifyTime);
        this.dictCode = dictCode;
        this.dictName = dictName;
        this.dictType = dictType;
    }

    @Override
    public void copyForEntity(SysDictionary temp) {
        id = temp.getId();
        valid = temp.getValid();
        creator = temp.getCreator();
        createTime = temp.getCreateTime();
        reviser = temp.getReviser();
        modifyTime = temp.getModifyTime();
        code = temp.getCode();
        text = temp.getText();
        fullCode = temp.getFullCode();
        fullText = temp.getFullText();
        orderNum = temp.getOrderNum();
        hasChild = temp.getHasChild();

        SysDictionary pDic = temp.getParent();
        if (null != pDic) {
            parent = new DictionaryVo();
            parent.copyForEntity(pDic);
        }

        dictType = temp.getDictType();
    }

    @Override
    public SysDictionary copyToEntity() {
        SysDictionary parentEntity = null;
        if (null != parent) {
            parentEntity = copyToEntity(parent);
        }
        return new SysDictionary(id, dictType, code, text, fullCode, fullText, parentEntity, null, orderNum, valid, creator,
                createTime, reviser, modifyTime);
    }

    @Override
    public SysDictionary copyToEntity(DictionaryVo v) {
        SysDictionary parentEntity = null;
        DictionaryVo parent = v.getParent();
        if (null != parent) {
            parentEntity = copyToEntity(parent);
        }
        return new SysDictionary(v.getId(), v.getDictType(), v.getCode(), v.getText(), v.getFullCode(), v.getFullText(),
                parentEntity, null, v.getOrderNum(), v.getValid(), v.getCreator(), v.getCreateTime(), v.getReviser(),
                v.getModifyTime());
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
            return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}