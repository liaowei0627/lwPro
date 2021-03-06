/**
 * platform-service
 * SysAuthority.java
 */
package com.flyhaze.platform.entity;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.flyhaze.framework.hibernate.entity.BaseEntity;
import com.flyhaze.platform.enums.AuthTypeEnum;
import com.google.common.base.Strings;
import com.google.common.collect.Sets;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * SysAuthority
 *
 * 系统权限表实体类
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-17 03:11:37
 * @see com.flyhaze.framework.hibernate.entity.BaseEntity<SysAuthority>
 * @since jdk1.8
 */
@SuppressWarnings("serial")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
@Entity
@Table(name = "SYS_AUTHORITIES")
public class SysAuthority extends BaseEntity<SysAuthority> {

    /**
     * 权限编号
     */
    private String authCode;

    /**
     * 权限名称
     */
    private String authName;

    /**
     * 备注
     */
    private String remark;

    /**
     * 权限类型
     * 
     * @see com.flyhaze.platform.enums.AuthTypeEnum
     */
    @Enumerated(value = EnumType.STRING)
    private AuthTypeEnum authType;

    /**
     * 是否内置
     */
    @Column(insertable = false, updatable = false)
    protected Boolean builtIn;

    /**
     * 访问权限集合（菜单）
     * 
     * @see com.flyhaze.platform.entity.SysAuthMenu
     */
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "sysAuthId")
    private Set<SysAuthMenu> sysAuthMenus = Sets.<SysAuthMenu>newHashSet();

    /**
     * 数据权限集合
     * 
     * @see com.flyhaze.platform.entity.SysAuthDataType
     */
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "sysAuthId")
    private Set<SysAuthDataType> sysAuthDataTypes = Sets.<SysAuthDataType>newHashSet();

    /**
     * 角色权限关系数据集合
     * 
     * @see com.flyhaze.platform.entity.SysRoleAuth
     */
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "sysAuthId")
    private Set<SysRoleAuth> sysRoleAuths = Sets.<SysRoleAuth>newHashSet();

    /**
     * 用户权限关系数据集合
     * 
     * @see com.flyhaze.platform.entity.SysUserAuth
     */
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "sysAuthId")
    private Set<SysUserAuth> sysUserAuths = Sets.<SysUserAuth>newHashSet();

    public SysAuthority(String id, String authCode, String authName, String remark, AuthTypeEnum authType, Boolean builtIn, Boolean valid, String creator,
            LocalDateTime createTime, String reviser, LocalDateTime modifyTime) {
        super(id, valid, creator, createTime, reviser, modifyTime);
        this.authCode = authCode;
        this.authName = authName;
        this.remark = remark;
        this.authType = authType;
        this.builtIn = builtIn;
    }

    public SysAuthority(String id, String authCode, String authName, String remark, AuthTypeEnum authType, Boolean builtIn, Set<SysAuthMenu> sysAuthMenus,
            Set<SysAuthDataType> sysAuthDataTypes, Set<SysRoleAuth> sysRoleAuths, Set<SysUserAuth> sysUserAuths, Boolean valid,
            String creator, LocalDateTime createTime, String reviser, LocalDateTime modifyTime) {
        super(id, valid, creator, createTime, reviser, modifyTime);
        this.authCode = authCode;
        this.authName = authName;
        this.remark = remark;
        this.authType = authType;
        this.builtIn = builtIn;
        this.sysAuthMenus = sysAuthMenus;
        this.sysAuthDataTypes = sysAuthDataTypes;
        this.sysRoleAuths = sysRoleAuths;
        this.sysUserAuths = sysUserAuths;
    }

    @Override
    public void setEntity(SysAuthority e) {
        String id = e.getId();
        if (!Strings.isNullOrEmpty(id)) {
            this.id = id;
        }
        String authCode = e.getAuthCode();
        if (!Strings.isNullOrEmpty(authCode)) {
            this.authCode = authCode;
        }
        String authName = e.getAuthName();
        if (!Strings.isNullOrEmpty(authName)) {
            this.authName = authName;
        }
        String remark = e.getRemark();
        if (!Strings.isNullOrEmpty(remark)) {
            this.remark = remark;
        }
        AuthTypeEnum authType = e.getAuthType();
        if (null != authType) {
            this.authType = authType;
        }
        Boolean builtIn = e.getBuiltIn();
        if (null != builtIn) {
            this.builtIn = builtIn;
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
        if (!(obj instanceof SysAuthority))
            return false;
        SysAuthority other = (SysAuthority) obj;
        if (id == null) {
            return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}