/**
 * platform-web
 * MenuModel.java
 */
package com.liaowei.platform.model;

import java.util.List;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.google.common.collect.Lists;
import com.liaowei.framework.model.BaseTreeModel;
import com.liaowei.platform.entity.SysMenu;
import com.liaowei.platform.enums.MenuTypeEnum;
import com.liaowei.platform.vo.MenuVo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * MenuModel
 *
 * 菜单信息
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-21 23:19:53
 * @see com.liaowei.framework.model.BaseModel
 * @since jdk1.8
 */
@SuppressWarnings("serial")
@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
public class MenuModel extends BaseTreeModel<SysMenu, MenuVo, MenuModel> {

    /**
     * 菜单地址
     */
    private String menuUrl;

    /**
     * 菜单类型：分系统SYSTEM、菜单MENU、链接LINK、按钮BUTTON
     */
    @Enumerated(value = EnumType.STRING)
    private MenuTypeEnum menuType;

    @Override
    public void copyForVo(MenuVo temp) {
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

        MenuVo pMenu = temp.getParent();
        if (null != pMenu) {
            parent = new MenuModel();
            parent.copyForVo(pMenu);
        }

        List<MenuVo> cMenus = temp.getChildren();
        if (null != cMenus && !cMenus.isEmpty()) {
            MenuModel child;
            for (MenuVo cMenu : cMenus) {
                child = new MenuModel();
                child.copyForVo(cMenu);
                children.add(child);
            }
        }

        this.menuUrl = temp.getMenuUrl();
        this.menuType = temp.getMenuType();
    }

    @Override
    public MenuVo copyToVo() {
        MenuVo parentVo = null;
        if (null != parent) {
            parentVo = copyToVo(parent);
        }
        List<MenuVo> childrenVo = null;
        if (null != children && !children.isEmpty()) {
            childrenVo = Lists.newArrayList();
            for (MenuModel child : children) {
                childrenVo.add(copyToVo(child));
            }
        }
        return new MenuVo(id, menuUrl, menuType, code, text, fullCode, fullText, parentVo, childrenVo, orderNum, valid, creator,
                createTime, reviser, modifyTime);
    }

    @Override
    public MenuVo copyToVo(MenuModel model) {
        MenuVo parentVo = null;
        MenuModel parent = model.getParent();
        if (null != parent) {
            parentVo = copyToVo(parent);
        }
        List<MenuVo> childrenVo = null;
        List<MenuModel> children = model.getChildren();
        if (null != children && !children.isEmpty()) {
            childrenVo = Lists.newArrayList();
            for (MenuModel child : children) {
                childrenVo.add(copyToVo(child));
            }
        }
        return new MenuVo(model.getId(), model.getMenuUrl(), model.getMenuType(), model.getCode(), model.getText(),
                model.getFullCode(), model.getFullText(), parentVo, childrenVo, model.getOrderNum(), model.getValid(),
                model.getCreator(), model.getCreateTime(), model.getReviser(), model.getModifyTime());
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
        if (!(obj instanceof MenuModel))
            return false;
        MenuModel other = (MenuModel) obj;
        if (id == null) {
            return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}