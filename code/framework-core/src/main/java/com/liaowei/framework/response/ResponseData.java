/**
 * framework-core
 * ResponseData.java
 */
package com.liaowei.framework.response;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * ResponseData
 *
 * Ajax请求返回JSON数据对象
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-08 21:31:43
 * @see java.io.Serializable
 * @since jdk1.8
 */
@SuppressWarnings("serial")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ResponseData<View> implements Serializable {

    // 返回状态
    private int stat;
    // 返回信息
    private String msg;
    // 返回数据
    private View data;

    public ResponseData(int stat, String msg) {
        this.stat = stat;
        this.msg = msg;
    }
}