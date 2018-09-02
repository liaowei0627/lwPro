/**
 * framework-core
 * IBasisService.java
 */
package com.liaowei.framework.core.service;

import java.io.Serializable;
import java.util.List;

import com.liaowei.framework.core.entity.IBasisIdEntity;
import com.liaowei.framework.core.exception.ApplicationException;
import com.liaowei.framework.core.vo.IBasisIdVo;

/**
 * IBasisService
 *
 * Service层超类接口，基本的增删改查接口
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-08 21:25:25
 * @since jdk1.8
 */
public interface IBasisService<V extends IBasisIdVo, E extends IBasisIdEntity, PK extends Serializable> {

    /**
     * 根据主键值查询数据对象
     * 
     * @param pk
     * @return
     * @throws ApplicationException
     */
    V findVo(PK id) throws ApplicationException;

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
     * 删除一条数据
     * 
     * @param pk
     * @throws ApplicationException
     */
    void delOne(PK id) throws ApplicationException;

    /**
     * 批量删除数据
     * 
     * @param pks
     * @throws ApplicationException
     */
    void delList(List<PK> ids) throws ApplicationException;
}