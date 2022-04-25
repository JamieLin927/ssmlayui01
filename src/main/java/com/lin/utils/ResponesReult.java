package com.lin.utils;

import sun.rmi.runtime.Log;

/**
 * @author :lzm
 * @date :2022/4/8 13:54
 * @description :
 * @modyified By:
 */
public class ResponesReult<T> {
    private  Integer code;
    private  String  msg;

    public ResponesReult(Integer code, String msg, long count, T data) {
        this.code = code;
        this.msg = msg;
        this.count = count;
        Data = data;
    }

    private  long count;
    private  T Data;

    public ResponesReult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResponesReult(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        Data = data;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return Data;
    }

    public void setData(T data) {
        Data = data;
    }
}
