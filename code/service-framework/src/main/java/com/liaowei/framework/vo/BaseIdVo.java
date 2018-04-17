/**
 * service-framework
 * BaseIdVo.java
 */
package com.liaowei.framework.vo;

import com.liaowei.framework.core.vo.IBasisIdVo;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * BaseIdVo
 *
 * TODO
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-21 00:57:58
 * @see TODO
 * @since jdk1.8
 */
@SuppressWarnings("serial")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@ToString
public class BaseIdVo implements IBasisIdVo {

    // 主键
    protected String id;
}