/**
 * platform-service
 * IRoleService.java
 */
package com.flyhaze.platform.service;

import com.flyhaze.framework.service.IService;
import com.flyhaze.platform.entity.SysRole;
import com.flyhaze.platform.vo.RoleVo;

/**
 * IRoleService
 *
 * 角色管理服务层接口
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-09-26 05:13:29
 * @see com.flyhaze.framework.service.IService<SysRole, RoleVo>
 * @since jdk1.8
 */
public interface IRoleService extends IService<SysRole, RoleVo> {
}