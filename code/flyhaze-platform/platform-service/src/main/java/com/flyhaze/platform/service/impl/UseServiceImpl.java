/**
 * platform-service
 * UseServiceImpl.java
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
import com.flyhaze.platform.constants.SysI18nKeyConstants;
import com.flyhaze.platform.dao.IUserDao;
import com.flyhaze.platform.entity.SysUser;
import com.flyhaze.platform.service.IUserService;
import com.flyhaze.platform.vo.UserVo;
import com.google.common.base.Strings;

/**
 * UseServiceImpl
 *
 * 用户管理服务实现
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-30 18:49:03
 * @see com.flyhaze.platform.service.IUserService
 * @see com.flyhaze.framework.service.impl.ServiceImpl<SysUser, UserVo>
 * @since jdk1.8
 */
@Service("userService")
public class UseServiceImpl extends ServiceImpl<SysUser, UserVo> implements IUserService {

    @Resource(name = "userDao")
    private IUserDao userDao;

    @Override
    protected IDao<SysUser> getDao() {
        return userDao;
    }

    @Override
    protected UserVo entityToVo(SysUser e) {
        UserVo v = new UserVo();
        v.copyForEntity(e);
        return v;
    }

    @Override
    public UserVo findUserByUserName(String userName) {
        SysUser sysUser = userDao.findByUserName(userName);
        UserVo userVo = null;
        if (null != sysUser) {
            userVo = new UserVo();
            userVo.copyForEntity(sysUser);
        }
        return userVo;
    }

    @Override
    protected boolean validSave(UserVo vo) throws ApplicationException {
        boolean rs = true;
        Where where = Where.rootWhere("userName", OneValueComparisonOperator.EQ, vo.getUserName());
        if (!Strings.isNullOrEmpty(vo.getId())) {
            where.andWhere("id", OneValueComparisonOperator.UE, vo.getId());
        }
        Long count = userDao.findCount(where);
        if (0 < count.intValue()) {
            msg = SysI18nKeyConstants.KEY_SAVE_USERNAME;
            rs = false;
        }
        return rs;
    }

    @Override
    protected boolean validDel(String[] ids) throws ApplicationException {
        boolean rs = true;
        Where where = Where.rootWhere("id", CollectionValueComparisonOperator.IN, ids);
        List<SysUser> list = userDao.findList(where, null);
        if (null != list && !list.isEmpty()) {
            for (SysUser user : list) {
                if (user.getBuiltIn().booleanValue()) {
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