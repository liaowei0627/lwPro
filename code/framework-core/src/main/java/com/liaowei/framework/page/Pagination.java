/**
 * framework-core
 * Pagination.java
 */
package com.liaowei.framework.page;

import java.io.Serializable;
import java.util.List;

/**
 * Pagination
 *
 * 分页查询对象
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-08 21:25:25
 * @see java.io.Serializable
 * @since jdk1.8
 */
@SuppressWarnings("serial")
public class Pagination<T> implements Serializable {

    /**
     * 总条数，没有数据为0
     */
    private int total = 0;
    /**
     * 每页条数
     */
    private int pageSize = 20;
    /**
     * 页号从1开始
     */
    private int pageNumber = 1;
    /**
     * 查询起始记录下标
     * 从0开始
     */
    private int startPosition;
    /**
     * 数据
     */
    private List<T> data;

    public Pagination() {
    }

    public Pagination(int total, int pageSize, int pageNumber, int startPosition, List<T> data) {
        this.total = total;
        this.pageSize = pageSize;
        this.pageNumber = pageNumber;
        this.startPosition = startPosition;
        this.data = data;
    }

    /**
     * 总条数，没有数据为0
     */
    public int getTotal() {
        return total;
    }

    /**
     * 总条数，没有数据为0
     */
    public void setTotal(int total) {
        this.total = total;
    }

    /**
     * 每页条数
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * 每页条数
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * 页号从1开始
     */
    public int getPageNumber() {
        return pageNumber;
    }

    /**
     * 页号从1开始
     */
    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    /**
     * 自动计算起始数据下标
     * 
     * @return
     */
    public int getStartPosition() {
        startPosition = pageNumber * pageSize - pageSize;
        return startPosition;
    }

    /**
     * 数据
     */
    public List<T> getData() {
        return data;
    }

    /**
     * 数据
     */
    public void setData(List<T> data) {
        this.data = data;
    }
}