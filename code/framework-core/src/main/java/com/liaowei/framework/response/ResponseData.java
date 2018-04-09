/**
 * framework-core
 * Pagination.java
 */
package com.liaowei.framework.response;

import java.io.Serializable;

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
public class ResponseData<T> implements Serializable {

    // 返回状态
    private int stat;
    // 返回信息
    private String msg;
    // 返回数据
    private T data;

    public ResponseData() {
    }

    public ResponseData(int stat, String msg) {
        this.stat = stat;
        this.msg = msg;
    }

    public ResponseData(int stat, String msg, T data) {
        this.stat = stat;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 返回状态
     * @return
     */
    public int getStat() {
        return stat;
    }

    /**
     * 返回状态
     * @param stat
     */
    public void setStat(int stat) {
        this.stat = stat;
    }

    /**
     * 返回信息
     * @return
     */
    public String getMsg() {
        return msg;
    }

    /**
     * 返回信息
     * @param msg
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * 返回数据
     * @return
     */
    public T getData() {
        return data;
    }

    /**
     * 返回数据
     * @param data
     */
    public void setData(T data) {
        this.data = data;
    }
}