/**
 * framework-hibernate
 * BaseIdEntity.java
 */
package com.flyhaze.framework.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;

import com.flyhaze.framework.core.entity.IBasisIdEntity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * BaseIdEntity
 * 
 * hibernate实体类的主键超类
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-08 21:33:14
 * @see com.flyhaze.framework.core.entity.IBasisIdEntity<E>
 * @since jdk1.8
 */
@SuppressWarnings("serial")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@ToString
@MappedSuperclass
public abstract class BaseIdEntity<E extends BaseIdEntity<E>> implements IBasisIdEntity<E> {

    /**
     * 主键
     */
    @Id
    @GenericGenerator(name = "uuid", strategy = "com.flyhaze.framework.generator.IdGenerator")
    @GeneratedValue(generator = "uuid")
    protected String id;
}