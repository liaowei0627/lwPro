/**
 * framework-core
 * IBaseDao.java
 */
package com.liaowei.framework.core.dao;

import java.io.Serializable;
import java.util.List;

import com.liaowei.framework.core.entity.IBaseEntity;
import com.liaowei.framework.core.exception.ApplicationException;
import com.liaowei.framework.page.Pagination;

/**
 * IBaseDao
 *
 * Dao层超类接口，基本的增删改查接口
 *
 * @author liaowei
 * @date 创建时间：2018年4月1日 上午9:48:36 
 * @since jdk1.8
 */
public interface IBaseDao<T extends IBaseEntity, PK extends Serializable> {

    /**
     * 通过主键查询一个实体对象
     * 
     * @param pk 主键值
     * @return
     * @throws ServiceException
     */
    T findEntity(PK pk);

    /**
     * insert一个实体对象
     * 
     * @param entity
     * @return
     * @throws ServiceException
     */
    T addEntity(T entity);

    /**
     * update一个实体对象
     * 
     * @param entity
     * @return
     * @throws ServiceException
     */
    T updateEntity(T entity);

    /**
     * 查询数据集合
     * 
     * @param entity 用作查询条件的实体对象
     * @return
     * @throws Exception 
     * @throws ServiceException
     */
    List<T> findList(T entity) throws ApplicationException;

    /**
     * 分页查询
     * 
     * @param page 分页对象
     * @param entity 用作查询条件的实体对象
     * @return
     * @throws ServiceException
     */
    Pagination<T> findPage(Pagination<T> page, T entity) throws ApplicationException;

    /**
     * 逻辑删除一条数据
     * 
     * @param pk 要逻辑删除数据的主键值
     * @throws ServiceException
     */
    void delEntity(PK pk);

    /**
     * 逻辑删除批量数据
     * 
     * @param pks 要逻辑删除数据的主键值集合
     * @throws ServiceException
     */
    void delList(List<PK> pks);
}
