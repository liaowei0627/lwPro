/**
 * platform-service
 * AuthDataType.java
 */
package com.liaowei.platform.vo;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.liaowei.framework.vo.BaseIdVo;
import com.liaowei.platform.enums.DataAuthTypeEnum;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * AuthDataType
 *
 * 系统权限——数据权限类型关系表实体类
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-21 00:54:56
 * @see com.liaowei.framework.service.vo.BaseVo
 * @since jdk1.8
 */
@SuppressWarnings("serial")
@Getter
@Setter
@ToString(callSuper = true)
public class AuthDataTypeVo extends BaseIdVo {


    /**
     * 系统权限ID
     */
    private String sysAuthId;

    /**
     * 站点ID，多个用“,”分隔
     */
    private String sites;

    /**
     * 数据权限类型：
     *    全部ALL、
     *    属于我的FOR_MY（creator为当前用户）、
     *    发送给我的TO_MY（submitTo为当前用户）、
     *    属于我角色的FOR_MYROLE（creator为拥有当前用户拥有的角色之一的用户）、
     *    发送给我角色的TO_MYROLE（submitTo为拥有当前用户拥有的角色之一的用户）
     */
    @Enumerated(value = EnumType.STRING)
    private DataAuthTypeEnum dataAuthType;

    public AuthDataTypeVo() {
        super();
    }

    public AuthDataTypeVo(String id, String sysAuthId, String sites, DataAuthTypeEnum dataAuthType) {
        super(id);
        this.sysAuthId = sysAuthId;
        this.sites = sites;
        this.dataAuthType = dataAuthType;
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
        if (!(obj instanceof AuthDataTypeVo))
            return false;
        AuthDataTypeVo other = (AuthDataTypeVo) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}