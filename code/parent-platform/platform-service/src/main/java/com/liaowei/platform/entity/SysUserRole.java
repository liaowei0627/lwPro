/**
 * platform-service
 * SysUserRole.java
 */
package com.liaowei.platform.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.liaowei.framework.entity.BaseIdEntity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * SysUserRole
 *
 * 系统用户——角色关系表实体类
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-18 22:31:01
 * @see com.liaowei.framework.entity.BaseIdEntity
 * @since jdk1.8
 */
@SuppressWarnings("serial")
@Getter
@Setter
@ToString(callSuper = true)
@Entity
@Table(name = "SYS_USER_ROLES")
public class SysUserRole extends BaseIdEntity {

    /**
     * 用户ID
     */
    private String sysUserId;

    /**
     * 角色ID
     */
    private String sysRoleId;

    public SysUserRole() {
        super();
    }

    public SysUserRole(String id, String sysUserId, String sysRoleId) {
        super(id);
        this.sysUserId = sysUserId;
        this.sysRoleId = sysRoleId;
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
        if (!(obj instanceof SysUserRole))
            return false;
        SysUserRole other = (SysUserRole) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}