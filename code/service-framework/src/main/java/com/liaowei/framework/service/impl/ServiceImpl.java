package com.liaowei.framework.service.impl;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.liaowei.framework.dao.IDao;
import com.liaowei.framework.entity.IdEntity;
import com.liaowei.framework.exception.ServiceException;
import com.liaowei.framework.page.Pagination;
import com.liaowei.framework.service.IService;

public abstract class ServiceImpl<T extends IdEntity, PK extends Serializable> implements IService<T, PK> {

    protected final Logger LOGGER = LoggerFactory.getLogger(getClassName());

    protected abstract String getClassName();

    protected abstract IDao<T, PK> getDao();

    @Override
    public T findEntity(PK pk) throws ServiceException {
        return getDao().findEntity(pk);
    }

    @Override
    public T addEntity(T entity) throws ServiceException {
        return getDao().addEntity(entity);
    }

    @Override
    public T updateEntity(T entity) throws ServiceException {
        return getDao().updateEntity(entity);
    }

    @Override
    public List<T> findList(T entity) throws ServiceException {
        return getDao().findList(entity);
    }

    @Override
    public Pagination<T> findPage(Pagination<T> page, T entity) throws ServiceException {
        page = getDao().findPage(page, entity);
        return new Pagination<T>(page.getTotal(), page.getPageSize(), page.getPageNumber(), page.getStartPosition(), page.getData());
    }

    @Override
    public void delEntity(PK pk) throws ServiceException {
        getDao().delEntity(pk);
    }

    @Override
    public void delList(List<PK> pks) throws ServiceException {
        getDao().delList(pks);
    }
}