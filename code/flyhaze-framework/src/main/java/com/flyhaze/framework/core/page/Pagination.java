/**
 * flyhaze-framework
 * Pagination.java
 */
package com.flyhaze.framework.core.page;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.flyhaze.framework.core.query.order.OrderBy;

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
public class Pagination<E> implements Serializable {

    /**
     * 是否分页，默认分页
     */
    @JsonIgnore
    @Getter
    @Setter
    private Boolean pageable = true;

    /**
     * 总条数，没有数据为0
     */
    @Getter
    @Setter
    private int total = 0;

    /**
     * 每页条数
     */
    @JsonIgnore
    @Getter
    @Setter
    private int rows = 20;

    /**
     * 页号<br>
     * 从1开始
     */
    @JsonIgnore
    @Getter
    @Setter
    private int page = 1;

    /**
     * 查询起始记录下标<br>
     * 从0开始
     */
    @JsonIgnore
    private int startPosition;

    /**
     * 排序字段
     */
    @JsonIgnore
    @Getter
    @Setter
    private List<OrderBy> orderBy;

    /**
     * 数据
     */
    @JsonProperty("rows")
    @NonNull
    @Getter
    @Setter
    private List<E> data;

    public Pagination(int pageSize, int pageNumber) {
        this.rows = pageSize;
        this.page = pageNumber;
    }

    public Pagination(List<OrderBy> orderBy) {
        this.orderBy = orderBy;
    }

    public Pagination(int pageSize, int pageNumber, List<OrderBy> orderBy) {
        this.rows = pageSize;
        this.page = pageNumber;
        this.orderBy = orderBy;
    }

    public Pagination(int pageSize, int pageNumber, List<OrderBy> orderBy, boolean pageable) {
        this.rows = pageSize;
        this.page = pageNumber;
        this.orderBy = orderBy;
        this.pageable = pageable;
    }

    public Pagination(int total, int pageSize, int pageNumber, List<E> data) {
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
        if (1 == page) {
            startPosition = 0;
        } else {
            startPosition = page * rows - rows;
        }
        return startPosition;
    }
}