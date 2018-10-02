/**
 * study-service
 * ILoginService.java
 */
package com.flyhaze.study.service;

import com.flyhaze.core.exception.ApplicationException;
import com.flyhaze.study.vo.UserVo;

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