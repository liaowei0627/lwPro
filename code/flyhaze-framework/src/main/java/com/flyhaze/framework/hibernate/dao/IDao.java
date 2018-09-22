/**
 * flyhaze-framework
 * IDao.java
 */
package com.flyhaze.framework.hibernate.dao;

import com.flyhaze.framework.core.dao.IBasisDao;
import com.flyhaze.framework.hibernate.entity.BaseIdEntity;

/**
 * IDao
 *
 * Dao层超类接口，基本的增删改查接口
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-08 21:33:14
 * @see com.flyhaze.framework.core.dao.IBasisDao<E, String>
 * @since jdk1.8
 */
public interface IDao<E extends BaseIdEntity<E>> extends IBasisDao<E, String> {
}