package com.liaowei.framework.service;

import java.io.Serializable;

import com.liaowei.framework.core.service.IBaseService;
import com.liaowei.framework.entity.SpringBaseEntity;
import com.liaowei.framework.vo.SpringBaseVo;

public interface ISpringService<V extends SpringBaseVo<E>, E extends SpringBaseEntity, PK extends Serializable> extends IBaseService<V, E, PK> {
}