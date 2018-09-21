/**
 * platform-service
 * IDictionaryService.java
 */
package com.flyhaze.platform.service;

import com.flyhaze.framework.service.IService;
import com.flyhaze.platform.entity.SysDictionary;
import com.flyhaze.platform.vo.DictionaryVo;

/**
 * IDictionaryService
 *
 * 数据字典管理服务接口
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-09-16 09:48:32
 * @see com.flyhaze.framework.service.IService<SysDictionary, DictionaryVo>
 * @since jdk1.8
 */
public interface IDictionaryService extends IService<SysDictionary, DictionaryVo> {
}