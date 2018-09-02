/**
 * framework-core
 * IDao.java
 */
package com.liaowei.framework.dao;

import java.io.Serializable;

import org.hibernate.service.spi.ServiceException;

import com.liaowei.framework.core.dao.IBasisDao;
import com.liaowei.framework.core.entity.IBasisIdEntity;
import com.liaowei.framework.core.exception.ApplicationException;
import com.liaowei.framework.page.Pagination;
import com.liaowei.framework.query.Where;

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
public interface IDao<E extends IBasisIdEntity, PK extends Serializable> extends IBasisDao<E, PK> {

    /**
     * 分页查询数据集合
     * 
     * @param pagination 分页对象
     * @param where hql查询条件对象
     * @return
     * @throws ServiceException
     */
    Pagination<E> findList(Pagination<E> pagination, Where where) throws ApplicationException;
}