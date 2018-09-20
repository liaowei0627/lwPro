/**
 * framework-hibernate
 * IDao.java
 */
package com.liaowei.framework.dao;

import com.liaowei.framework.core.dao.IBasisDao;
import com.liaowei.framework.core.exception.ApplicationException;
import com.liaowei.framework.entity.BaseIdEntity;

/**
 * IDao
 *
 * Dao层超类接口，基本的增删改查接口
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-08 21:33:14
 * @see com.liaowei.framework.core.dao.IBasisDao<E, PK>
 * @since jdk1.8
 */
public interface IDao<E extends BaseIdEntity<E>> extends IBasisDao<E, String> {

    /**
     * 新增数结构数据时，设置fullCode、fullText列值
     * 
     * @param entity
     * @throws ApplicationException 
     */
    void refreshFullCode(E entity) throws ApplicationException;

    /**
     * 新增树结构数据时，设置orderNum列值
     * 
     * @param entity
     */
    void refreshOrderNum(E entity) throws ApplicationException;

    /**
     * 批量删除节点树
     * 
     * @param fullCodePrefix 全路径编号前缀
     * @throws ApplicationException
     */
    void delChildren(String fullCodePrefix) throws ApplicationException;
}