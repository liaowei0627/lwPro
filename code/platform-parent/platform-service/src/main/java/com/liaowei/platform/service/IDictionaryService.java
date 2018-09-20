/**
 * platform-service
 * IDictionaryService.java
 */
package com.liaowei.platform.service;

import com.liaowei.framework.service.IService;
import com.liaowei.platform.entity.SysDictionary;
import com.liaowei.platform.vo.DictionaryVo;

/**
 * IDictionaryService
 *
 * 数据字典管理服务接口
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-09-16 09:48:32
 * @see com.liaowei.framework.service.IService<SysDictionary, DictionaryVo>
 * @since jdk1.8
 */
public interface IDictionaryService extends IService<SysDictionary, DictionaryVo> {
}