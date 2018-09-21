/**
 * platform-web
 * MenuModel.java
 */
package com.flyhaze.platform.model;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.flyhaze.framework.model.BaseTreeModel;
import com.flyhaze.platform.entity.SysMenu;
import com.flyhaze.platform.enums.MenuTypeEnum;
import com.flyhaze.platform.enums.SubSystemEnum;
import com.flyhaze.platform.vo.MenuVo;

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
 * @see com.flyhaze.framework.model.BaseTreeModel<SysMenu, MenuVo, MenuModel>
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
     * 菜单类型
     * 
     * @see com.flyhaze.platform.enums.MenuTypeEnum
     */
    @Enumerated(value = EnumType.STRING)
    private MenuTypeEnum menuType;

    /**
     * 分系统
     * 
     * @see com.flyhaze.platform.enums.SubSystemEnum
     */
    @Enumerated(value = EnumType.STRING)
    private SubSystemEnum subSystem;

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
        hasChild = temp.getHasChild();

        MenuVo pMenu = temp.getParent();
        if (null != pMenu) {
            parent = new MenuModel();
            parent.copyForVo(pMenu);
        }

        menuUrl = temp.getMenuUrl();
        menuType = temp.getMenuType();
        subSystem = temp.getSubSystem();
    }

    @Override
    public MenuVo copyToVo() {
        MenuVo parentVo = null;
        if (null != parent) {
            parentVo = copyToVo(parent);
        }
        return new MenuVo(id, menuUrl, menuType, subSystem, code, text, fullCode, fullText, parentVo, null, orderNum, null, valid,
                creator, createTime, reviser, modifyTime);
    }

    @Override
    public MenuVo copyToVo(MenuModel model) {
        MenuVo parentVo = null;
        MenuModel parent = model.getParent();
        if (null != parent) {
            parentVo = copyToVo(parent);
        }
        return new MenuVo(model.getId(), model.getMenuUrl(), model.getMenuType(), model.getSubSystem(), model.getCode(),
                model.getText(), model.getFullCode(), model.getFullText(), parentVo, null, model.getOrderNum(), null,
                model.getValid(), model.getCreator(), model.getCreateTime(), model.getReviser(), model.getModifyTime());
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