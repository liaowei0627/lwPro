/**
 * platform-service
 * SysRoleDaoImpl.java
 */
package com.flyhaze.platform.dao.impl;

import org.springframework.stereotype.Repository;

import com.flyhaze.framework.hibernate.dao.impl.DaoImpl;
import com.flyhaze.platform.dao.IRoleDao;
import com.flyhaze.platform.entity.SysRole;

/**
 * SysRoleDaoImpl
 *
 * 系统角色DAO实现
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-19 21:16:00
 * @see com.flyhaze.platform.dao.IRoleDao
 * @see com.flyhaze.framework.hibernate.dao.impl.DaoImpl<SysRole>
 * @since jdk1.8
 */
@Repository("roleDao")
public class RoleDaoImpl extends DaoImpl<SysRole> implements IRoleDao {

    @Override
    protected Class<SysRole> getEntityClass() {
        return SysRole.class;
    }
}