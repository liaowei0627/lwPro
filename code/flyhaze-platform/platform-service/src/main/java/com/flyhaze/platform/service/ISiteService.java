/**
 * platform-service
 * ISiteService.java
 */
package com.flyhaze.platform.service;

import com.flyhaze.framework.core.exception.ApplicationException;
import com.flyhaze.framework.service.IService;
import com.flyhaze.platform.entity.SysSite;
import com.flyhaze.platform.vo.SiteVo;

/**
 * ISiteService
 *
 * 站点管理服务接口
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-09-14 23:24:24
 * @see com.flyhaze.framework.service.IService<SysSite, SiteVo>
 * @since jdk1.8
 */
public interface ISiteService extends IService<SysSite, SiteVo> {

    /**
     * 新增数据
     * 
     * @param vo
     * @return
     * @throws ApplicationException
     */
    SiteVo addVo(SiteVo vo) throws ApplicationException;

    /**
     * 修改数据
     * 
     * @param vo
     * @return
     * @throws ApplicationException
     */
    SiteVo updateVo(SiteVo vo) throws ApplicationException;

    /**
     * 批量删除数据
     * 
     * @param pks
     * @throws ApplicationException
     */
    void delList(String[] id) throws ApplicationException;
}