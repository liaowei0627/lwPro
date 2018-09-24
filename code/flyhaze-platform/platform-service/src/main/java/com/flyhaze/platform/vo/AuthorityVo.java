/**
 * platform-service
 * AuthorityVo.java
 */
package com.flyhaze.platform.vo;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.flyhaze.framework.vo.BaseVo;
import com.flyhaze.platform.entity.SysAuthority;
import com.flyhaze.platform.enums.AuthTypeEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * AuthorityVo
 *
 * 权限信息
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-19 21:53:04
 * @see com.flyhaze.framework.vo.BaseVo<SysAuthority, AuthorityVo>
 * @since jdk1.8
 */
@SuppressWarnings("serial")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
public class AuthorityVo extends BaseVo<SysAuthority, AuthorityVo> {

    /**
     * 权限编号
     */
    private String authCode;

    /**
     * 权限名称
     */
    private String authName;

    /**
     * 备注
     */
    private String remark;

    /**
     * 权限类型
     * 
     * @see com.flyhaze.platform.enums.AuthTypeEnum
     */
    @Enumerated(value = EnumType.STRING)
    private AuthTypeEnum authType;

    /**
     * 是否内置
     */
    protected Boolean builtIn;

    /**
     * 权限拥有的数据权限集合
     * 
     * @see com.flyhaze.platform.vo.AuthDataTypeVo
     */
    private List<AuthDataTypeVo> authDataTypes;

    /**
     * 权限拥有的菜单集合
     */
    private List<MenuVo> menus;

    public AuthorityVo(String id, String authCode, String authName, String remark, AuthTypeEnum authType, Boolean valid,
            String creator, LocalDateTime createTime, String reviser, LocalDateTime modifyTime) {
        super(id, valid, creator, createTime, reviser, modifyTime);
        this.authCode = authCode;
        this.authName = authName;
        this.remark = remark;
        this.authType = authType;
    }

    public AuthorityVo(String id, String authCode, String authName, String remark, AuthTypeEnum authType,
            List<AuthDataTypeVo> authDataTypes, List<MenuVo> menus, Boolean valid, String creator, LocalDateTime createTime,
            String reviser, LocalDateTime modifyTime) {
        super(id, valid, creator, createTime, reviser, modifyTime);
        this.authCode = authCode;
        this.authName = authName;
        this.remark = remark;
        this.authType = authType;
        this.authDataTypes = authDataTypes;
        this.menus = menus;
    }

    @Override
    public void copyForEntity(SysAuthority temp) {
        id = temp.getId();
        valid = temp.getValid();
        creator = temp.getCreator();
        createTime = temp.getCreateTime();
        reviser = temp.getReviser();
        modifyTime = temp.getModifyTime();

        authCode = temp.getAuthCode();
        authName = temp.getAuthName();
        remark = temp.getRemark();
        authType = temp.getAuthType();
        builtIn = temp.getBuiltIn();
    }

    @Override
    public SysAuthority copyToEntity() {
        return new SysAuthority(id, authCode, authName, remark, authType, builtIn, valid, creator, createTime, reviser,
                modifyTime);
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
            return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}