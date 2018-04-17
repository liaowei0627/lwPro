/**
 * platform-service
 * UserVo.java
 */
package com.liaowei.platform.vo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import com.liaowei.framework.vo.BaseVo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * UserVo
 *
 * 用户信息
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-09 22:35:43
 * @see com.liaowei.framework.service.vo.BaseVo
 * @since jdk1.8
 */
@SuppressWarnings("serial")
@Getter
@Setter
@ToString(callSuper = true)
public class UserVo extends BaseVo {

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 用户拥有的角色集合
     */
    private List<RoleVo> roles;

    /**
     * 用户拥有的权限集合
     */
    private List<AuthorityVo> authorities;

    /**
     * 用户拥有权限的站点集合
     */
    private List<SiteVo> sites;

    /**
     * 用户拥有的数据权限集合
     */
    private List<AuthDataTypeVo> authDataTypes;

    /**
     * 用户拥有的菜单集合
     */
    private Set<MenuVo> menus;

    public UserVo() {
        super();
    }

    public UserVo(String id, String userName, String password, Boolean valid, String creator, LocalDateTime createTime,
            String reviser, LocalDateTime modifyTime) {
        super(id, valid, creator, createTime, reviser, modifyTime);
        this.userName = userName;
        this.password = password;
    }

    public UserVo(String id, String userName, String password, List<RoleVo> roles, List<AuthorityVo> authorities,
            List<SiteVo> sites, List<AuthDataTypeVo> authDataTypes, Set<MenuVo> menus, Boolean valid, String creator,
            LocalDateTime createTime, String reviser, LocalDateTime modifyTime) {
        super(id, valid, creator, createTime, reviser, modifyTime);
        this.userName = userName;
        this.password = password;
        this.roles = roles;
        this.authorities = authorities;
        this.sites = sites;
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
        if (!(obj instanceof UserVo))
            return false;
        UserVo other = (UserVo) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}