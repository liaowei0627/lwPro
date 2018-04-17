package com.liaowei.study.model;

import java.time.LocalDateTime;

import com.liaowei.framework.model.BaseModel;
import com.liaowei.study.vo.UserVo;

@SuppressWarnings("serial")
public class UserModel extends BaseModel {

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    public UserModel() {
        super();
    }

    public UserModel(String id, String userName, String password, Boolean valid, String creator, LocalDateTime createTime, String reviser, LocalDateTime modifyTime) {
        super(id, valid, creator, createTime, reviser, modifyTime);
        this.userName = userName;
        this.password = password;
    }

    /**
     * 从UserVo对象生成UserModel对象
     * 
     * @param userVo
     * @return
     */
    public static UserModel forVo(UserVo userVo) {
        return new UserModel(userVo.getId(), userVo.getUserName(), userVo.getPassword(), userVo.getValid(), userVo.getCreator(), userVo.getCreateTime(), userVo.getReviser(), userVo.getModifyTime());
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
        if (!(obj instanceof UserModel))
            return false;
        UserModel other = (UserModel) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}