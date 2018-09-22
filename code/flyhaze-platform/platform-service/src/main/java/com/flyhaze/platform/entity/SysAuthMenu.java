/**
 * platform-service
 * SysAuthMenu.java
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
 * SysAuthMenu
 *
 * 系统权限——菜单关系表实体类
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-17 20:41:10
 * @see com.flyhaze.framework.hibernate.entity.BaseIdEntity<SysAuthMenu>
 * @since jdk1.8
 */
@SuppressWarnings("serial")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
@Entity
@Table(name = "SYS_AUTH_MENUS")
public class SysAuthMenu extends BaseIdEntity<SysAuthMenu> {

    /**
     * 权限ID
     */
    private String sysAuthId;

    /**
     * 菜单ID
     */
    private String sysMenuId;

    public SysAuthMenu(String id, String sysAuthId, String sysMenuId) {
        super(id);
        this.sysAuthId = sysAuthId;
        this.sysMenuId = sysMenuId;
    }

    @Override
    public void setEntity(SysAuthMenu e) {
        String id = e.getId();
        if (!Strings.isNullOrEmpty(id)) {
            this.id = id;
        }
        String sysAuthId = e.getSysAuthId();
        if (!Strings.isNullOrEmpty(sysAuthId)) {
            this.sysAuthId = sysAuthId;
        }
        String sysMenuId = e.getSysMenuId();
        if (!Strings.isNullOrEmpty(sysMenuId)) {
            this.sysMenuId = sysMenuId;
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
        if (!(obj instanceof SysAuthMenu))
            return false;
        SysAuthMenu other = (SysAuthMenu) obj;
        if (id == null) {
            return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}