/**
 * platform-service
 * ISysSiteDao.java
 */
package com.flyhaze.platform.dao;

import com.flyhaze.framework.hibernate.dao.IDao;
import com.flyhaze.platform.entity.SysSite;

/**
 * ISysSiteDao
 *
 * 系统站点DAO接口
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-19 21:09:53
 * @see com.flyhaze.framework.dao.IDao<SysSite>
 * @since jdk1.8
 */
public interface ISiteDao extends IDao<SysSite> {

    /**
     * 检查站点是否可以删除
     * @param id
     * @return
     */
    boolean canDel(String[] id);
}