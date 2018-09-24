/**
 * flyhaze-framework
 * ServiceImpl.java
 */
package com.flyhaze.framework.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.flyhaze.framework.core.constants.I18nKeyConstants;
import com.flyhaze.framework.core.exception.ApplicationException;
import com.flyhaze.framework.core.page.Pagination;
import com.flyhaze.framework.core.query.Where;
import com.flyhaze.framework.core.query.operator.CollectionValueComparisonOperator;
import com.flyhaze.framework.core.query.operator.OneValueComparisonOperator;
import com.flyhaze.framework.core.query.order.OrderBy;
import com.flyhaze.framework.core.service.impl.BasisServiceImpl;
import com.flyhaze.framework.hibernate.dao.IDao;
import com.flyhaze.framework.hibernate.entity.BaseIdEntity;
import com.flyhaze.framework.hibernate.entity.BaseTreeEntity;
import com.flyhaze.framework.service.IService;
import com.flyhaze.framework.vo.BaseIdVo;
import com.flyhaze.framework.vo.BaseTreeVo;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * ServiceImpl
 *
 * Spring服务层基类
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-08 21:40:39
 * @see com.flyhaze.framework.service.IService<V, E>
 * @see com.flyhaze.framework.core.service.impl.BasisServiceImpl<E, V, String>
 * @since jdk1.8
 */
