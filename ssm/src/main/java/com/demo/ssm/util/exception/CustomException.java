package com.demo.ssm.util.exception;

/**
 * Created by Administrator on 2015/11/28.
 * 自定义异常类型
 */
public class CustomException extends Exception {
    private String message;

    public CustomException(){}

    public CustomException(String message) {
        super(message);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
