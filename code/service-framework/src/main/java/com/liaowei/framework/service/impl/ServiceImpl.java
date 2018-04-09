/**
 * service-framework
 * ServiceImpl.java
 */
package com.liaowei.framework.service.impl;

import java.io.Serializable;
import java.util.List;

import com.google.common.collect.Lists;
import com.liaowei.framework.core.exception.ApplicationException;
import com.liaowei.framework.core.service.impl.BasisServiceImpl;
import com.liaowei.framework.entity.BaseEntity;
import com.liaowei.framework.page.Pagination;
import com.liaowei.framework.service.IService;
import com.liaowei.framework.vo.BaseVo;

/**
 * ServiceImpl
 *
 * Spring服务层基类
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-08 21:40:39
 * @see com.liaowei.framework.service.IService<V, E, PK>
 * @see com.liaowei.framework.core.service.impl.BasisServiceImpl<V, E, PK>
 * @since jdk1.8
 */
public abstract class ServiceImpl<V extends BaseVo<E>, E extends BaseEntity, PK extends Serializable> extends BasisServiceImpl<V, E, PK> implements IService<V, E, PK> {

    @Override
    public V findVo(PK pk) throws ApplicationException {
        E entity = getDao().findEntity(pk);
        return voCopy(entity);
    }

    @Override
    public V addVo(V v) throws ApplicationException {
        E e = getDao().addEntity(v.toEntity());
        return voCopy(e);
    }

    @Override
    public V updateVo(V v) throws ApplicationException {
        E e = getDao().updateEntity(v.toEntity());
        return voCopy(e);
    }

    @Override
    public List<V> findList(V v) throws ApplicationException {
        List<E> l = getDao().findList(v.toEntity());
        List<V> vl = Lists.newArrayList();
        for (E e : l) {
            vl.add(voCopy(e));
        }
        return vl;
    }

    @Override
    public Pagination<V> findPage(Pagination<V> page, V v) throws ApplicationException {
        List<V> vl = page.getData();
        List<E> el = Lists.newArrayList();
        for (V vo : vl) {
            el.add(vo.toEntity());
        }
        Pagination<E> p = new Pagination<>(page.getTotal(), page.getPageSize(), page.getPageNumber(), page.getStartPosition(), el);
        p = getDao().findPage(p, v.toEntity());
        el = p.getData();
        vl = Lists.newArrayList();
        for (E e : el) {
            vl.add(voCopy(e));
        }
        return new Pagination<V>(page.getTotal(), page.getPageSize(), page.getPageNumber(), page.getStartPosition(), vl);
    }

    @Override
    public void delOne(PK pk) throws ApplicationException {
        getDao().delEntity(pk);
    }

    @Override
    public void delList(List<PK> pks) throws ApplicationException {
        getDao().delList(pks);
    }
}