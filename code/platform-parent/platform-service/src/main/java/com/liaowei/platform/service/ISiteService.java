/**
 * platform-service
 * ISiteService.java
 */
package com.liaowei.platform.service;

import com.liaowei.framework.core.exception.ApplicationException;
import com.liaowei.framework.service.IService;
import com.liaowei.platform.entity.SysSite;
import com.liaowei.platform.vo.SiteVo;

/**
 * ISiteService
 *
 * 站点管理服务接口
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-09-14 23:24:24
 * @see com.liaowei.framework.service.IService<SysSite, SiteVo>
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