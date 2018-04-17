/**
 * framework-core
 * Pagination.java
 */
package com.liaowei.framework.page;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

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
@NoArgsConstructor
@ToString
public class Pagination<T> implements Serializable {

    /**
     * 总条数，没有数据为0
     */
    @Getter
    @Setter
    private int total = 0;

    /**
     * 每页条数
     */
    @Getter
    @Setter
    private int rows = 20;

    /**
     * 页号从1开始
     */
    @Getter
    @Setter
    private int page = 1;
    /**
     * 查询起始记录下标
     * 从0开始
     */
    private int startPosition;

    /**
     * 数据
     */
    @NonNull
    @Getter
    @Setter
    private List<T> data;

    public Pagination(int pageSize, int pageNumber) {
        this.rows = pageSize;
        this.page = pageNumber;
    }

    public Pagination(int total, int pageSize, int pageNumber, List<T> data) {
        this.total = total;
        this.rows = pageSize;
        this.page = pageNumber;
        this.data = data;
    }

    /**
     * 自动计算起始数据下标
     * 
     * @return
     */
    public int getStartPosition() {
        startPosition = page * rows - rows;
        return startPosition;
    }
}