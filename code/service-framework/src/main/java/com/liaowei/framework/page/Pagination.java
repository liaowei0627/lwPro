package com.liaowei.framework.page;

import java.util.List;

/**
 * Pagination
 *
 * 分页查询对象
 *
 * @author liaowei
 * @date 创建时间：2018年3月31日 下午11:07:30 
 * @since jdk1.8
 */
public class Pagination<T> {

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

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getStartPosition() {
        startPosition = pageNumber * pageSize - pageSize;
        return startPosition;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}