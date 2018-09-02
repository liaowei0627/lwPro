/**
 * platform-service
 * IMenuService.java
 */
package com.liaowei.platform.service;

import com.liaowei.framework.core.exception.ApplicationException;
import com.liaowei.framework.service.IService;
import com.liaowei.platform.entity.SysMenu;
import com.liaowei.platform.vo.MenuVo;

/**
 * IMenuService
 *
 * 菜单管理服务接口
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-21 23:38:17
 * @see com.liaowei.framework.service.IService<MenuVo, SysMenu, String>
 * @since jdk1.8
 */
public interface IMenuService extends IService<MenuVo, SysMenu, String> {

    /**
     * 新增数据
     * 
     * @param vo
     * @return
     * @throws ApplicationException
     */
    MenuVo addVo(MenuVo vo) throws ApplicationException;
}