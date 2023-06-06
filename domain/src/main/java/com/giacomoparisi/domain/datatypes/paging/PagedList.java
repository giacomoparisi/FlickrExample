package com.giacomoparisi.domain.datatypes.paging;

import java.util.ArrayList;
import java.util.List;

public class PagedList<T> {

    private final List<T> data;
    private final int page;
    private final boolean isCompleted;

    public PagedList(List<T> data, int page, boolean isCompleted) {
        this.data = data;
        this.page = page;
        this.isCompleted = isCompleted;
    }

    public List<T> getData() {
        return data;
    }

    public int getPage() {
        return page;
    }

    public boolean getIsCompleted() {
        return isCompleted;
    }

    public <P> PagedList<P> map(Mapper<T, P> mapper) {
        List<P> mappedList = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            mappedList.add(mapper.map(data.get(i)));
        }
        return new PagedList<>(mappedList, page, isCompleted);
    }

    public PagedList<T> addPage(PagedList<T> other) {
        List<T> newData = new ArrayList<>();
        newData.addAll(data);
        newData.addAll(other.data);
        return new PagedList<>(newData, other.page, other.isCompleted);
    }
}
