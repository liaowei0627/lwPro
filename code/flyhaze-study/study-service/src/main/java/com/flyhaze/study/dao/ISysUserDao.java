package com.flyhaze.study.dao;

import com.flyhaze.framework.hibernate.dao.IDao;
import com.flyhaze.study.entity.SysUser;

public interface ISysUserDao extends IDao<SysUser> {

    SysUser findByUserName(String userName);
}