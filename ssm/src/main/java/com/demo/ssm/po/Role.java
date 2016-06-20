package com.demo.ssm.po;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/2/1.
 */
public class Role implements Serializable {
    private String id;
    private String name;
    private String sort;
    private String note;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
