/**
 * platform-service
 * SysSiteDaoImpl.java
 */
package com.liaowei.platform.dao.impl;

import org.springframework.stereotype.Repository;

import com.liaowei.framework.dao.impl.DaoImpl;
import com.liaowei.platform.dao.ISiteDao;
import com.liaowei.platform.entity.SysSite;

/**
 * SysSiteDaoImpl
 *
 * 系统站点DAO实现
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-19 21:11:05
 * @see com.liaowei.platform.dao.ISiteDao
 * @see com.liaowei.framework.dao.impl.DaoImpl<SysSite, String>
 * @since jdk1.8
 */
@Repository("siteDao")
public class SiteDaoImpl extends DaoImpl<SysSite, String> implements ISiteDao {

    @Override
    protected Class<SysSite> getEntityClass() {
        return SysSite.class;
    }
}