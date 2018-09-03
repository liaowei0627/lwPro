package com.liaowei.study.service;

import com.liaowei.framework.service.IService;
import com.liaowei.study.entity.SysUser;
import com.liaowei.study.vo.UserVo;

/**
 * IUserService
 *
 * 用户管理相关服务接口
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-22 20:12:28
 * @see com.liaowei.framework.service.IService<UserVo, SysUser, String>
 * @since jdk1.8
 */
public interface IUserService extends IService<SysUser, UserVo> {
}