/**
 * study-web
 * SessionUser.java
 */
package com.liaowei.study;

import java.io.Serializable;

/**
 * SessionUser
 *
 * 当前登录用户对象
 *
 * @author liaowei
 * @date 创建时间：2018年4月6日 下午6:14:17 
 * @see java.io.Serializable
 * @since jdk1.8
 */
@SuppressWarnings("serial")
public class SessionUser implements Serializable {

    private String id;
    private String userName;

    public SessionUser() {
    }

    public SessionUser(String id, String userName) {
        this.id = id;
        this.userName = userName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}