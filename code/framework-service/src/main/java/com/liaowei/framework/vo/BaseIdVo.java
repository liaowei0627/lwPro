/**
 * service-framework
 * BaseIdVo.java
 */
package com.liaowei.framework.vo;

import com.liaowei.framework.core.vo.IBasisIdVo;
import com.liaowei.framework.entity.BaseIdEntity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * BaseIdVo
 *
 * Spring服务层向控制层传递主键数据的封装类基类
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-21 00:57:58
 * @see com.liaowei.framework.core.vo.IBasisIdVo
 * @since jdk1.8
 */
@SuppressWarnings("serial")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@ToString
public abstract class BaseIdVo<E extends BaseIdEntity<E>, V extends BaseIdVo<E, V>> implements IBasisIdVo<E, V> {

    // 主键
    protected String id;
}