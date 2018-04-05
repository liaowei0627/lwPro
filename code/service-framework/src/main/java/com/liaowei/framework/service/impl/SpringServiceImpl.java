/**
 * service-framework
 * ServiceImpl.java
 */
package com.liaowei.framework.service.impl;

import java.io.Serializable;
import java.util.List;

import com.google.common.collect.Lists;
import com.liaowei.framework.core.exception.ApplicationException;
import com.liaowei.framework.core.service.impl.BaseServiceImpl;
import com.liaowei.framework.entity.SpringBaseEntity;
import com.liaowei.framework.page.Pagination;
import com.liaowei.framework.service.ISpringService;
import com.liaowei.framework.vo.SpringBaseVo;

/**
 * ServiceImpl
 *
 * Service层超类实现
 *
 * @author liaowei
 * @date 创建时间：2018年4月1日 上午9:43:10 
 * @see com.liaowei.framework.service.ISpringService<V extends SpringBaseVo<E>, E extends IdEntity, PK extends Serializable>
 * @since jdk1.8
 */
public abstract class SpringServiceImpl<V extends SpringBaseVo<E>, E extends SpringBaseEntity, PK extends Serializable> extends BaseServiceImpl<V, E, PK> implements ISpringService<V, E, PK> {

    @Override
    public V findEntity(PK pk) throws ApplicationException {
        E entity = getDao().findEntity(pk);
        return voCopy(entity);
    }

    @Override
    public V addEntity(V v) throws ApplicationException {
        E e = getDao().addEntity(v.toEntity());
        return voCopy(e);
    }

    @Override
    public V updateEntity(V v) throws ApplicationException {
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
    public void delEntity(PK pk) throws ApplicationException {
        getDao().delEntity(pk);
    }

    @Override
    public void delList(List<PK> pks) throws ApplicationException {
        getDao().delList(pks);
    }
}