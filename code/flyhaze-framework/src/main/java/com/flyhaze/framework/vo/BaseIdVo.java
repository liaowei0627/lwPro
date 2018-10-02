/**
 * flyhaze-framework
 * BaseIdVo.java
 */
package com.flyhaze.framework.vo;

import com.flyhaze.core.vo.IBasisIdVo;
import com.flyhaze.framework.hibernate.entity.BaseIdEntity;

import lombok.AccessLevel;
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
 * @see com.flyhaze.core.vo.IBasisIdVo<E, V>
 * @since jdk1.8
 */
@SuppressWarnings("serial")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@ToString
public abstract class BaseIdVo<E extends BaseIdEntity<E>, V extends BaseIdVo<E, V>> implements IBasisIdVo<E, V> {

    // 消息
    protected String msg;

    // 主键
    protected String id;

    public BaseIdVo(String id) {
        this.id = id;
    }
}