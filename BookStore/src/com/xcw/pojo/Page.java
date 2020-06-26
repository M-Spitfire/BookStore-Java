package com.xcw.pojo;

public class Page {
    private Integer pageSize;
    private Integer currentPage;
    private Integer min;
    private Integer max;

    public Integer getMin() {
        return min;
    }

    public void setMin(Integer min) {
        this.min = min;
    }

    public Integer getMax() {
        return max;
    }

    public void setMax(Integer max) {
        this.max = max;
    }

    public Page(Integer pageSize, Integer currentPage, Integer min, Integer max) {
        this.pageSize = pageSize;
        this.currentPage = currentPage;
        this.min = min;
        this.max = max;
    }

    public Page() {
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    @Override
    public String toString() {
        return "Page{" +
                "pageSize=" + pageSize +
                ", currentPage=" + currentPage +
                ", min=" + min +
                ", max=" + max +
                '}';
    }
}
