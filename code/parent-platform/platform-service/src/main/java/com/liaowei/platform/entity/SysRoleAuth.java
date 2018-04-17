/**
 * platform-service
 * SysRoleAuths.java
 */
package com.liaowei.platform.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.liaowei.framework.entity.BaseIdEntity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * SysRoleAuths
 *
 * 系统角色——权限关系表实体类
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-17 20:54:17
 * @see com.liaowei.framework.entity.BaseIdEntity
 * @since jdk1.8
 */
@SuppressWarnings("serial")
@Getter
@Setter
@ToString(callSuper = true)
@Entity
@Table(name = "SYS_ROLE_AUTHS")
public class SysRoleAuth extends BaseIdEntity {

    /**
     * 角色ID
     */
    private String sysRoleId;

    /**
     * 权限ID
     */
    private String sysAuthId;

    public SysRoleAuth() {
        super();
    }

    public SysRoleAuth(String id, String sysRoleId, String sysAuthId) {
        super(id);
        this.sysRoleId = sysRoleId;
        this.sysAuthId = sysAuthId;
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
        if (!(obj instanceof SysRoleAuth))
            return false;
        SysRoleAuth other = (SysRoleAuth) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}