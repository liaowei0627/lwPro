package com.flyhaze.sample.clazz;

public class SamplePojo extends SampleSuperPojo {

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    private SamplePojo parent;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public SamplePojo getParent() {
        return parent;
    }

    public void setParent(SamplePojo parent) {
        this.parent = parent;
    }
}