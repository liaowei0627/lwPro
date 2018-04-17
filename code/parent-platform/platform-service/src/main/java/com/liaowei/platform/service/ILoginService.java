/**
 * platform-service
 * ILoginService.java
 */
package com.liaowei.platform.service;

import com.liaowei.framework.core.exception.ApplicationException;
import com.liaowei.platform.vo.UserVo;

/**
 * ILoginService
 *
 * 登录相关服务接口
 *
 * @author liaowei
 * @date 创建时间：2018年4月6日 下午10:29:16 
 * @since jdk1.8
 */
public interface ILoginService {

    UserVo findByUserName(String userName) throws ApplicationException;
}