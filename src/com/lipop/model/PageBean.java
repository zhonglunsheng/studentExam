package com.lipop.model;

public class PageBean {
    private int page;
    private int pageSize;
    @SuppressWarnings("unused")
	private int pageStart;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageStart() {
        return pageStart=(page-1)*pageSize;
    }

    public void setPageStart(int pageStart) {
        this.pageStart = pageStart;
    }
}
