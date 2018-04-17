/**
 * platform-service
 * SysRoles.java
 */
package com.liaowei.platform.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.liaowei.framework.entity.BaseEntity;
import com.liaowei.platform.enums.RoleTypeEnum;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * SysRoles
 *
 * 系统角色表实体类
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-17 03:17:43
 * @see com.liaowei.framework.entity.BaseEntity
 * @since jdk1.8
 */
@SuppressWarnings("serial")
@Getter
@Setter
@ToString(callSuper = true)
@Entity
@Table(name = "SYS_ROLES")
public class SysRole extends BaseEntity {

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色类型：管理员ADMINS、用户USERS
     */
    @Enumerated(value = EnumType.STRING)
    private RoleTypeEnum roleType;

    /**
     * 角色权限关系数据集合
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Column(name = "sysRoleId")
    private List<SysRoleAuth> sysRoleAuths;

    /**
     * 用户角色关系数据集合
     */
    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @Column(name = "sysRoleId")
    private List<SysUserRole> sysUserRoles;

    public SysRole() {
        super();
    }

    public SysRole(String id, String roleName, RoleTypeEnum roleType, Boolean valid, String creator, LocalDateTime createTime,
            String reviser, LocalDateTime modifyTime) {
        super(id, valid, creator, createTime, reviser, modifyTime);
        this.roleName = roleName;
        this.roleType = roleType;
    }

    public SysRole(String id, String roleName, RoleTypeEnum roleType, List<SysRoleAuth> sysRoleAuths, List<SysUserRole> sysUserRoles,
            Boolean valid, String creator, LocalDateTime createTime, String reviser, LocalDateTime modifyTime) {
        super(id, valid, creator, createTime, reviser, modifyTime);
        this.roleName = roleName;
        this.roleType = roleType;
        this.sysRoleAuths = sysRoleAuths;
        this.sysUserRoles = sysUserRoles;
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
        if (!(obj instanceof SysRole))
            return false;
        SysRole other = (SysRole) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}