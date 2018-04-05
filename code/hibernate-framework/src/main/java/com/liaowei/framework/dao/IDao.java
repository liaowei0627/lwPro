/**
 * framework-core
 * IDao.java
 */
package com.liaowei.framework.dao;

import java.io.Serializable;

import com.liaowei.framework.core.dao.IBaseDao;
import com.liaowei.framework.entity.SpringBaseEntity;

/**
 * IDao
 *
 * Dao层超类接口，基本的增删改查接口
 *
 * @author liaowei
 * @date 创建时间：2018年4月1日 上午9:48:36 
 * @since jdk1.8
 */
public interface IDao<T extends SpringBaseEntity, PK extends Serializable> extends IBaseDao<T, PK> {
}