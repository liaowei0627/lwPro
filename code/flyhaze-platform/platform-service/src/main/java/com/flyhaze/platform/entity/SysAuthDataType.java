/**
 * platform-service
 * SysAuthDataType.java
 */
package com.flyhaze.platform.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.flyhaze.framework.entity.BaseIdEntity;
import com.flyhaze.platform.enums.DataAuthTypeEnum;
import com.google.common.base.Strings;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * SysAuthDataType
 *
 * 系统权限——数据权限类型关系表实体类
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-17 06:03:22
 * @see com.flyhaze.framework.entity.BaseIdEntity<SysAuthDataType>
 * @since jdk1.8
 */
@SuppressWarnings("serial")
@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
@Entity
@Table(name = "SYS_AUTH_DATA_TYPES")
public class SysAuthDataType extends BaseIdEntity<SysAuthDataType> {

    /**
     * 系统权限ID
     */
    private String sysAuthId;

    /**
     * 站点ID，多个用“,”分隔
     */
    private String sites;

    /**
     * 数据权限类型
     * 
     * @see com.flyhaze.platform.enums.DataAuthTypeEnum
     */
    @Enumerated(value = EnumType.STRING)
    private DataAuthTypeEnum dataAuthType;

    public SysAuthDataType(String id, String sysAuthId, String sites, DataAuthTypeEnum dataAuthType) {
        super(id);
        this.sysAuthId = sysAuthId;
        this.sites = sites;
        this.dataAuthType = dataAuthType;
    }

    @Override
    public void setEntity(SysAuthDataType e) {
        String id = e.getId();
        if (!Strings.isNullOrEmpty(id)) {
            this.id = id;
        }
        String sysAuthId = e.getSysAuthId();
        if (!Strings.isNullOrEmpty(sysAuthId)) {
            this.sysAuthId = sysAuthId;
        }
        String sites = e.getSites();
        if (!Strings.isNullOrEmpty(sites)) {
            this.sites = sites;
        }
        DataAuthTypeEnum dataAuthType = e.getDataAuthType();
        if (null != dataAuthType) {
            this.dataAuthType = dataAuthType;
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
        if (!(obj instanceof SysAuthDataType))
            return false;
        SysAuthDataType other = (SysAuthDataType) obj;
        if (id == null) {
            return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}