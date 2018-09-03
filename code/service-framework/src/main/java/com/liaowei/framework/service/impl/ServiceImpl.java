/**
 * service-framework
 * ServiceImpl.java
 */
package com.liaowei.framework.service.impl;

import java.util.List;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.liaowei.framework.core.exception.ApplicationException;
import com.liaowei.framework.core.service.impl.BasisServiceImpl;
import com.liaowei.framework.dao.IDao;
import com.liaowei.framework.entity.BaseIdEntity;
import com.liaowei.framework.page.Pagination;
import com.liaowei.framework.query.Where;
import com.liaowei.framework.service.IService;
import com.liaowei.framework.vo.BaseIdVo;

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
public abstract class ServiceImpl<E extends BaseIdEntity<E>, V extends BaseIdVo<E, V>> extends BasisServiceImpl<E, V, String> implements IService<E, V> {

    /**
     * 从子类注入Dao
     */
    protected abstract IDao<E> getDao();

    @Override
    public V findVo(String id) throws ApplicationException {
        log.debug("DEBUG：根据主键值查询数据对象, 主键：" + id);

        E entity = getDao().findEntity(id);
        V vo = null;
        if (null != entity) {
            vo = entityToVo(entity);
        }

        return vo;
    }

    @Override
    public V addVo(V v) throws ApplicationException {
        log.debug("DEBUG：新增数据，数据：" + v.toString());
        
        E e = getDao().addEntity(v.copyToEntity());

        return entityToVo(e);
    }

    @Override
    public V updateVo(V v) throws ApplicationException {
        log.debug("DEBUG：修改数据，数据：" + v.toString());
        E entity = getDao().findEntity(v.getId());
        entity.setEntity(v.copyToEntity());
        E e = getDao().updateEntity(entity);
        return entityToVo(e);
    }

    @Override
    public Pagination<V> findList(Pagination<V> pagination, Where where) throws ApplicationException {
        log.debug("DEBUG：查询数据分页列表，查询条件：" + (null == where ? "无条件" : where.toString()) + ",分页信息：" + (null == pagination ? "无分页" : pagination.toString()));
        List<E> el = Lists.newArrayList();
        Pagination<E> p;
        if (null == pagination) {
            p = new Pagination<E>();
        } else {
            p = new Pagination<E>(pagination.getRows(), pagination.getPage(), pagination.getOrderBy());
        }
        p = getDao().findList(p, where);
        el = p.getData();
        List<V> vl = Lists.newArrayList();
        for (E e : el) {
            vl.add(entityToVo(e));
        }
        return new Pagination<V>(p.getTotal(), p.getRows(), p.getPage(), vl);
    }

    @Override
    public void delOne(String id) throws ApplicationException {
        log.debug("DEBUG：根据主键值删除一条数据对象, 主键：" + id);
        getDao().delEntity(id);
    }

    @Override
    public void delList(List<String> ids) throws ApplicationException {
        log.debug("DEBUG：根据主键值批量删除数据对象, 主键：" + Joiner.on(",").join(ids));
        getDao().delList(ids);
    }
}