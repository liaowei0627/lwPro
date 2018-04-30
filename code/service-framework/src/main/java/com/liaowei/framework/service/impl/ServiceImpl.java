/**
 * service-framework
 * ServiceImpl.java
 */
package com.liaowei.framework.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.liaowei.framework.core.enums.OrderEnum;
import com.liaowei.framework.core.exception.ApplicationException;
import com.liaowei.framework.core.service.impl.BasisServiceImpl;
import com.liaowei.framework.entity.BaseEntity;
import com.liaowei.framework.page.Pagination;
import com.liaowei.framework.service.IService;
import com.liaowei.framework.vo.BaseVo;

import lombok.extern.slf4j.Slf4j;

/**
 * ServiceImpl
 *
 * Spring服务层基类
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-08 21:40:39
 * @see com.liaowei.framework.service.IService<V, E>
 * @see com.liaowei.framework.core.service.impl.BasisServiceImpl<V, E>
 * @since jdk1.8
 */
@Slf4j
public abstract class ServiceImpl<V extends BaseVo, E extends BaseEntity, PK extends Serializable> extends BasisServiceImpl<V, E, PK> implements IService<V, E, PK> {

    @Override
    public V findVo(PK id) throws ApplicationException {
        log.debug("根据主键值查询数据对象, 主键：" + id);

        E entity = getDao().findEntity(id);
        V vo = null;
        if (null != entity) {
            vo = entityToVo(entity);
        }

        return vo;
    }

    @Override
    public V addVo(V v) throws ApplicationException {
        log.debug("新增数据，数据：" + v.toString());
        
        E e = getDao().addEntity(voToEntity(v));

        return entityToVo(e);
    }

    @Override
    public V updateVo(V v) throws ApplicationException {
        log.debug("修改数据，数据：" + v.toString());
        E e = getDao().updateEntity(voToEntity(v));
        return entityToVo(e);
    }

    @Override
    public List<V> findList(V v, Map<String, OrderEnum> orderBy) throws ApplicationException {
        log.debug("查询数据列表，查询条件：" + v.toString());
        List<E> l = getDao().findList(voToEntity(v), orderBy);
        List<V> vl = Lists.newArrayList();
        for (E e : l) {
            vl.add(entityToVo(e));
        }
        return vl;
    }

    @Override
    public Pagination<V> findPage(Pagination<V> page, V v, Map<String, OrderEnum> orderBy) throws ApplicationException {
        log.debug("查询数据分页列表，查询条件：" + v.toString() + ",分页信息：" + page.toString());
        List<E> el = Lists.newArrayList();
        Pagination<E> p = new Pagination<>(page.getRows(), page.getPage());
        p = getDao().findPage(p, voToEntity(v), orderBy);
        el = p.getData();
        List<V> vl = Lists.newArrayList();
        for (E e : el) {
            vl.add(entityToVo(e));
        }
        return new Pagination<V>(page.getTotal(), page.getRows(), page.getPage(), vl);
    }

    @Override
    public void delOne(PK id) throws ApplicationException {
        log.debug("根据主键值删除一条数据对象, 主键：" + id);
        getDao().delEntity(id);
    }

    @Override
    public void delList(List<PK> ids) throws ApplicationException {
        log.debug("根据主键值批量删除数据对象, 主键：" + Joiner.on(",").join(ids));
        getDao().delList(ids);
    }
}