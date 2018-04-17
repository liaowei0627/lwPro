/**
 * framework-core
 * IBasisService.java
 */
package com.liaowei.framework.core.service;

import java.io.Serializable;
import java.util.List;

import com.liaowei.framework.core.entity.IBasisEntity;
import com.liaowei.framework.core.exception.ApplicationException;
import com.liaowei.framework.core.vo.IBasisVo;
import com.liaowei.framework.page.Pagination;

/**
 * IBasisService
 *
 * Service层超类接口，基本的增删改查接口
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-08 21:25:25
 * @since jdk1.8
 */
public interface IBasisService<V extends IBasisVo, E extends IBasisEntity, PK extends Serializable> {

    /**
     * 根据主键值查询数据对象
     * 
     * @param pk
     * @return
     * @throws ApplicationException
     */
    V findVo(PK pk) throws ApplicationException;

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
     * 查询数据列表
     * 
     * @param vo
     * @return
     * @throws ApplicationException
     */
    List<V> findList(V vo) throws ApplicationException;

    /**
     * 查询数据分页列表
     * 
     * @param page
     * @param vo
     * @return
     * @throws ApplicationException
     */
    Pagination<V> findPage(Pagination<V> page, V vo) throws ApplicationException;

    /**
     * 删除一条数据
     * 
     * @param pk
     * @throws ApplicationException
     */
    void delOne(PK pk) throws ApplicationException;

    /**
     * 批量删除数据
     * 
     * @param pks
     * @throws ApplicationException
     */
    void delList(List<PK> pks) throws ApplicationException;
}