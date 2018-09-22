/**
 * platform-service
 * IUserService.java
 */
package com.flyhaze.platform.service;

import com.flyhaze.framework.service.IService;
import com.flyhaze.platform.entity.SysUser;
import com.flyhaze.platform.vo.UserVo;

/**
 * IUserService
 *
 * 用户管理服务接口
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-30 18:45:00
 * @see com.flyhaze.framework.service.IService<SysUser, UserVo>
 * @since jdk1.8
 */
public interface IUserService extends IService<SysUser, UserVo> {
}