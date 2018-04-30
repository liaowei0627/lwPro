/**
 * framework-core
 * IBasisDao.java
 */
package com.liaowei.framework.core.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.liaowei.framework.core.entity.IBasisEntity;
import com.liaowei.framework.core.enums.OrderEnum;
import com.liaowei.framework.core.exception.ApplicationException;
import com.liaowei.framework.page.Pagination;

/**
 * IBasisDao
 *
 * Dao层超类接口，基本的增删改查接口
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-08 21:25:25
 * @see java.io.Serializable
 * @since jdk1.8
 */
public interface IBasisDao<E extends IBasisEntity, PK extends Serializable> {

    /**
     * 通过主键查询一个实体对象
     * 
     * @param pk 主键值
     * @return
     * @throws ServiceException
     */
    E findEntity(PK id);

    /**
     * insert一个实体对象
     * 
     * @param entity
     * @return
     * @throws ServiceException
     */
    E addEntity(E entity);

    /**
     * update一个实体对象
     * 
     * @param entity
     * @return
     * @throws ServiceException
     */
    E updateEntity(E entity);

    /**
     * 查询数据集合
     * 
     * @param entity 用作查询条件的实体对象
     * @param orderBy 排序Map：key entity字段名；value asc/desc
     * @return
     * @throws Exception 
     * @throws ServiceException
     */
    List<E> findList(E entity, Map<String, OrderEnum> orderBy) throws ApplicationException;

    /**
     * 分页查询
     * 
     * @param page 分页对象
     * @param entity 用作查询条件的实体对象
     * @param orderBy 排序Map：key entity字段名；value asc/desc
     * @return
     * @throws ServiceException
     */
    Pagination<E> findPage(Pagination<E> page, E entity, Map<String, OrderEnum> orderBy) throws ApplicationException;

    /**
     * 逻辑删除一条数据
     * 
     * @param pk 要逻辑删除数据的主键值
     * @throws ServiceException
     */
    void delEntity(PK id);

    /**
     * 逻辑删除批量数据
     * 
     * @param pks 要逻辑删除数据的主键值集合
     * @throws ServiceException
     */
    void delList(List<PK> ids);
}
