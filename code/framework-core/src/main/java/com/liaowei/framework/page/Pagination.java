/**
 * framework-core
 * Pagination.java
 */
package com.liaowei.framework.page;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
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
    @Getter
    @Setter
    private boolean isPageable = true;

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
     * 排序字段
     */
    @Getter
    @Setter
    private Map<String, OrderEnum> orderBy = null;

    /**
     * 数据
     */
    @NonNull
    @Getter
    @Setter
    private List<E> data;

    public Pagination(int pageSize, int pageNumber) {
        this.rows = pageSize;
        this.page = pageNumber;
    }

    public Pagination(Map<String, OrderEnum> orderBy) {
        this.orderBy = orderBy;
    }

    public Pagination(int pageSize, int pageNumber, Map<String, OrderEnum> orderBy) {
        this.rows = pageSize;
        this.page = pageNumber;
        this.orderBy = orderBy;
    }

    public Pagination(int pageSize, int pageNumber, Map<String, OrderEnum> orderBy, boolean isPageable) {
        this.rows = pageSize;
        this.page = pageNumber;
        this.orderBy = orderBy;
        this.isPageable = isPageable;
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

    /**
     * OrderEnum
     *
     * 排序枚举
     *
     * @author 廖维(EmailTo：liaowei-0627@163.com)
     * @date 2018-04-26 09:39:43
     * @since jdk1.8
     */
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @Getter
    public enum OrderEnum {
        /**
         * 升序
         */
        ASC("asc"),
        /**
         * 降序
         */
        DESC("desc");

        private String text;
    }
}