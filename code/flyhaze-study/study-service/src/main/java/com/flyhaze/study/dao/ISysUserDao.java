package com.flyhaze.study.dao;

import com.flyhaze.framework.dao.IDao;
import com.flyhaze.study.entity.SysUser;

public interface ISysUserDao extends IDao<SysUser> {

    SysUser findByUserName(String userName);
}