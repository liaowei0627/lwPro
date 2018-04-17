/**
 * platform-web
 * UserModel.java
 */
package com.liaowei.platform.model;

import java.time.LocalDateTime;

import com.liaowei.framework.model.BaseModel;
import com.liaowei.platform.vo.UserVo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * UserModel
 *
 * 用户信息
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-15 14:40:01
 * @see com.liaowei.framework.model.BaseModel
 * @since jdk1.8
 */
@SuppressWarnings("serial")
@Getter
@Setter
@ToString(callSuper = true)
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