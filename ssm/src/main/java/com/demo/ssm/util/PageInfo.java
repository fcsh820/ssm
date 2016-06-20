package com.demo.ssm.util;

import java.util.List;
import java.util.Map;

/**
 * 分页实体类（结合easyui）
 * Created by Administrator on 2016/1/30.
 */
public class PageInfo {
    private final static int PAGESIZE = 50; //默认显示记录数

    private int total; //总记录
    private List rows; //显示的记录

    private int from;
    private int size;
    private int page; //当前页
    private Map<String, Object> condition; //查询条件

    public PageInfo(){
    }

    public PageInfo(int page, int size) {
        if (page < 1) {
            this.page = 1;
        } else {
            this.page = page;
        }
        if (size < 0) {
            this.size = PAGESIZE;
        } else {
            this.size = size;
        }
        this.from = (this.page - 1) * size;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List getRows() {
        return rows;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public Map<String, Object> getCondition() {
        return condition;
    }

    public void setCondition(Map<String, Object> condition) {
        this.condition = condition;
    }
}
