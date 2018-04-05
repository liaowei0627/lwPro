/**
 * study-service
 * UserVo.java
 */
package com.liaowei.study.vo;

import java.time.LocalDateTime;

import com.liaowei.framework.vo.SpringBaseVo;
import com.liaowei.study.entity.SysUser;

/**
 * UserVo
 *
 * TODO
 *
 * @author liaowei
 * @date 创建时间：2018年4月5日 下午12:31:02 
 * @see com.liaowei.framework.service.vo.SpringBaseVo<SysUser>
 * @since jdk1.8
 */
@SuppressWarnings("serial")
public class UserVo extends SpringBaseVo<SysUser> {

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

    public UserVo(String id, String userName, String password, Boolean valid, String creator, LocalDateTime createTime, String reviser, LocalDateTime modifyTime) {
        super(id, valid, creator, createTime, reviser, modifyTime);
        this.userName = userName;
        this.password = password;
    }

    @Override
    public void copyEntity(SysUser sysUser) {
        id = sysUser.getId();
        userName = sysUser.getUserName();
        password = sysUser.getPassword();
        valid = sysUser.getValid();
        creator = sysUser.getCreator();
        createTime = sysUser.getCreateTime();
        reviser = sysUser.getReviser();
        modifyTime = sysUser.getModifyTime();
    }

    @Override
    public SysUser toEntity() {
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

}
