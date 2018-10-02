/**
 * platform-service
 * UserVo.java
 */
package com.flyhaze.platform.vo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.flyhaze.framework.vo.BaseVo;
import com.flyhaze.platform.entity.SysSite;
import com.flyhaze.platform.entity.SysUser;
import com.flyhaze.platform.enums.SubSystemEnum;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * UserVo
 *
 * 用户信息
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-09 22:35:43
 * @see com.flyhaze.framework.vo.BaseVo<SysUser, UserVo>
 * @since jdk1.8
 */
@SuppressWarnings("serial")
@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
public class UserVo extends BaseVo<SysUser, UserVo> {

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 备注
     */
    private String remark;

    /**
     * 分系统
     * 
     * @see com.flyhaze.platform.enums.SubSystemEnum
     */
    @Enumerated(value = EnumType.STRING)
    private SubSystemEnum subSystem;

    /**
     * 站点
     */
    private SiteVo site;

    /**
     * 是否内置
     */
    protected Boolean builtIn;

    /**
     * 用户拥有的角色集合
     * 
     * @see com.flyhaze.platform.vo.RoleVo
     */
    private List<RoleVo> roles;

    /**
     * 用户拥有的权限集合
     * 
     * @see com.flyhaze.platform.vo.AuthorityVo
     */
    private List<AuthorityVo> authorities;

    /**
     * 用户拥有的数据权限集合
     * 
     * @see com.flyhaze.platform.vo.AuthDataTypeVo
     */
    private List<AuthDataTypeVo> authDataTypes;

    /**
     * 用户拥有的菜单集合
     */
    private Set<MenuVo> menus;

    public UserVo(String id, String userName, String password, String remark, SubSystemEnum subSystem, SiteVo site,
            Boolean valid, String creator, LocalDateTime createTime, String reviser, LocalDateTime modifyTime) {
        super(id, valid, creator, createTime, reviser, modifyTime);
        this.userName = userName;
        this.password = password;
        this.remark = remark;
        this.subSystem = subSystem;
        this.site = site;
    }

    public UserVo(String id, String userName, String password, String remark, SubSystemEnum subSystem, SiteVo site,
            List<RoleVo> roles, List<AuthorityVo> authorities, List<AuthDataTypeVo> authDataTypes, Set<MenuVo> menus,
            Boolean valid, String creator, LocalDateTime createTime, String reviser, LocalDateTime modifyTime) {
        super(id, valid, creator, createTime, reviser, modifyTime);
        this.userName = userName;
        this.password = password;
        this.subSystem = subSystem;
        this.site = site;
        this.roles = roles;
        this.authorities = authorities;
        this.authDataTypes = authDataTypes;
        this.menus = menus;
    }

    @Override
    public void copyForEntity(SysUser temp) {
        id = temp.getId();
        valid = temp.getValid();
        creator = temp.getCreator();
        createTime = temp.getCreateTime();
        reviser = temp.getReviser();
        modifyTime = temp.getModifyTime();

        userName = temp.getUserName();
        password = temp.getPassword();
        remark = temp.getRemark();
        subSystem = temp.getSubSystem();
        SysSite siteEntity = temp.getSite();
        if (null != siteEntity) {
            site = new SiteVo();
            site.copyForEntity(siteEntity);
        }
        builtIn = temp.getBuiltIn();
    }

    @Override
    public SysUser copyToEntity() {
        SysSite sityEntity = null;
        if (null != site) {
            sityEntity = site.copyToEntity();
        }
        return new SysUser(id, userName, password, remark, subSystem, sityEntity, valid, creator, createTime, reviser, modifyTime);
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
        if (!(obj instanceof UserVo))
            return false;
        UserVo other = (UserVo) obj;
        if (id == null) {
            return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}