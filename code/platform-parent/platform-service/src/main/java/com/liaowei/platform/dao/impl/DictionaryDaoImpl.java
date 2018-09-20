/**
 * platform-service
 * SysDictionaryDaoImpl.java
 */
package com.liaowei.platform.dao.impl;

import org.springframework.stereotype.Repository;

import com.liaowei.framework.dao.impl.DaoImpl;
import com.liaowei.platform.dao.IDictionaryDao;
import com.liaowei.platform.entity.SysDictionary;

/**
 * SysDictionaryDaoImpl
 *
 * 系统字典DAO实现
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-19 21:08:09
 * @see com.liaowei.platform.dao.IDictionaryDao
 * @see com.liaowei.framework.dao.impl.DaoImpl<SysDictionary>
 * @since jdk1.8
 */
@Repository("dictionaryDao")
public class DictionaryDaoImpl extends DaoImpl<SysDictionary> implements IDictionaryDao {

    @Override
    protected Class<SysDictionary> getEntityClass() {
        return SysDictionary.class;
    }
}