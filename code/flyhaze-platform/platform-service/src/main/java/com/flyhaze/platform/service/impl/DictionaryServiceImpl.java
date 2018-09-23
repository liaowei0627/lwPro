/**
 * platform-service
 * DictionaryServiceImpl.java
 */
package com.flyhaze.platform.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.flyhaze.framework.service.impl.ServiceImpl;
import com.flyhaze.platform.dao.IDictionaryDao;
import com.flyhaze.platform.entity.SysDictionary;
import com.flyhaze.platform.service.IDictionaryService;
import com.flyhaze.platform.vo.DictionaryVo;

/**
 * DictionaryServiceImpl
 *
 * 数据字典管理服务实现
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-09-16 09:50:09
 * @see com.flyhaze.platform.service.IDictionaryService
 * @see com.flyhaze.framework.service.impl.ServiceImpl<SysDictionary, DictionaryVo>
 * @since jdk1.8
 */
@Service("dictionaryService")
public class DictionaryServiceImpl extends ServiceImpl<SysDictionary, DictionaryVo> implements IDictionaryService {

    @Resource(name = "dictionaryDao")
    private IDictionaryDao dictionaryDao;

    @Override
    protected IDictionaryDao getDao() {
        return dictionaryDao;
    }

    @Override
    protected DictionaryVo entityToVo(SysDictionary e) {

        DictionaryVo v = new DictionaryVo();
        v.copyForEntity(e);

        return v;
    }
}