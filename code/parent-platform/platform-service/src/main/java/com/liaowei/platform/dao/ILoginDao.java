/**
 * platform-service
 * ILoginDao.java
 */
package com.liaowei.platform.dao;

import com.liaowei.platform.entity.SysUser;

/**
 * ILoginDao
 *
 * 登录相关DAO接口
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-15 11:16:50
 * @since jdk1.8
 */
public interface ILoginDao {

    SysUser findByUserName(String userName);
}