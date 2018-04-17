/**
 * platform-service
 * RoleVo.java
 */
package com.liaowei.platform.vo;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.liaowei.framework.vo.BaseVo;
import com.liaowei.platform.enums.RoleTypeEnum;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * RoleVo
 *
 * 角色信息
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-19 21:21:34
 * @see com.liaowei.framework.service.vo.BaseVo
 * @since jdk1.8
 */
@SuppressWarnings("serial")
@Getter
@Setter
@ToString(callSuper = true)
public class RoleVo extends BaseVo {

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色类型：管理员ADMINS、用户USERS
     */
    @Enumerated(value = EnumType.STRING)
    private RoleTypeEnum roleType;

    /**
     * 角色包含的权限集合
     */
    private List<AuthorityVo> authorities;

    public RoleVo() {
        super();
    }

    public RoleVo(String id, String roleName, RoleTypeEnum roleType, Boolean valid, String creator, LocalDateTime createTime,
            String reviser, LocalDateTime modifyTime) {
        super(id, valid, creator, createTime, reviser, modifyTime);
        this.roleName = roleName;
        this.roleType = roleType;
    }

    public RoleVo(String id, String roleName, RoleTypeEnum roleType, List<AuthorityVo> authorities, Boolean valid, String creator, LocalDateTime createTime,
            String reviser, LocalDateTime modifyTime) {
        super(id, valid, creator, createTime, reviser, modifyTime);
        this.roleName = roleName;
        this.roleType = roleType;
        this.authorities = authorities;
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
        if (!(obj instanceof RoleVo))
            return false;
        RoleVo other = (RoleVo) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}