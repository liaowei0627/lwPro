/**
 * platform-service
 * IAuthorityService.java
 */
package com.flyhaze.platform.service;

import com.flyhaze.framework.service.IService;
import com.flyhaze.platform.entity.SysAuthority;
import com.flyhaze.platform.vo.AuthorityVo;

/**
 * IAuthorityService
 *
 * 权限管理服务接口
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-09-24 02:34:10
 * @see com.flyhaze.framework.service.IService<SysAuthority, AuthorityVo>
 * @since jdk1.8
 */
public interface IAuthorityService extends IService<SysAuthority, AuthorityVo> {
}