/**
 * platform-service
 * SiteServiceImpl.java
 */
package com.flyhaze.platform.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.flyhaze.core.constants.I18nKeyConstants;
import com.flyhaze.core.exception.ApplicationException;
import com.flyhaze.core.query.Where;
import com.flyhaze.core.query.operator.OneValueComparisonOperator;
import com.flyhaze.framework.hibernate.dao.IDao;
import com.flyhaze.framework.service.impl.ServiceImpl;
import com.flyhaze.platform.dao.ISiteDao;
import com.flyhaze.platform.entity.SysSite;
import com.flyhaze.platform.service.ISiteService;
import com.flyhaze.platform.vo.SiteVo;
import com.google.common.base.Strings;

/**
 * SiteServiceImpl
 *
 * 站点管理服务实现
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-09-14 23:26:08
 * @see com.flyhaze.platform.service.ISiteService
 * @see com.flyhaze.framework.service.impl.ServiceImpl<SysSite, SiteVo>
 * @since jdk1.8
 */
@Service("siteService")
public class SiteServiceImpl extends ServiceImpl<SysSite, SiteVo> implements ISiteService {

    @Resource(name = "siteDao")
    private ISiteDao siteDao;

    @Override
    protected IDao<SysSite> getDao() {
        return siteDao;
    }

    @Override
    protected SiteVo entityToVo(SysSite e) {
        SiteVo v = new SiteVo();
        if (null != e) {
            v.copyForEntity(e);
        }
        return v;
    }

    @Override
    protected boolean validSave(SiteVo vo) throws ApplicationException {
        boolean rs = true;
        Where where = Where.rootWhere("siteCode", OneValueComparisonOperator.EQ, vo.getSiteCode());
        if (!Strings.isNullOrEmpty(vo.getId())) {
            where.andWhere("id", OneValueComparisonOperator.UE, vo.getId());
        }
        Long count = siteDao.findCount(where);
        if (0 < count.intValue()) {
            msg = I18nKeyConstants.KEY_SAVE_CODE;
            rs = false;
        }
        return rs;
    }

    @Override
    protected boolean validDel(String[] ids) throws ApplicationException {
        return true;
    }
}