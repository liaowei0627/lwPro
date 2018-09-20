/**
 * platform-service
 * SysUserDaoImpl.java
 */
package com.liaowei.platform.dao.impl;

import org.springframework.stereotype.Repository;

import com.liaowei.framework.dao.impl.DaoImpl;
import com.liaowei.platform.dao.IUserDao;
import com.liaowei.platform.entity.SysUser;

/**
 * SysUserDaoImpl
 *
 * 系统用户DAO实现
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-19 21:00:20
 * @see com.liaowei.platform.dao.IUserDao
 * @see com.liaowei.framework.dao.impl.DaoImpl<SysUser>
 * @since jdk1.8
 */
@Repository("userDao")
public class UserDaoImpl extends DaoImpl<SysUser> implements IUserDao {

    @Override
    protected Class<SysUser> getEntityClass() {
        return SysUser.class;
    }
}