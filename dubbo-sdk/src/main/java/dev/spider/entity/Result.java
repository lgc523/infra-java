package dev.spider.entity;

import java.io.Serializable;

/**
 * @author spider
 */
public class Result<T> implements Serializable {
    private T data;
    private boolean success;
    private String msg;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

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
}
