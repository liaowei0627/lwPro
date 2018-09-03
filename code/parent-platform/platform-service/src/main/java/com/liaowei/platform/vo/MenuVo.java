/**
 * platform-service
 * MenuVo.java
 */
package com.liaowei.platform.vo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.google.common.collect.Sets;
import com.liaowei.framework.vo.BaseTreeVo;
import com.liaowei.platform.entity.SysMenu;
import com.liaowei.platform.enums.MenuTypeEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * MenuVo
 *
 * 菜单信息
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-19 21:58:31
 * @see com.liaowei.framework.service.vo.BaseVo
 * @since jdk1.8
 */
@SuppressWarnings("serial")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
public class MenuVo extends BaseTreeVo<SysMenu, MenuVo> {

    /**
     * 菜单文本
     */
    // private String text;

    /**
     * 菜单地址
     */
    private String menuUrl;

    /**
     * 菜单类型：分系统SYSTEM、菜单MENU、链接LINK、按钮BUTTON
     */
    @Enumerated(value = EnumType.STRING)
    private MenuTypeEnum menuType;

    public MenuVo(String id, String menuUrl, MenuTypeEnum menuType, String code, String text, String fullCode, String fullText,
            MenuVo parent, List<MenuVo> children, Integer orderNum, Boolean valid, String creator, LocalDateTime createTime,
            String reviser, LocalDateTime modifyTime) {
        super(id, code, text, fullCode, fullText, parent, children, orderNum, valid, creator, createTime, reviser, modifyTime);
        this.menuUrl = menuUrl;
        this.menuType = menuType;
    }

    @Override
    public void copyForEntity(SysMenu temp) {
        id = temp.getId();
        valid = temp.getValid();
        creator = temp.getCreator();
        createTime = temp.getCreateTime();
        reviser = temp.getReviser();
        modifyTime = temp.getModifyTime();
        code = temp.getCode();
        text = temp.getText();
        fullCode = temp.getFullCode();
        fullText = temp.getFullText();
        orderNum = temp.getOrderNum();

        SysMenu pMenu = temp.getParent();
        if (null != pMenu) {
            parent = new MenuVo();
            parent.copyForEntity(pMenu);
        }

        Set<SysMenu> cMenus = temp.getChildren();
        if (null != cMenus && !cMenus.isEmpty()) {
            MenuVo child;
            for (SysMenu cMenu : cMenus) {
                child = new MenuVo();
                child.copyForEntity(cMenu);
                children.add(child);
            }
        }

        this.menuUrl = temp.getMenuUrl();
        this.menuType = temp.getMenuType();
    }

    @Override
    public SysMenu copyToEntity() {
        SysMenu parentEntity = null;
        if (null != parent) {
            parentEntity = copyToEntity(parent);
        }
        Set<SysMenu> childrenEntity = null;
        if (null != children && !children.isEmpty()) {
            childrenEntity = Sets.newHashSet();
            for (MenuVo child : children) {
                childrenEntity.add(copyToEntity(child));
            }
        }
        return new SysMenu(id, menuUrl, menuType, code, text, fullCode, fullText, parentEntity, childrenEntity, orderNum, valid,
                creator, createTime, reviser, modifyTime);
    }

    @Override
    public SysMenu copyToEntity(MenuVo v) {
        SysMenu parentEntity = null;
        MenuVo parent = v.getParent();
        if (null != parent) {
            parentEntity = copyToEntity(parent);
        }
        Set<SysMenu> childrenEntity = null;
        List<MenuVo> children = v.getChildren();
        if (null != children && !children.isEmpty()) {
            childrenEntity = Sets.newHashSet();
            for (MenuVo child : children) {
                childrenEntity.add(copyToEntity(child));
            }
        }
        return new SysMenu(v.getId(), v.getMenuUrl(), v.getMenuType(), v.getCode(), v.getText(), v.getFullCode(), v.getFullText(),
                parentEntity, childrenEntity, v.getOrderNum(), v.getValid(), v.getCreator(), v.getCreateTime(), v.getReviser(),
                v.getModifyTime());
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
        if (!(obj instanceof MenuVo))
            return false;
        MenuVo other = (MenuVo) obj;
        if (id == null) {
            return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}