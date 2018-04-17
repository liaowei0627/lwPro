/**
 * platform-service
 * SysMenu.java
 */
package com.liaowei.platform.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.liaowei.framework.entity.BaseTreeEntity;
import com.liaowei.platform.enums.MenuTypeEnum;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * SysMenu
 *
 * 系统菜单表实体类
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-16 11:50:56
 * @see com.liaowei.framework.entity.BaseEntity
 * @since jdk1.8
 */
@SuppressWarnings("serial")
@Getter
@Setter
@ToString(callSuper = true)
@Entity
@Table(name = "SYS_MENUS")
public class SysMenu extends BaseTreeEntity<SysMenu> {

    /**
     * 菜单文本
     */
    private String menuText;

    /**
     * 菜单地址
     */
    private String menuUrl;

    /**
     * 菜单类型：分系统SYSTEM、菜单MENU、链接LINK、按钮BUTTON
     */
    @Enumerated(value = EnumType.STRING)
    private MenuTypeEnum menuType;

    /**
     * 权限菜单关系中间表数据
     */
    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @Column(name = "sysMenuId")
    private List<SysAuthMenu> sysAuthMenus;

    public SysMenu() {
        super();
    }

    public SysMenu(String id, String menuText, String menuUrl, MenuTypeEnum menuType, Integer orderNum, SysMenu parent,
            Set<SysMenu> children, Boolean valid, String creator, LocalDateTime createTime, String reviser,
            LocalDateTime modifyTime) {
        super(id, parent, children, orderNum, valid, creator, createTime, reviser, modifyTime);
        this.menuText = menuText;
        this.menuUrl = menuUrl;
        this.menuType = menuType;
    }

    public SysMenu(String id, String menuText, String menuUrl, MenuTypeEnum menuType, Integer orderNum,
            List<SysAuthMenu> sysAuthMenus, SysMenu parent, Set<SysMenu> children, Boolean valid, String creator,
            LocalDateTime createTime, String reviser, LocalDateTime modifyTime) {
        super(id, parent, children, orderNum, valid, creator, createTime, reviser, modifyTime);
        this.menuText = menuText;
        this.menuUrl = menuUrl;
        this.menuType = menuType;
        this.sysAuthMenus = sysAuthMenus;
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
        if (!(obj instanceof SysMenu))
            return false;
        SysMenu other = (SysMenu) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}