/**
 * platform-service
 * SysAuthority.java
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
import com.liaowei.platform.enums.AuthTypeEnum;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * SysAuthority
 *
 * 系统权限表实体类
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-17 03:11:37
 * @see com.liaowei.framework.entity.BaseEntity
 * @since jdk1.8
 */
@SuppressWarnings("serial")
@Getter
@Setter
@ToString(callSuper = true)
@Entity
@Table(name = "SYS_AUTHORITIES")
public class SysAuthority extends BaseEntity {

    /**
     * 权限名称
     */
    private String authName;

    /**
     * 权限类型：访问权限ACCESS、数据权限DATA
     */
    @Enumerated(value = EnumType.STRING)
    private AuthTypeEnum authType;

    /**
     * 访问权限集合（菜单）
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Column(name = "sysAuthId")
    private List<SysAuthMenu> sysAuthMenus;

    /**
     * 数据权限集合
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Column(name = "sysAuthId")
    private List<SysAuthDataType> sysAuthDataTypes;

    /**
     * 角色权限关系数据集合
     */
    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @Column(name = "sysAuthId")
    private List<SysRoleAuth> sysRoleAuths;

    /**
     * 用户权限关系数据集合
     */
    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @Column(name = "sysAuthId")
    private List<SysUserAuth> sysUserAuths;

    public SysAuthority() {
        super();
    }

    public SysAuthority(String id, String authName, AuthTypeEnum authType, Boolean valid, String creator,
            LocalDateTime createTime, String reviser, LocalDateTime modifyTime) {
        super(id, valid, creator, createTime, reviser, modifyTime);
        this.authName = authName;
        this.authType = authType;
    }

    public SysAuthority(String id, String authName, AuthTypeEnum authType, List<SysAuthMenu> sysAuthMenus,
            List<SysAuthDataType> sysAuthDataTypes, List<SysRoleAuth> sysRoleAuths, List<SysUserAuth> sysUserAuths, Boolean valid,
            String creator, LocalDateTime createTime, String reviser, LocalDateTime modifyTime) {
        super(id, valid, creator, createTime, reviser, modifyTime);
        this.authName = authName;
        this.authType = authType;
        this.sysAuthMenus = sysAuthMenus;
        this.sysAuthDataTypes = sysAuthDataTypes;
        this.sysRoleAuths = sysRoleAuths;
        this.sysUserAuths = sysUserAuths;
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
        if (!(obj instanceof SysAuthority))
            return false;
        SysAuthority other = (SysAuthority) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}