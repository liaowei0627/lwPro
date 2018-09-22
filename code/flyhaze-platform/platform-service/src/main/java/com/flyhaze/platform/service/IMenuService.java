/**
 * platform-service
 * IMenuService.java
 */
package com.flyhaze.platform.service;

import com.flyhaze.framework.service.IService;
import com.flyhaze.platform.entity.SysMenu;
import com.flyhaze.platform.vo.MenuVo;

/**
 * IMenuService
 *
 * 菜单管理服务接口
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-21 23:38:17
 * @see com.flyhaze.framework.service.IService<SysMenu, MenuVo>
 * @since jdk1.8
 */
public interface IMenuService extends IService<SysMenu, MenuVo> {
}