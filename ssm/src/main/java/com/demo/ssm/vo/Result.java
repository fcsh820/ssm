package com.demo.ssm.vo;

import java.io.Serializable;

/**
 * 操作结果集
 * Created by Administrator on 2016/1/29.
 */
public class Result implements Serializable {
    private static final long serialVersionUID = 3393565110075129709L;

    private boolean success = false;
    private String msg = "";
    private Object obj = null;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }
}
