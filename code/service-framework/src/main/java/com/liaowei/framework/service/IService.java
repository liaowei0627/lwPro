/**
 * service-framework
 * IService.java
 */
package com.liaowei.framework.service;

import java.io.Serializable;

import com.liaowei.framework.core.entity.IBasisIdEntity;
import com.liaowei.framework.core.exception.ApplicationException;
import com.liaowei.framework.core.service.IBasisService;
import com.liaowei.framework.page.Pagination;
import com.liaowei.framework.query.Where;
import com.liaowei.framework.vo.BaseIdVo;

/**
 * IService
 *
 * Spring服务层基类接口
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-08 21:40:53
 * @see com.liaowei.framework.core.service.IBasisService<V, E, PK>
 * @since jdk1.8
 */
public interface IService<V extends BaseIdVo, E extends IBasisIdEntity, PK extends Serializable> extends IBasisService<V, E, PK> {

    /**
     * 查询数据分页列表
     * 
     * @param pagination 分页对象
     * @param where hql查询条件对象
     * @return
     * @throws ApplicationException
     */
    Pagination<V> findList(Pagination<V> pagination, Where where) throws ApplicationException;
}