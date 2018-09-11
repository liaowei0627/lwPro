/**
 * platform-service
 * SysRoles.java
 */
package com.liaowei.platform.entity;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.google.common.base.Strings;
import com.google.common.collect.Sets;
import com.liaowei.framework.entity.BaseEntity;
import com.liaowei.platform.enums.PowerTypeEnum;
import com.liaowei.platform.enums.RoleTypeEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
@Entity
@Table(name = "SYS_ROLES")
public class SysRole extends BaseEntity<SysRole> {

    /**
     * 角色编号
     */
    private String roleCode;

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
     * 安全类型：管理员ADMIN、用户USER
     */
    @Enumerated(value = EnumType.STRING)
    private PowerTypeEnum powerType;

    /**
     * 站点编号
     */
    private String siteCode;

    /**
     * 角色权限关系数据集合
     */
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "sysRoleId")
    private Set<SysRoleAuth> sysRoleAuths = Sets.<SysRoleAuth>newHashSet();

    /**
     * 用户角色关系数据集合
     */
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "sysRoleId")
    private Set<SysUserRole> sysUserRoles = Sets.<SysUserRole>newHashSet();

    public SysRole(String id, String roleCode, String roleName, RoleTypeEnum roleType, PowerTypeEnum powerType, String siteCode,
            Boolean valid, String creator, LocalDateTime createTime, String reviser, LocalDateTime modifyTime) {
        super(id, valid, creator, createTime, reviser, modifyTime);
        this.roleCode = roleCode;
        this.roleName = roleName;
        this.roleType = roleType;
        this.powerType = powerType;
        this.siteCode = siteCode;
    }

    public SysRole(String id, String roleCode, String roleName, RoleTypeEnum roleType, PowerTypeEnum powerType, String siteCode,
            Boolean valid, String creator, LocalDateTime createTime, String reviser, LocalDateTime modifyTime,
            Set<SysRoleAuth> sysRoleAuths, Set<SysUserRole> sysUserRoles) {
        super(id, valid, creator, createTime, reviser, modifyTime);
        this.roleCode = roleCode;
        this.roleName = roleName;
        this.roleType = roleType;
        this.powerType = powerType;
        this.siteCode = siteCode;
        this.sysRoleAuths = sysRoleAuths;
        this.sysUserRoles = sysUserRoles;
    }

    @Override
    public void setEntity(SysRole e) {
        String id = e.getId();
        if (!Strings.isNullOrEmpty(id)) {
            this.id = id;
        }
        String roleCode = e.getRoleCode();
        if (!Strings.isNullOrEmpty(roleCode)) {
            this.roleCode = roleCode;
        }
        String roleName = e.getRoleName();
        if (!Strings.isNullOrEmpty(roleName)) {
            this.roleName = roleName;
        }
        RoleTypeEnum roleType = e.getRoleType();
        if (null != roleType) {
            this.roleType = roleType;
        }
        PowerTypeEnum powerType = e.getPowerType();
        if (null != powerType) {
            this.powerType = powerType;
        }
        String siteCode = e.getSiteCode();
        if (!Strings.isNullOrEmpty(siteCode)) {
            this.siteCode = siteCode;
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
        if (!(obj instanceof SysRole))
            return false;
        SysRole other = (SysRole) obj;
        if (id == null) {
            return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}