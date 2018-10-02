/**
 * flyhaze-core
 * IBasisDao.java
 */
package com.flyhaze.core.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.flyhaze.core.entity.IBasisIdEntity;
import com.flyhaze.core.exception.ApplicationException;
import com.flyhaze.core.page.Pagination;
import com.flyhaze.core.query.Where;
import com.flyhaze.core.query.order.OrderBy;

/**
 * IBasisDao
 *
 * Dao层超类接口，基本的增删改查接口
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-08 21:25:25
 * @since jdk1.8
 */
public interface IBasisDao<E extends IBasisIdEntity<E>, PK extends Serializable> {

    /**
     * 通过主键查询一个实体对象
     * 
     * @param pk 主键值
     * @return
     * @throws ApplicationException
     */
    E findEntity(PK id) throws ApplicationException;

    /**
     * 查询数据总数
     * @param where HQL查询条件对象
     * @return 
     * @throws ApplicationException
     */
    Long findCount(Where where) throws ApplicationException;

    /**
     * 查询数据列表
     * 
     * @param where HQL查询条件对象
     * @param orderBy 排序字段
     * @return
     * @throws ApplicationException
     */
    List<E> findList(Where where, List<OrderBy> orderBy) throws ApplicationException;

    /**
     * 分页查询数据列表
     * 
     * @param pagination 分页对象
     * @param where HQL查询条件对象
     * @return
     * @throws ApplicationException
     */
    Pagination<E> findList(Pagination<E> pagination, Where where) throws ApplicationException;

    /**
     * 指定HQL语句from子句查询数据列表
     * 
     * @param where HQL查询条件
     * @param from from子句
     * @param cls 返回数据类型
     * @return 返回数据类型数组
     * @throws ApplicationException
     */
    <C> List<C> findList(Where where, String from, Class<C> cls) throws ApplicationException;

    /**
     * 指定HQL语句from子句查询对象
     * 
     * @param where HQL查询条件
     * @param from from子句
     * @param cls 返回数据类型
     * @return 返回数据类型
     * @throws ApplicationException
     */
    <C> C findUnipueResult(Where where, String from, Class<C> cls) throws ApplicationException;

    /**
     * insert一个实体对象
     * 
     * @param entity
     * @return
     * @throws ApplicationException
     */
    E addEntity(E entity) throws ApplicationException;

    /**
     * update一个实体对象
     * 
     * @param entity
     * @return
     * @throws ApplicationException
     */
    E updateEntity(E entity) throws ApplicationException;

    /**
     * 逻辑删除批量数据
     * 
     * @param pks 要逻辑删除数据的主键值集合
     * @throws ApplicationException
     */
    void delList(PK[] id) throws ApplicationException;

    /**
     * 运行HQL语句
     * @param hql
     * @param param
     * @throws ApplicationException
     */
    void excByHQL(String hql, Map<String, Object> param) throws ApplicationException;
}