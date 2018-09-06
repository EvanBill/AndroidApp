package com.example.zhang.http;

/**
 * Created by Administrator on 2018/1/10.
 */

public class ResponseBean {

    public int customCode;
    public String message;

    public ResponseBean() {
    }

    public ResponseBean(int customCode, String message) {
        this.customCode = customCode;
        this.message = message;
    }

    public int getCustomCode() {
        return customCode;
    }

    public void setCustomCode(int customCode) {
        this.customCode = customCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ResponseBean{" +
                "customCode=" + customCode +
                ", message='" + message + '\'' +
                '}';
    }
}
