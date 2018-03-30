package com.liaowei.framework.generator;

import java.io.Serializable;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentityGenerator;
import org.hibernate.id.UUIDGenerator;

public class IdGenerator extends IdentityGenerator {

	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object entity) {
		Serializable serializable = UUIDGenerator.buildSessionFactoryUniqueIdentifierGenerator().generate(session, entity);
		String id = serializable.toString();
		id = id.replace("-", "").toUpperCase();
		return id;
	}
}