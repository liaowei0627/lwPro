/**
 * platform-service
 * SysRoleDaoImpl.java
 */
package com.liaowei.platform.dao.impl;

import org.springframework.stereotype.Repository;

import com.liaowei.framework.dao.impl.DaoImpl;
import com.liaowei.platform.dao.IRoleDao;
import com.liaowei.platform.entity.SysRole;

/**
 * SysRoleDaoImpl
 *
 * 系统角色DAO实现
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-19 21:16:00
 * @see com.liaowei.platform.dao.IRoleDao
 * @see com.liaowei.framework.dao.impl.DaoImpl<SysRole, String>
 * @since jdk1.8
 */
@Repository("roleDao")
public class RoleDaoImpl extends DaoImpl<SysRole, String> implements IRoleDao {

    @Override
    protected Class<SysRole> getEntityClass() {
        return SysRole.class;
    }
}