/**
 * platform-service
 * MenuDaoImpl.java
 */
package com.liaowei.platform.dao.impl;

import org.springframework.stereotype.Repository;

import com.liaowei.framework.dao.impl.DaoImpl;
import com.liaowei.platform.dao.IMenuDao;
import com.liaowei.platform.entity.SysMenu;

/**
 * MenuDaoImpl
 *
 * 菜单管理DAO实现
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-21 23:42:25
 * @see com.liaowei.platform.dao.IMenuDao
 * @see com.liaowei.framework.dao.impl.DaoImpl<SysMenu, String>
 * @since jdk1.8
 */
@Repository("menuDao")
public class MenuDaoImpl extends DaoImpl<SysMenu, String> implements IMenuDao {

    @Override
    protected Class<SysMenu> getEntityClass() {
        return SysMenu.class;
    }
}