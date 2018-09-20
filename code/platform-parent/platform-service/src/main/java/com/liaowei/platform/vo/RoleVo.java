/**
 * platform-service
 * RoleVo.java
 */
package com.liaowei.platform.vo;

import java.util.List;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.liaowei.framework.vo.BaseVo;
import com.liaowei.platform.entity.SysRole;
import com.liaowei.platform.enums.RoleTypeEnum;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * RoleVo
 *
 * 角色信息
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-19 21:21:34
 * @see com.liaowei.framework.vo.BaseVo<SysRole, RoleVo>
 * @since jdk1.8
 */
@SuppressWarnings("serial")
@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
public class RoleVo extends BaseVo<SysRole, RoleVo> {

    /**
     * 角色编号
     */
    private String roleCode;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色类型
     * 
     * @see com.liaowei.platform.enums.RoleTypeEnum
     */
    @Enumerated(value = EnumType.STRING)
    private RoleTypeEnum roleType;

    /**
     * 是否内置
     */
    protected Boolean builtIn;

    /**
     * 角色包含的权限集合
     */
    private List<AuthorityVo> authorities;

    @Override
    public void copyForEntity(SysRole temp) {
        id = temp.getId();
        valid = temp.getValid();
        creator = temp.getCreator();
        createTime = temp.getCreateTime();
        reviser = temp.getReviser();
        modifyTime = temp.getModifyTime();

        roleCode = temp.getRoleCode();
        roleName = temp.getRoleName();
        roleType = temp.getRoleType();
        builtIn = temp.getBuiltIn();
    }

    @Override
    public SysRole copyToEntity() {
        return new SysRole(id, roleCode, roleName, roleType, builtIn, valid, creator, createTime, reviser, modifyTime);
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
            return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}