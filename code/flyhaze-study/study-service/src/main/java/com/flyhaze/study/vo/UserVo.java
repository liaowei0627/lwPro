/**
 * study-service
 * UserVo.java
 */
package com.flyhaze.study.vo;

import java.time.LocalDateTime;

import com.flyhaze.framework.vo.BaseVo;
import com.flyhaze.study.entity.SysUser;

/**
 * UserVo
 *
 * 用户信息
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-09 22:35:43
 * @see com.flyhaze.framework.service.vo.BaseVo<SysUser>
 * @since jdk1.8
 */
@SuppressWarnings("serial")
public class UserVo extends BaseVo<SysUser, UserVo> {

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    public UserVo() {
        super();
    }

    public UserVo(String id, String userName, String password, Boolean valid, String creator, LocalDateTime createTime,
            String reviser, LocalDateTime modifyTime) {
        super(id, valid, creator, createTime, reviser, modifyTime);
        this.userName = userName;
        this.password = password;
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
    }

    @Override
    public SysUser copyToEntity() {
        return new SysUser(id, userName, password, valid, creator, createTime, reviser, modifyTime);
    }

    /**
     * 用户名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 用户名
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 密码
     */
    public void setPassword(String password) {
        this.password = password;
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