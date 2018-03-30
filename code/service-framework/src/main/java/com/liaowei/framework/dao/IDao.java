package com.liaowei.framework.dao;

import java.io.Serializable;
import java.util.List;

import com.liaowei.framework.entity.IdEntity;
import com.liaowei.framework.exception.ServiceException;
import com.liaowei.framework.page.Pagination;

public interface IDao<T extends IdEntity, PK extends Serializable> {

    T findEntity(PK pk) throws ServiceException;

    T addEntity(T entity) throws ServiceException;

    T updateEntity(T entity) throws ServiceException;

    List<T> findList(T entity) throws ServiceException;

    Pagination<T> findPage(Pagination<T> page, T entity) throws ServiceException;

    void delEntity(PK pk) throws ServiceException;

    void delList(List<PK> pks) throws ServiceException;
}
