/**
 * platform-service
 * RoleServiceImpl.java
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
import com.flyhaze.platform.dao.IRoleDao;
import com.flyhaze.platform.entity.SysRole;
import com.flyhaze.platform.service.IRoleService;
import com.flyhaze.platform.vo.RoleVo;
import com.google.common.base.Strings;

/**
 * RoleServiceImpl
 *
 * 角色管理服务层接口实现
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-09-26 05:14:44
 * @see com.flyhaze.platform.service.IRoleService
 * @see com.flyhaze.framework.service.impl.ServiceImpl<SysRole, RoleVo>
 * @since jdk1.8
 */
@Service("roleService")
public class RoleServiceImpl extends ServiceImpl<SysRole, RoleVo> implements IRoleService {

    @Resource(name = "roleDao")
    private IRoleDao roleDao;

    @Override
    protected IDao<SysRole> getDao() {
        return roleDao;
    }

    @Override
    protected RoleVo entityToVo(SysRole e) {
        RoleVo v = new RoleVo();
        v.copyForEntity(e);
        return v;
    }

    @Override
    protected boolean validSave(RoleVo vo) throws ApplicationException {
        boolean rs = true;
        Where where = Where.rootWhere("roleCode", OneValueComparisonOperator.EQ, vo.getRoleCode());
        if (!Strings.isNullOrEmpty(vo.getId())) {
            where.andWhere("id", OneValueComparisonOperator.UE, vo.getId());
        }
        Long count = roleDao.findCount(where);
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
        List<SysRole> list = roleDao.findList(where, null);
        if (null != list && !list.isEmpty()) {
            for (SysRole role : list) {
                if (role.getBuiltIn().booleanValue()) {
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