/**
 * framework-core
 * IDao.java
 */
package com.liaowei.framework.dao;

import com.liaowei.framework.core.dao.IBasisDao;
import com.liaowei.framework.entity.BaseIdEntity;

/**
 * IDao
 *
 * Dao层超类接口，基本的增删改查接口
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-08 21:33:14
 * @see com.liaowei.framework.core.dao.IBasisDao<E, PK>
 * @since jdk1.8
 */
public interface IDao<E extends BaseIdEntity<E>> extends IBasisDao<E, String> {
}