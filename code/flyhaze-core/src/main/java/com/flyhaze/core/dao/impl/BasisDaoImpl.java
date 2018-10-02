/**
 * flyhaze-core
 * BasisDaoImpl.java
 */
package com.flyhaze.core.dao.impl;

import java.io.Serializable;

import com.flyhaze.core.dao.IBasisDao;
import com.flyhaze.core.entity.IBasisIdEntity;

/**
 * BasisDaoImpl
 *
 * Dao层超类实现
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-08 21:25:25
 * @see com.flyhaze.framework.core.dao.IBasisDao<E, PK>
 * @since jdk1.8
 */
public abstract class BasisDaoImpl<E extends IBasisIdEntity<E>, PK extends Serializable> implements IBasisDao<E, PK> {

    protected abstract Class<E> getEntityClass();
}