/**
 * platform-service
 * SiteServiceImpl.java
 */
package com.liaowei.platform.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.google.common.base.Joiner;
import com.liaowei.framework.core.exception.ApplicationException;
import com.liaowei.framework.dao.IDao;
import com.liaowei.framework.page.Pagination;
import com.liaowei.framework.query.Where;
import com.liaowei.framework.query.exception.DuplicationCodeException;
import com.liaowei.framework.query.operator.OneValueComparisonOperator;
import com.liaowei.framework.service.impl.ServiceImpl;
import com.liaowei.platform.dao.ISiteDao;
import com.liaowei.platform.entity.SysSite;
import com.liaowei.platform.service.ISiteService;
import com.liaowei.platform.vo.SiteVo;

import lombok.extern.slf4j.Slf4j;

/**
 * SiteServiceImpl
 *
 * 站点管理服务实现
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-09-14 23:26:08
 * @see com.liaowei.platform.service.ISiteService
 * @see com.liaowei.framework.service.impl.ServiceImpl<SysSite, SiteVo>
 * @since jdk1.8
 */
@Service("siteService")
@Slf4j
public class SiteServiceImpl extends ServiceImpl<SysSite, SiteVo> implements ISiteService {

    @Resource(name = "siteDao")
    private ISiteDao siteDao;

    @Override
    protected IDao<SysSite> getDao() {
        return siteDao;
    }

    @Override
    protected SiteVo entityToVo(SysSite e) {
        log.debug("DEBUG：Entity转换Vo：" + e);

        SiteVo v = new SiteVo();
        v.copyForEntity(e);

        return v;
    }

    @Override
    public SiteVo addVo(SiteVo v) throws ApplicationException {
        log.debug("DEBUG：新增站点：" + v);

        Where where = Where.rootWhere("siteCode", OneValueComparisonOperator.EQ, v.getSiteCode());
        Pagination<SysSite> p = siteDao.findList(null, where);
        if (0 < p.getTotal()) {
            throw new DuplicationCodeException(DuplicationCodeException.DUPLICATION_CODE);
        }

        return super.addVo(v);
    }

    @Override
    public SiteVo updateVo(SiteVo v) throws ApplicationException {
        log.debug("DEBUG：修改站点：" + v);

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
        log.debug("DEBUG：根据主键值批量删除数据对象, 主键：" + Joiner.on(",").join(id));

        if (siteDao.canDel(id)) {
            super.delList(id);
        }
    }
}