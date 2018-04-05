package com.liaowei.study.model;

import com.liaowei.framework.model.SpringBaseModel;
import com.liaowei.study.entity.SysUser;
import com.liaowei.study.vo.UserVo;

@SuppressWarnings("serial")
public class UserModel extends SpringBaseModel<UserVo, SysUser> {

    @Override
    public void copyVo(UserVo vo) {
    }

    @Override
    public UserVo toVo() {
        return null;
    }
}