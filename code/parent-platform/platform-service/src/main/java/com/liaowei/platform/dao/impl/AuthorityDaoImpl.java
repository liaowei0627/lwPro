/**
 * platform-service
 * SysAuthorityDaoImpl.java
 */
package com.liaowei.platform.dao.impl;

import org.springframework.stereotype.Repository;

import com.liaowei.framework.dao.impl.DaoImpl;
import com.liaowei.platform.dao.IAuthorityDao;
import com.liaowei.platform.entity.SysAuthority;

/**
 * SysAuthorityDaoImpl
 *
 * 系统权限DAO实现
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-19 21:13:51
 * @see com.liaowei.platform.dao.IAuthorityDao
 * @see com.liaowei.framework.dao.impl.DaoImpl<SysAuthority, String>
 * @since jdk1.8
 */
@Repository("authorityDao")
public class AuthorityDaoImpl extends DaoImpl<SysAuthority> implements IAuthorityDao {

    @Override
    protected Class<SysAuthority> getEntityClass() {
        return SysAuthority.class;
    }
}