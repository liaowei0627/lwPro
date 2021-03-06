/**
 * platform-service
 * RoleVo.java
 */
package com.flyhaze.platform.vo;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.flyhaze.framework.vo.BaseVo;
import com.flyhaze.platform.entity.SysRole;
import com.flyhaze.platform.enums.RoleTypeEnum;

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
 * @see com.flyhaze.framework.vo.BaseVo<SysRole, RoleVo>
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
     * 备注
     */
    private String remark;

    /**
     * 角色类型
     * 
     * @see com.flyhaze.platform.enums.RoleTypeEnum
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

    public RoleVo(String id, String roleCode, String roleName, String remark, RoleTypeEnum roleType, Boolean valid,
            String creator, LocalDateTime createTime, String reviser, LocalDateTime modifyTime) {
        super(id, valid, creator, createTime, reviser, modifyTime);
        this.roleCode = roleCode;
        this.roleName = roleName;
        this.remark = remark;
        this.roleType = roleType;
    }

    public RoleVo(String id, String roleCode, String roleName, String remark, RoleTypeEnum roleType,
            List<AuthorityVo> authorities, Boolean valid, String creator, LocalDateTime createTime, String reviser,
            LocalDateTime modifyTime) {
        super(id, valid, creator, createTime, reviser, modifyTime);
        this.roleCode = roleCode;
        this.roleName = roleName;
        this.remark = remark;
        this.roleType = roleType;
        this.authorities = authorities;
    }

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
        remark = temp.getRemark();
        roleType = temp.getRoleType();
        builtIn = temp.getBuiltIn();
    }

    @Override
    public SysRole copyToEntity() {
        return new SysRole(id, roleCode, roleName, remark, roleType, valid, creator, createTime, reviser, modifyTime);
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