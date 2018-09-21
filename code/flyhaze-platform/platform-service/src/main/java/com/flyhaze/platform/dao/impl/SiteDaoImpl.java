/**
 * platform-service
 * SysSiteDaoImpl.java
 */
package com.flyhaze.platform.dao.impl;

import org.springframework.stereotype.Repository;

import com.flyhaze.framework.dao.impl.DaoImpl;
import com.flyhaze.platform.dao.ISiteDao;
import com.flyhaze.platform.entity.SysSite;

/**
 * SysSiteDaoImpl
 *
 * 系统站点DAO实现
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-19 21:11:05
 * @see com.flyhaze.platform.dao.ISiteDao
 * @see com.flyhaze.framework.dao.impl.DaoImpl<SysSite>
 * @since jdk1.8
 */
@Repository("siteDao")
public class SiteDaoImpl extends DaoImpl<SysSite> implements ISiteDao {

    @Override
    protected Class<SysSite> getEntityClass() {
        return SysSite.class;
    }

    @Override
    public boolean canDel(String[] id) {
        // TODO 检查站点是否可删除
        return true;
    }
}