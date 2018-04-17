/**
 * platform-service
 * SysAuthDataType.java
 */
package com.liaowei.platform.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.liaowei.framework.entity.BaseIdEntity;
import com.liaowei.platform.enums.DataAuthTypeEnum;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * SysAuthDataType
 *
 * 系统权限——数据权限类型关系表实体类
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-17 06:03:22
 * @see com.liaowei.framework.entity.BaseIdEntity
 * @since jdk1.8
 */
@SuppressWarnings("serial")
@Getter
@Setter
@ToString(callSuper = true)
@Entity
@Table(name = "SYS_AUTH_DATA_TYPES")
public class SysAuthDataType extends BaseIdEntity {

    /**
     * 系统权限ID
     */
    private String sysAuthId;

    /**
     * 站点ID，多个用“,”分隔
     */
    private String sites;

    /**
     * 数据权限类型：
     *    全部ALL、
     *    属于我的FOR_MY（creator为当前用户）、
     *    发送给我的TO_MY（submitTo为当前用户）、
     *    属于我角色的FOR_MYROLE（creator为拥有当前用户拥有的角色之一的用户）、
     *    发送给我角色的TO_MYROLE（submitTo为拥有当前用户拥有的角色之一的用户）
     */
    @Enumerated(value = EnumType.STRING)
    private DataAuthTypeEnum dataAuthType;

    public SysAuthDataType() {
        super();
    }

    public SysAuthDataType(String id, String sysAuthId, String sites, DataAuthTypeEnum dataAuthType) {
        super(id);
        this.sysAuthId = sysAuthId;
        this.sites = sites;
        this.dataAuthType = dataAuthType;
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
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}