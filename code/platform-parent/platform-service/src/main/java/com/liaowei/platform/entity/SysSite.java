/**
 * platform-service
 * SysSite.java
 */
package com.liaowei.platform.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.google.common.base.Strings;
import com.liaowei.framework.entity.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * SysSite
 *
 * 系统站点表实体类
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-17 03:33:15
 * @see com.liaowei.framework.entity.BaseEntity
 * @since jdk1.8
 */
@SuppressWarnings("serial")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
@Entity
@Table(name = "SYS_SITES")
public class SysSite extends BaseEntity<SysSite> {

    /**
     * 站点编号
     */
    private String siteCode;

    /**
     * 站点名称
     */
    private String siteName;

    public SysSite(String id, String siteCode, String siteName, Boolean valid, String creator, LocalDateTime createTime, String reviser,
            LocalDateTime modifyTime) {
        super(id, valid, creator, createTime, reviser, modifyTime);
        this.siteCode = siteCode;
        this.siteName = siteName;
    }

    @Override
    public void setEntity(SysSite e) {
        String id = e.getId();
        if (!Strings.isNullOrEmpty(id)) {
            this.id = id;
        }
        String siteCode = e.getSiteCode();
        if (!Strings.isNullOrEmpty(siteCode)) {
            this.siteCode = siteCode;
        }
        String siteName = e.getSiteName();
        if (!Strings.isNullOrEmpty(siteName)) {
            this.siteName = siteName;
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
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof SysSite))
            return false;
        SysSite other = (SysSite) obj;
        if (id == null) {
            return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}