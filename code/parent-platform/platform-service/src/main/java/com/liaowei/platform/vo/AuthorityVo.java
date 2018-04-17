/**
 * platform-service
 * AuthorityVo.java
 */
package com.liaowei.platform.vo;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.liaowei.framework.vo.BaseVo;
import com.liaowei.platform.enums.AuthTypeEnum;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * AuthorityVo
 *
 * 权限信息
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-19 21:53:04
 * @see com.liaowei.framework.service.vo.BaseVo
 * @since jdk1.8
 */
@SuppressWarnings("serial")
@Getter
@Setter
@ToString(callSuper = true)
public class AuthorityVo extends BaseVo {

    /**
     * 权限名称
     */
    private String authName;

    /**
     * 权限类型：访问权限ACCESS、数据权限DATA
     */
    @Enumerated(value = EnumType.STRING)
    private AuthTypeEnum authType;

    /**
     * 权限拥有的数据权限集合
     */
    private List<AuthDataTypeVo> authDataTypes;

    /**
     * 权限拥有的菜单集合
     */
    private List<MenuVo> menus;

    public AuthorityVo() {
        super();
    }

    public AuthorityVo(String id, String authName, AuthTypeEnum authType, List<AuthDataTypeVo> authDataTypes, List<MenuVo> menus,
            Boolean valid, String creator, LocalDateTime createTime, String reviser, LocalDateTime modifyTime) {
        super(id, valid, creator, createTime, reviser, modifyTime);
        this.authName = authName;
        this.authType = authType;
        this.authDataTypes = authDataTypes;
        this.menus = menus;
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
        if (!(obj instanceof AuthorityVo))
            return false;
        AuthorityVo other = (AuthorityVo) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}