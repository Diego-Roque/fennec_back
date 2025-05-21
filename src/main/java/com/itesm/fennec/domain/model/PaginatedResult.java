package com.itesm.fennec.domain.model;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PaginatedResult<T> {
    private List<T> data;
    private long total;
    private int page;
    private int limit;
    private int totalPages;

    public PaginatedResult() {}

    public PaginatedResult(List<T> data, long total, int page, int limit) {
        this.data = data;
        this.total = total;
        this.page = page;
        this.limit = limit;
        this.totalPages = (int) Math.ceil((double) total / limit);
    }

    // Getters y setters
    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}
