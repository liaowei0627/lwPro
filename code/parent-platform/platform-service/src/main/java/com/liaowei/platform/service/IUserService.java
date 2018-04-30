/**
 * platform-service
 * IUserService.java
 */
package com.liaowei.platform.service;

import com.liaowei.framework.service.IService;
import com.liaowei.platform.entity.SysUser;
import com.liaowei.platform.vo.UserVo;

/**
 * IUserService
 *
 * 用户管理服务接口
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-30 18:45:00
 * @see com.liaowei.framework.service.IService<UserVo, SysUser, String>
 * @since jdk1.8
 */
public interface IUserService extends IService<UserVo, SysUser, String> {
}