package com.liaowei.framework.page;

import java.util.List;

public class Pagination<T> {

    private int total = 0;
    private int pageSize = 20;
    private int pageNumber = 1;
    private int startPosition = 1;
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
        startPosition = pageNumber * pageSize - pageSize + 1;
        return startPosition;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}