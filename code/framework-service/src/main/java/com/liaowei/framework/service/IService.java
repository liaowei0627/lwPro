/**
 * service-framework
 * IService.java
 */
package com.liaowei.framework.service;

import com.liaowei.framework.core.service.IBasisService;
import com.liaowei.framework.entity.BaseIdEntity;
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
public interface IService<E extends BaseIdEntity<E>, V extends BaseIdVo<E, V>> extends IBasisService<E, V, String> {
}