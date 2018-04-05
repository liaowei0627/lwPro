/**
 * framework-core
 * IdGenerator.java
 */
package com.liaowei.framework.generator;

import java.io.Serializable;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentityGenerator;
import org.hibernate.id.UUIDGenerator;

/**
 * IdGenerator
 *
 * hibernate id生成器自定义实现
 * 修改了uuid生成方式，去除“-”并将其中的字母转换成大写
 *
 * @author liaowei
 * @date 创建时间：2018年4月1日 上午9:53:15 
 * @see org.hibernate.id.IdentityGenerator
 * @since jdk1.8
 */
public class IdGenerator extends IdentityGenerator {

	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object entity) {
		Serializable serializable = UUIDGenerator.buildSessionFactoryUniqueIdentifierGenerator().generate(session, entity);
		String id = serializable.toString();
		id = id.replace("-", "").toUpperCase();
		return id;
	}
}