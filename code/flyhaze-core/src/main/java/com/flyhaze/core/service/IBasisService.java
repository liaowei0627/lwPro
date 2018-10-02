/**
 * flyhaze-core
 * IBasisService.java
 */
package com.flyhaze.core.service;

import java.io.Serializable;
import java.util.List;

import com.flyhaze.core.entity.IBasisIdEntity;
import com.flyhaze.core.exception.ApplicationException;
import com.flyhaze.core.page.Pagination;
import com.flyhaze.core.query.Where;
import com.flyhaze.core.query.order.OrderBy;
import com.flyhaze.core.vo.IBasisIdVo;

/**
 * IBasisService
 *
 * Service层超类接口，基本的增删改查接口
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-08 21:25:25
 * @since jdk1.8
 */
public interface IBasisService<E extends IBasisIdEntity<E>, V extends IBasisIdVo<E, V>, PK extends Serializable> {

    /**
     * 根据主键值查询数据对象
     * 
     * @param pk
     * @return
     * @throws ApplicationException
     */
    V findVo(PK id) throws ApplicationException;

    /**
     * 查询数据列表
     * 
     * @param where hql查询条件对象
     * @param orderBy 排序字段
     * @return
     * @throws ApplicationException
     */
    List<V> findList(Where where, List<OrderBy> orderBy) throws ApplicationException;

    /**
     * 查询数据分页列表
     * 
     * @param pagination 分页对象
     * @param where hql查询条件对象
     * @return
     * @throws ApplicationException
     */
    Pagination<V> findList(Pagination<V> pagination, Where where) throws ApplicationException;

    /**
     * 新增数据
     * 
     * @param vo
     * @return
     * @throws ApplicationException
     */
    V addVo(V vo) throws ApplicationException;

    /**
     * 修改数据
     * 
     * @param vo
     * @return
     * @throws ApplicationException
     */
    V updateVo(V vo) throws ApplicationException;

    /**
     * 批量删除数据
     * 
     * @param pks
     * @return 
     * @throws ApplicationException
     */
    String delList(PK[] ids) throws ApplicationException;
}