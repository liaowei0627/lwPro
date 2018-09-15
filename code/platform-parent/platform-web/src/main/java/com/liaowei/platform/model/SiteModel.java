/**
 * platform-web
 * SiteModel.java
 */
package com.liaowei.platform.model;

import com.liaowei.framework.model.BaseModel;
import com.liaowei.platform.entity.SysSite;
import com.liaowei.platform.vo.SiteVo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * SiteModel
 *
 * 站点信息
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-09-14 23:29:33
 * @see com.liaowei.framework.model.BaseModel<SysSite, SiteVo, SiteModel>
 * @since jdk1.8
 */
@SuppressWarnings("serial")
@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
public class SiteModel extends BaseModel<SysSite, SiteVo, SiteModel> {

    /**
     * 站点编号
     */
    private String siteCode;

    /**
     * 站点名称
     */
    private String siteName;

    @Override
    public void copyForVo(SiteVo temp) {
        id = temp.getId();
        valid = temp.getValid();
        creator = temp.getCreator();
        createTime = temp.getCreateTime();
        reviser = temp.getReviser();
        modifyTime = temp.getModifyTime();

        siteCode = temp.getSiteCode();
        siteName = temp.getSiteName();
    }

    @Override
    public SiteVo copyToVo() {
        return new SiteVo(id, siteCode, siteName, valid, creator, createTime, reviser, modifyTime);
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
        if (!(obj instanceof SiteModel))
            return false;
        SiteModel other = (SiteModel) obj;
        if (id == null) {
            return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}