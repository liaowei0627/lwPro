/**
 * platform-service
 * MenuDaoImpl.java
 */
package com.flyhaze.platform.dao.impl;

import org.springframework.stereotype.Repository;

import com.flyhaze.framework.dao.impl.DaoImpl;
import com.flyhaze.platform.dao.IMenuDao;
import com.flyhaze.platform.entity.SysMenu;

/**
 * MenuDaoImpl
 *
 * 菜单管理DAO实现
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-21 23:42:25
 * @see com.flyhaze.platform.dao.IMenuDao
 * @see com.flyhaze.framework.dao.impl.DaoImpl<SysMenu>
 * @since jdk1.8
 */
@Repository("menuDao")
public class MenuDaoImpl extends DaoImpl<SysMenu> implements IMenuDao {

    @Override
    protected Class<SysMenu> getEntityClass() {
        return SysMenu.class;
    }
}