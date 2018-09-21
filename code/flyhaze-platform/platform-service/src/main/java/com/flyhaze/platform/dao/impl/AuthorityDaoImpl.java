/**
 * platform-service
 * SysAuthorityDaoImpl.java
 */
package com.flyhaze.platform.dao.impl;

import org.springframework.stereotype.Repository;

import com.flyhaze.framework.hibernate.dao.impl.DaoImpl;
import com.flyhaze.platform.dao.IAuthorityDao;
import com.flyhaze.platform.entity.SysAuthority;

/**
 * SysAuthorityDaoImpl
 *
 * 系统权限DAO实现
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-19 21:13:51
 * @see com.flyhaze.platform.dao.IAuthorityDao
 * @see com.flyhaze.framework.hibernate.dao.impl.DaoImpl<SysAuthority>
 * @since jdk1.8
 */
@Repository("authorityDao")
public class AuthorityDaoImpl extends DaoImpl<SysAuthority> implements IAuthorityDao {

    @Override
    protected Class<SysAuthority> getEntityClass() {
        return SysAuthority.class;
    }
}