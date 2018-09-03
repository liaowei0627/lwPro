/**
 * platform-service
 * SysMenu.java
 */
package com.liaowei.platform.entity;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.google.common.base.Strings;
import com.google.common.collect.Sets;
import com.liaowei.framework.entity.BaseTreeEntity;
import com.liaowei.platform.enums.MenuTypeEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
@Entity
@Table(name = "SYS_MENUS")
public class SysMenu extends BaseTreeEntity<SysMenu> {

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
    @JoinColumn(name = "sysMenuId")
    private Set<SysAuthMenu> sysAuthMenus = Sets.<SysAuthMenu>newHashSet();

    public SysMenu(String id, String menuUrl, MenuTypeEnum menuType, String code, String text, String fullCode, String fullText,
            SysMenu parent, Set<SysMenu> children, Integer orderNum, Boolean valid, String creator, LocalDateTime createTime,
            String reviser, LocalDateTime modifyTime) {
        super(id, code, text, fullCode, fullText, parent, children, orderNum, valid, creator, createTime, reviser, modifyTime);
        this.menuUrl = menuUrl;
        this.menuType = menuType;
    }

    public SysMenu(String id, String menuUrl, MenuTypeEnum menuType, Set<SysAuthMenu> sysAuthMenus, String code, String text,
            String fullCode, String fullText, SysMenu parent, Set<SysMenu> children, Integer orderNum, Boolean valid,
            String creator, LocalDateTime createTime, String reviser, LocalDateTime modifyTime) {
        super(id, code, text, fullCode, fullText, parent, children, orderNum, valid, creator, createTime, reviser, modifyTime);
        this.menuUrl = menuUrl;
        this.menuType = menuType;
        this.sysAuthMenus = sysAuthMenus;
    }

    @Override
    public void setEntity(SysMenu e) {
        String id = e.getId();
        if (!Strings.isNullOrEmpty(id)) {
            this.id = id;
        }
        String code = e.getCode();
        if (!Strings.isNullOrEmpty(code)) {
            this.code = code;
        }
        String text = e.getText();
        if (!Strings.isNullOrEmpty(text)) {
            this.text = text;
        }
        String fullCode = e.getFullCode();
        if (!Strings.isNullOrEmpty(fullCode)) {
            this.fullCode = fullCode;
        }
        String fullText = e.getFullText();
        if (!Strings.isNullOrEmpty(fullText)) {
            this.fullText = fullText;
        }
        Integer orderNum = e.getOrderNum();
        if (null != orderNum) {
            this.orderNum = orderNum;
        }
        String menuUrl = e.getMenuUrl();
        if (!Strings.isNullOrEmpty(menuUrl)) {
            this.menuUrl = menuUrl;
        }
        MenuTypeEnum menuType = e.getMenuType();
        if (null != menuType) {
            this.menuType = menuType;
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
        if (!(obj instanceof SysMenu))
            return false;
        SysMenu other = (SysMenu) obj;
        if (id == null) {
            return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}