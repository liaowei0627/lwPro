/**
 * platform-web
 * UserModel.java
 */
package com.liaowei.platform.model;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.liaowei.framework.model.BaseModel;
import com.liaowei.platform.entity.SysUser;
import com.liaowei.platform.enums.SubSystemEnum;
import com.liaowei.platform.vo.UserVo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * UserModel
 *
 * 用户信息
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-15 14:40:01
 * @see com.liaowei.framework.model.BaseModel<SysUser, UserVo, UserModel>
 * @since jdk1.8
 */
@SuppressWarnings("serial")
@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
public class UserModel extends BaseModel<SysUser, UserVo, UserModel> {

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 分系统
     * 
     * @see com.liaowei.platform.enums.SubSystemEnum
     */
    @Enumerated(value = EnumType.STRING)
    private SubSystemEnum subSystem;

    /**
     * 站点编号
     */
    private String siteCode;

    /**
     * 是否内置
     */
    protected Boolean builtIn;

    @Override
    public void copyForVo(UserVo temp) {
        id = temp.getId();
        valid = temp.getValid();
        creator = temp.getCreator();
        createTime = temp.getCreateTime();
        reviser = temp.getReviser();
        modifyTime = temp.getModifyTime();

        userName = temp.getUserName();
        password = temp.getPassword();
        subSystem = temp.getSubSystem();
        siteCode = temp.getSiteCode();
        builtIn = temp.getBuiltIn();
    }

    @Override
    public UserVo copyToVo() {
        return new UserVo(id, userName, password, subSystem, siteCode, builtIn, valid, creator, createTime, reviser, modifyTime);
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
            return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}