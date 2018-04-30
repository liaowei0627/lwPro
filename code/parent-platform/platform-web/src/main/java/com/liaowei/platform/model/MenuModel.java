/**
 * platform-web
 * MenuModel.java
 */
package com.liaowei.platform.model;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.liaowei.framework.model.BaseTreeModel;
import com.liaowei.platform.enums.MenuTypeEnum;

import lombok.Getter;
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
@Getter
@Setter
@ToString(callSuper = true)
public class MenuModel extends BaseTreeModel<MenuModel> {

    /**
     * 菜单地址
     */
    private String menuUrl;

    /**
     * 菜单类型：分系统SYSTEM、菜单MENU、链接LINK、按钮BUTTON
     */
    @Enumerated(value = EnumType.STRING)
    private MenuTypeEnum menuType;

    public MenuModel() {
        super();
    }

    public MenuModel(String id, String menuUrl, MenuTypeEnum menuType, String code, String text, String fullCode, String fullText,
            MenuModel parent, Set<MenuModel> children, Integer orderNum, Boolean valid, String creator, LocalDateTime createTime,
            String reviser, LocalDateTime modifyTime) {
        super(id, code, text, fullCode, fullText, parent, children, orderNum, valid, creator, createTime, reviser, modifyTime);
        this.menuUrl = menuUrl;
        this.menuType = menuType;
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
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}