package com.quokka.task.dto;

import java.util.List;

public class PaginationDto<T> {

    private List<T> list;

    private int count;

    public PaginationDto() {
    }

    public PaginationDto(List<T> list, int count) {
        this.list = list;
        this.count = count;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
