/**
 * platform-service
 * DictionaryServiceImpl.java
 */
package com.liaowei.platform.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.liaowei.framework.service.impl.ServiceImpl;
import com.liaowei.platform.dao.IDictionaryDao;
import com.liaowei.platform.entity.SysDictionary;
import com.liaowei.platform.service.IDictionaryService;
import com.liaowei.platform.vo.DictionaryVo;

import lombok.extern.slf4j.Slf4j;

/**
 * DictionaryServiceImpl
 *
 * 数据字典管理服务实现
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-09-16 09:50:09
 * @see com.liaowei.platform.service.IDictionaryService
 * @see com.liaowei.framework.service.impl.ServiceImpl<SysDictionary, DictionaryVo>
 * @since jdk1.8
 */
@Service("dictionaryService")
@Slf4j
public class DictionaryServiceImpl extends ServiceImpl<SysDictionary, DictionaryVo> implements IDictionaryService {

    @Resource(name = "dictionaryDao")
    private IDictionaryDao dictionaryDao;

    @Override
    protected IDictionaryDao getDao() {
        return dictionaryDao;
    }

    @Override
    protected DictionaryVo entityToVo(SysDictionary e) {
        log.debug("DEBUG：Entity转换Vo：" + e);

        DictionaryVo v = new DictionaryVo();
        v.copyForEntity(e);

        return v;
    }
}