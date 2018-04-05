/**
 * framework-core
 * IBaseService.java
 */
package com.liaowei.framework.core.service;

import java.io.Serializable;
import java.util.List;

import com.liaowei.framework.core.entity.IBaseEntity;
import com.liaowei.framework.core.exception.ApplicationException;
import com.liaowei.framework.core.vo.IBaseVo;
import com.liaowei.framework.page.Pagination;

/**
 * IBaseService
 *
 * Service层超类接口，基本的增删改查接口
 *
 * @author liaowei
 * @date 创建时间：2018年4月1日 上午9:42:15 
 * @since jdk1.8
 */
public interface IBaseService<V extends IBaseVo<E>, E extends IBaseEntity, PK extends Serializable> {

    V findEntity(PK pk) throws ApplicationException;

    V addEntity(V vo) throws ApplicationException;

    V updateEntity(V vo) throws ApplicationException;

    List<V> findList(V vo) throws ApplicationException;

    Pagination<V> findPage(Pagination<V> page, V vo) throws ApplicationException;

    void delEntity(PK pk) throws ApplicationException;

    void delList(List<PK> pks) throws ApplicationException;
}