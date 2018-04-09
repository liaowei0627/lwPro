/**
 * study-service
 * ILoginService.java
 */
package com.liaowei.study.service;

import com.liaowei.framework.service.IService;
import com.liaowei.study.entity.SysUser;
import com.liaowei.study.vo.UserVo;

/**
 * ILoginService
 *
 * 登录相关接口
 *
 * @author liaowei
 * @date 创建时间：2018年4月6日 下午10:29:16 
 * @see com.liaowei.framework.service.ISpringService<UserVo, SysUser, String>
 * @since jdk1.8
 */
public interface ILoginService extends IService<UserVo, SysUser, String> {

}