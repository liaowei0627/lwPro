/**
 * flyhaze-framework
 * IService.java
 */
package com.flyhaze.framework.service;

import com.flyhaze.framework.core.service.IBasisService;
import com.flyhaze.framework.hibernate.entity.BaseIdEntity;
import com.flyhaze.framework.vo.BaseIdVo;

/**
 * IService
 *
 * Spring服务层基类接口
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-08 21:40:53
 * @see com.flyhaze.framework.core.service.IBasisService<V, E, String>
 * @since jdk1.8
 */
public interface IService<E extends BaseIdEntity<E>, V extends BaseIdVo<E, V>> extends IBasisService<E, V, String> {
}