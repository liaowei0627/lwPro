/**
 * platform-service
 * MenuVo.java
 */
package com.liaowei.platform.vo;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.liaowei.framework.vo.BaseTreeVo;
import com.liaowei.platform.enums.MenuTypeEnum;

import lombok.Getter;
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
@Getter
@Setter
@ToString(callSuper = true)
public class MenuVo extends BaseTreeVo<MenuVo> {

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

    public MenuVo() {
        super();
    }

    public MenuVo(String id, String menuText, String menuUrl, MenuTypeEnum menuType, Integer orderNum, MenuVo parent,
            Set<MenuVo> children, Boolean valid, String creator, LocalDateTime createTime, String reviser,
            LocalDateTime modifyTime) {
        super(id, parent, children, orderNum, valid, creator, createTime, reviser, modifyTime);
        this.menuText = menuText;
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
        if (!(obj instanceof MenuVo))
            return false;
        MenuVo other = (MenuVo) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}