public abstract class ServiceImpl<E extends BaseIdEntity<E>, V extends BaseIdVo<E, V>> extends BasisServiceImpl<E, V, String>
        implements IService<E, V> {

    /**
     * 从子类注入Dao
     */
    protected abstract IDao<E> getDao();

    @Override
    public V findVo(String id) throws ApplicationException {
        E entity = getDao().findEntity(id);
        V vo = null;
        if (null != entity) {
            vo = entityToVo(entity);
        }
        return vo;
    }

    @Override
    public V addVo(V v) throws ApplicationException {
        V vo;
        if (validSave(v)) {
            E entity = v.copyToEntity();
            if (v instanceof BaseTreeVo) {
                boolean hasDupFullCode = refreshFullCode(entity);
                if (!hasDupFullCode) {
                    vo = entityToVo(null);
                    vo.setMsg(msg);
                    return vo;
                }
            }
            E e = getDao().addEntity(entity);
            vo = entityToVo(e);
        } else {
            vo = entityToVo(null);
            vo.setMsg(msg);
        }
        return vo;
    }

    @Override
    public V updateVo(V v) throws ApplicationException {
        V vo;
        if (validSave(v)) {
            E entityPojo = v.copyToEntity();
            E entity = getDao().findEntity(v.getId());
            entity.setEntity(entityPojo);
            if (entity instanceof BaseTreeEntity) {
                boolean hasDupFullCode = refreshTree(entityPojo, entity);
                if (!hasDupFullCode) {
                    vo = entityToVo(null);
                    vo.setMsg(msg);
                    return vo;
                }
            }
            E e = getDao().updateEntity(entity);
            vo = entityToVo(e);
        } else {
            vo = entityToVo(null);
            vo.setMsg(msg);
        }
        return vo;
    }

    @Override
    public List<V> findList(Where where, List<OrderBy> orderBy) throws ApplicationException {

        List<E> el = getDao().findList(where, orderBy);
        List<V> vl = Lists.<V>newArrayList();
        for (E e : el) {
            vl.add(entityToVo(e));
        }

        return vl;
    }

    @Override
    public Pagination<V> findList(Pagination<V> pagination, Where where) throws ApplicationException {
        List<E> el = Lists.<E>newArrayList();
        Pagination<E> p;
        if (null == pagination) {
            p = new Pagination<E>();
            p.setPageable(Boolean.FALSE);
        } else {
            p = new Pagination<E>(pagination.getRows(), pagination.getPage(), pagination.getOrderBy(), pagination.getPageable());
        }
        p = getDao().findList(p, where);
        el = p.getData();
        List<V> vl = Lists.<V>newArrayList();
        for (E e : el) {
            vl.add(entityToVo(e));
        }
        return new Pagination<V>(p.getTotal(), p.getRows(), p.getPage(), vl);
    }

    @Override
    public void delList(String[] ids) throws ApplicationException {

        if (validDel(ids)) {
            Where where = Where.rootWhere("id", CollectionValueComparisonOperator.IN, ids);
            List<E> entityList = getDao().findList(where, null);

            if (!entityList.isEmpty()) {
                @SuppressWarnings("unchecked")
                Class<E> cls = (Class<E>) entityList.get(0).getClass();
                if (BaseTreeEntity.class.isAssignableFrom(cls)) {
                    for (E e : entityList) {
                        @SuppressWarnings("rawtypes")
                        BaseTreeEntity tree = (BaseTreeEntity) e;
                        delChildren(tree.getFullCode(), cls);
                    }
                }

                getDao().delList(ids);
            }
        }
    }

    /**
     * 批量删除节点树
     * 
     * @param fullCodePrefix 全路径编号前缀
     * @param cls
     * @throws ApplicationException
     */
    private void delChildren(String fullCodePrefix_l, Class<E> cls) throws ApplicationException {

        String hql = "delete from " + cls.getSimpleName() + " t where t.fullCode like :fullCodePrefix_l";
        Map<String, Object> param = Maps.<String, Object>newHashMap();
        param.put("fullCodePrefix_l", fullCodePrefix_l + "-%");
        getDao().excByHQL(hql, param);
    }

    /**
     * 修改树结构数据时，刷新fullCode、fullText列值
     * 
     * @param entityPojo
     * @param entity
     * @throws ApplicationException
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    private boolean refreshTree(E entityPojo, E entity) throws ApplicationException {

        BaseTreeEntity treeEntityPojo = (BaseTreeEntity) entityPojo;
        BaseTreeEntity parentPojo = treeEntityPojo.getParent();

        BaseTreeEntity treeEntity = (BaseTreeEntity) entity;
        treeEntity.setParent(parentPojo);
        Set<E> children = treeEntity.getChildren();
        String oldFullCode = treeEntity.getFullCode();
        String oldFullText = treeEntity.getFullText();

        boolean hasDupFullCode = refreshFullCode(entity);
        if (!hasDupFullCode) {
            return false;
        }
        String newFullCode = treeEntity.getFullCode();
        String newFullText = treeEntity.getFullText();

        if (!oldFullCode.equals(newFullCode) && null != children && !children.isEmpty()) {
            refreshChildren(oldFullCode, oldFullText, newFullCode, newFullText, (Class<E>) entity.getClass());
        }
        return true;
    }

    /**
     * 新增数结构数据时，设置fullCode、fullText列值
     * 
     * @param entity
     * @throws ApplicationException
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    private boolean refreshFullCode(E entity) throws ApplicationException {

        BaseTreeEntity tree = (BaseTreeEntity) entity;
        String newCode = tree.getCode();
        String newText = tree.getText();
        BaseTreeEntity parent = tree.getParent();
        String newFullCode = newCode;
        String newFullText = newText;

        BaseTreeEntity newParent = null;
        if (null != parent) {
            E parentEntity = getDao().findEntity(parent.getId());
            if (null != parentEntity) {
                newParent = (BaseTreeEntity) parentEntity;
                String parentFullCode = newParent.getFullCode();
                String parentFullText = newParent.getFullText();
                newFullCode = parentFullCode + "-" + newCode;
                newFullText = parentFullText + "|" + newText;
            }
        }
        tree.setParent(newParent);

        // 检查全路径编号是否重复
        String entityId = entity.getId();
        Where where = Where.rootWhere("fullCode", OneValueComparisonOperator.EQ, newFullCode);
        if (!Strings.isNullOrEmpty(entityId)) {
            where.andWhere("id", OneValueComparisonOperator.EQ, entityId);
        }
        Long cnt = getDao().findCount(where);
        if (cnt.intValue() > 0) {
            msg = I18nKeyConstants.KEY_SAVE_FULLCODE;
            return false;
        }

        tree.setFullCode(newFullCode);
        tree.setFullText(newFullText);

        return true;
    }

    /**
     * 刷新子节点的fullCode和fullText列值
     * 
     * @param oldFullCodePrefix
     * @param oldFullTextPrefix
     * @param newFullCodePrefix
     * @param newFullTextPrefix
     * @throws ApplicationException
     */
    private void refreshChildren(String oldFullCodePrefix, String oldFullTextPrefix, String newFullCodePrefix,
            String newFullTextPrefix, Class<E> cls) throws ApplicationException {

        String hql = "update " + cls.getSimpleName() + " t set "
                + "t.fullCode = concat(:newFullCodePrefix, substring(t.fullCode, (length(:oldFullCodePrefix) + 1), length(t.fullCode))),"
                + "t.fullText = concat(:newFullTextPrefix, substring(t.fullText, (length(:oldFullTextPrefix) + 1), length(t.fullText))) "
                + "where t.fullCode like :oldFullCodePrefix_l";
        Map<String, Object> param = Maps.<String, Object>newHashMap();
        param.put("newFullCodePrefix", newFullCodePrefix);
        param.put("oldFullCodePrefix", oldFullCodePrefix);
        param.put("newFullTextPrefix", newFullTextPrefix);
        param.put("oldFullTextPrefix", oldFullTextPrefix);
        param.put("oldFullCodePrefix_l", oldFullCodePrefix + "-%");
        getDao().excByHQL(hql, param);
    }
}