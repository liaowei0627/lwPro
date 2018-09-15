/**
 * platform-service
 * SiteVo.java
 */
package com.liaowei.platform.vo;

import java.time.LocalDateTime;

import com.liaowei.framework.vo.BaseVo;
import com.liaowei.platform.entity.SysSite;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * SiteVo
 *
 * 站点信息
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-19 22:01:35
 * @see com.liaowei.framework.service.vo.BaseVo
 * @since jdk1.8
 */
@SuppressWarnings("serial")
@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
public class SiteVo extends BaseVo<SysSite, SiteVo> {

    /**
     * 站点编号
     */
    private String siteCode;

    /**
     * 站点名称
     */
    private String siteName;

    public SiteVo(String id, String siteCode, String siteName, Boolean valid, String creator, LocalDateTime createTime,
            String reviser, LocalDateTime modifyTime) {
        super(id, valid, creator, createTime, reviser, modifyTime);
        this.siteCode = siteCode;
        this.siteName = siteName;
    }

    @Override
    public void copyForEntity(SysSite temp) {
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
    public SysSite copyToEntity() {
        return new SysSite(id, siteCode, siteName, valid, creator, createTime, reviser, modifyTime);
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
        if (!(obj instanceof SiteVo))
            return false;
        SiteVo other = (SiteVo) obj;
        if (id == null) {
            return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}