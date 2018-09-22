/**
 * platform-service
 * AuthDataType.java
 */
package com.flyhaze.platform.vo;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.flyhaze.framework.vo.BaseIdVo;
import com.flyhaze.platform.entity.SysAuthDataType;
import com.flyhaze.platform.enums.DataAuthTypeEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * AuthDataType
 *
 * 系统权限——数据权限类型关系表实体类
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-21 00:54:56
 * @see com.flyhaze.framework.vo.BaseIdVo<SysAuthDataType, AuthDataTypeVo>
 * @since jdk1.8
 */
@SuppressWarnings("serial")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
public class AuthDataTypeVo extends BaseIdVo<SysAuthDataType, AuthDataTypeVo> {


    /**
     * 系统权限ID
     */
    private String sysAuthId;

    /**
     * 站点ID，多个用“,”分隔
     */
    private String sites;

    /**
     * 数据权限类型
     * 
     * @see com.flyhaze.platform.enums.DataAuthTypeEnum
     */
    @Enumerated(value = EnumType.STRING)
    private DataAuthTypeEnum dataAuthType;

    @Override
    public void copyForEntity(SysAuthDataType temp) {
        id = temp.getId();
        sysAuthId = temp.getSysAuthId();
        sites = temp.getSites();
        dataAuthType = temp.getDataAuthType();
    }

    @Override
    public SysAuthDataType copyToEntity() {
        return new SysAuthDataType(id, sysAuthId, sites, dataAuthType);
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
            return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}