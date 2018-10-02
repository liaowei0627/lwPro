/**
 * platform-service
 * AuthorityServiceImpl.java
 */
package com.flyhaze.platform.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.flyhaze.core.constants.I18nKeyConstants;
import com.flyhaze.core.exception.ApplicationException;
import com.flyhaze.core.query.Where;
import com.flyhaze.core.query.operator.CollectionValueComparisonOperator;
import com.flyhaze.core.query.operator.OneValueComparisonOperator;
import com.flyhaze.framework.hibernate.dao.IDao;
import com.flyhaze.framework.service.impl.ServiceImpl;
import com.flyhaze.platform.dao.IAuthorityDao;
import com.flyhaze.platform.entity.SysAuthority;
import com.flyhaze.platform.service.IAuthorityService;
import com.flyhaze.platform.vo.AuthorityVo;
import com.google.common.base.Strings;

/**
 * AuthorityServiceImpl
 *
 * 权限管理服务实现
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-09-24 02:35:45
 * @see com.flyhaze.platform.service.IAuthorityService
 * @see com.flyhaze.framework.service.impl.ServiceImpl<SysAuthority, AuthorityVo>
 * @since jdk1.8
 */
@Service("authorityService")
public class AuthorityServiceImpl extends ServiceImpl<SysAuthority, AuthorityVo> implements IAuthorityService {

    @Resource(name = "authorityDao")
    private IAuthorityDao authorityDao;

    @Override
    protected IDao<SysAuthority> getDao() {
        return authorityDao;
    }

    @Override
    protected AuthorityVo entityToVo(SysAuthority e) {
        AuthorityVo v = new AuthorityVo();
        v.copyForEntity(e);
        return v;
    }

    @Override
    protected boolean validSave(AuthorityVo vo) throws ApplicationException {
        boolean rs = true;
        Where where = Where.rootWhere("authCode", OneValueComparisonOperator.EQ, vo.getAuthCode());
        if (!Strings.isNullOrEmpty(vo.getId())) {
            where.andWhere("id", OneValueComparisonOperator.UE, vo.getId());
        }
        Long count = authorityDao.findCount(where);
        if (0 < count.intValue()) {
            msg = I18nKeyConstants.KEY_SAVE_CODE;
            rs = false;
        }
        return rs;
    }

    @Override
    protected boolean validDel(String[] ids) throws ApplicationException {
        boolean rs = true;
        Where where = Where.rootWhere("id", CollectionValueComparisonOperator.IN, ids);
        List<SysAuthority> list = authorityDao.findList(where, null);
        if (null != list && !list.isEmpty()) {
            for (SysAuthority auth : list) {
                if (auth.getBuiltIn().booleanValue()) {
                    msg = I18nKeyConstants.KEY_DEL_BUILTIN;
                    rs = false;
                }
            }
        } else {
            msg = I18nKeyConstants.KEY_DEL_DATA;
            rs = false;
        }
        return rs;
    }
}