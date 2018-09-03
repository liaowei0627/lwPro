/**
 * platform-service
 * SysUser.java
 */
package com.liaowei.platform.entity;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.google.common.base.Strings;
import com.google.common.collect.Sets;
import com.liaowei.framework.entity.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * SysUser
 *
 * 系统用户表实体类
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-07 23:11:57
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
@Table(name = "SYS_USERS")
public class SysUser extends BaseEntity<SysUser> {

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 用户角色关系数据集合
     */
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "sysUserId")
    private Set<SysUserRole> sysUserRoles = Sets.<SysUserRole>newHashSet();

    /**
     * 用户权限关系数据集合
     */
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "sysUserId")
    private Set<SysUserAuth> sysUserAuths = Sets.<SysUserAuth>newHashSet();

    public SysUser(String id, String userName, String password, Boolean valid, String creator, LocalDateTime createTime,
            String reviser, LocalDateTime modifyTime) {
        super(id, valid, creator, createTime, reviser, modifyTime);
        this.userName = userName;
        this.password = password;
    }

    public SysUser(String id, Boolean valid, String creator, LocalDateTime createTime, String reviser, LocalDateTime modifyTime,
            String userName, String password, Set<SysUserRole> sysUserRoles, Set<SysUserAuth> sysUserAuths) {
        super(id, valid, creator, createTime, reviser, modifyTime);
        this.userName = userName;
        this.password = password;
        this.sysUserRoles = sysUserRoles;
        this.sysUserAuths = sysUserAuths;
    }

    @Override
    public void setEntity(SysUser e) {
        String id = e.getId();
        if (!Strings.isNullOrEmpty(id)) {
            this.id = id;
        }
        String userName = e.getUserName();
        if (!Strings.isNullOrEmpty(userName)) {
            this.userName = userName;
        }
        String password = e.getPassword();
        if (!Strings.isNullOrEmpty(password)) {
            this.password = password;
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
        if (!(obj instanceof SysUser))
            return false;
        SysUser other = (SysUser) obj;
        if (id == null) {
            return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}