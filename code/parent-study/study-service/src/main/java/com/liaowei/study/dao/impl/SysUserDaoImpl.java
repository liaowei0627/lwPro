package com.liaowei.study.dao.impl;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.liaowei.framework.dao.impl.DaoImpl;
import com.liaowei.study.dao.ISysUserDao;
import com.liaowei.study.entity.SysUser;

@Repository("sysUserDao")
public class SysUserDaoImpl extends DaoImpl<SysUser, String> implements ISysUserDao {

	@Resource(name = "sessionFactory")
	protected SessionFactory sessionFactory;

    protected Class<SysUser> getEntityClass() {
        return SysUser.class;
    }

    @Override
    protected String getClassName() {
        return SysUserDaoImpl.class.getName();
    }
}