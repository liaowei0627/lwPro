package com.liaowei.study.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.liaowei.framework.entity.SpringBaseEntity;

@SuppressWarnings("serial")
@Entity
@Table(name = "SYS_USERS")
public class SysUser extends SpringBaseEntity {

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    public SysUser() {
        super();
    }

    public SysUser(String id, String userName, String password, Boolean valid, String creator, LocalDateTime createTime, String reviser, LocalDateTime modifyTime) {
        super(id, valid, creator, createTime, reviser, modifyTime);
        this.userName = userName;
        this.password = password;
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