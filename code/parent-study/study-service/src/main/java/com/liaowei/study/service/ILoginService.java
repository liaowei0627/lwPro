/**
 * study-service
 * ILoginService.java
 */
package com.liaowei.study.service;

import com.liaowei.framework.core.exception.ApplicationException;
import com.liaowei.study.vo.UserVo;

/**
 * ILoginService
 *
 * 登录相关接口
 *
 * @author liaowei
 * @date 创建时间：2018年4月6日 下午10:29:16 
 * @since jdk1.8
 */
public interface ILoginService {

    UserVo findByUserName(String userName) throws ApplicationException;
}