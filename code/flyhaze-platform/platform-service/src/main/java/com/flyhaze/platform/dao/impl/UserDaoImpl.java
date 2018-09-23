/**
 * platform-service
 * SysUserDaoImpl.java
 */
package com.flyhaze.platform.dao.impl;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.flyhaze.framework.hibernate.dao.impl.DaoImpl;
import com.flyhaze.platform.dao.IUserDao;
import com.flyhaze.platform.entity.SysUser;

/**
 * SysUserDaoImpl
 *
 * 系统用户DAO实现
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-19 21:00:20
 * @see com.flyhaze.platform.dao.IUserDao
 * @see com.flyhaze.framework.hibernate.dao.impl.DaoImpl<SysUser>
 * @since jdk1.8
 */
@Repository("userDao")
public class UserDaoImpl extends DaoImpl<SysUser> implements IUserDao {

    @Override
    protected Class<SysUser> getEntityClass() {
        return SysUser.class;
    }

    @Override
    public SysUser findByUserName(String userName) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "from SysUser t where t.userName = :userName and t.valid = true";
        Query<SysUser> query = session.createQuery(hql, SysUser.class);
        query.setParameter("userName", userName);
        SysUser sysUser = query.uniqueResult();
        return sysUser;
    }
}