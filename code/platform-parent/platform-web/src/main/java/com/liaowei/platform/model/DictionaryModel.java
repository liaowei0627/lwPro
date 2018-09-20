/**
 * platform-web
 * DictionaryModel.java
 */
package com.liaowei.platform.model;

import com.liaowei.framework.model.BaseTreeModel;
import com.liaowei.platform.entity.SysDictionary;
import com.liaowei.platform.vo.DictionaryVo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * DictionaryModel
 *
 * 数据字典信息
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-09-16 19:51:36
 * @see com.liaowei.framework.model.BaseTreeModel<SysDictionary, DictionaryVo, DictionaryModel>
 * @since jdk1.8
 */
@SuppressWarnings("serial")
@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
public class DictionaryModel extends BaseTreeModel<SysDictionary, DictionaryVo, DictionaryModel> {

    @Override
    public void copyForVo(DictionaryVo temp) {
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

        DictionaryVo pDict = temp.getParent();
        if (null != pDict) {
            parent = new DictionaryModel();
            parent.copyForVo(pDict);
        }
    }

    @Override
    public DictionaryVo copyToVo() {
        DictionaryVo parentVo = null;
        if (null != parent) {
            parentVo = copyToVo(parent);
        }
        return new DictionaryVo(id, code, text, fullCode, fullText, parentVo, null, orderNum, null, valid, creator,
                createTime, reviser, modifyTime);
    }

    @Override
    public DictionaryVo copyToVo(DictionaryModel model) {
        DictionaryVo parentVo = null;
        DictionaryModel parent = model.getParent();
        if (null != parent) {
            parentVo = copyToVo(parent);
        }
        return new DictionaryVo(model.getId(), model.getCode(), model.getText(), model.getFullCode(),
                model.getFullText(), parentVo, null, model.getOrderNum(), null, model.getValid(), model.getCreator(),
                model.getCreateTime(), model.getReviser(), model.getModifyTime());
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
        if (!(obj instanceof DictionaryModel))
            return false;
        DictionaryModel other = (DictionaryModel) obj;
        if (id == null) {
            return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}