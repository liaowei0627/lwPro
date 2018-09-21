/**
 * platform-service
 * SysUserAuth.java
 */
package com.flyhaze.platform.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.flyhaze.framework.hibernate.entity.BaseIdEntity;
import com.google.common.base.Strings;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * SysUserAuth
 *
 * 系统用户——权限关系表实体类
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-18 22:46:02
 * @see com.flyhaze.framework.hibernate.entity.BaseIdEntity<SysUserAuth>
 * @since jdk1.8
 */
@SuppressWarnings("serial")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
@Entity
@Table(name = "SYS_USER_AUTHS")
public class SysUserAuth extends BaseIdEntity<SysUserAuth> {

    /**
     * 用户ID
     */
    private String sysUserId;

    /**
     * 角色ID
     */
    private String sysAuthId;

    public SysUserAuth(String id, String sysUserId, String sysAuthId) {
        super(id);
        this.sysUserId = sysUserId;
        this.sysAuthId = sysAuthId;
    }

    @Override
    public void setEntity(SysUserAuth e) {
        String id = e.getId();
        if (!Strings.isNullOrEmpty(id)) {
            this.id = id;
        }
        String sysUserId = e.getSysUserId();
        if (!Strings.isNullOrEmpty(sysUserId)) {
            this.sysUserId = sysUserId;
        }
        String sysAuthId = e.getSysAuthId();
        if (!Strings.isNullOrEmpty(sysAuthId)) {
            this.sysAuthId = sysAuthId;
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
        if (!(obj instanceof SysUserAuth))
            return false;
        SysUserAuth other = (SysUserAuth) obj;
        if (id == null) {
            return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}