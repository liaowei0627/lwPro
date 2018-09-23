/**
 * platform-service
 * SiteServiceImpl.java
 */
package com.flyhaze.platform.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.flyhaze.framework.core.exception.ApplicationException;
import com.flyhaze.framework.core.page.Pagination;
import com.flyhaze.framework.core.query.Where;
import com.flyhaze.framework.core.query.exception.DuplicationCodeException;
import com.flyhaze.framework.core.query.operator.OneValueComparisonOperator;
import com.flyhaze.framework.hibernate.dao.IDao;
import com.flyhaze.framework.service.impl.ServiceImpl;
import com.flyhaze.platform.dao.ISiteDao;
import com.flyhaze.platform.entity.SysSite;
import com.flyhaze.platform.service.ISiteService;
import com.flyhaze.platform.vo.SiteVo;

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
        v.copyForEntity(e);

        return v;
    }

    @Override
    public SiteVo addVo(SiteVo v) throws ApplicationException {

        Where where = Where.rootWhere("siteCode", OneValueComparisonOperator.EQ, v.getSiteCode());
        Pagination<SysSite> p = siteDao.findList(null, where);
        if (0 < p.getTotal()) {
            throw new DuplicationCodeException(DuplicationCodeException.DUPLICATION_CODE);
        }

        return super.addVo(v);
    }

    @Override
    public SiteVo updateVo(SiteVo v) throws ApplicationException {

        Where where = Where.rootWhere("siteCode", OneValueComparisonOperator.EQ, v.getSiteCode());
        where.andWhere("id", OneValueComparisonOperator.UE, v.getId());
        Pagination<SysSite> p = siteDao.findList(null, where);
        if (0 < p.getTotal()) {
            throw new DuplicationCodeException(DuplicationCodeException.DUPLICATION_CODE);
        }

        return super.updateVo(v);
    }

    @Override
    public void delList(String[] id) throws ApplicationException {

        if (siteDao.canDel(id)) {
            super.delList(id);
        }
    }
}