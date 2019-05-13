package com.example.zhang.http;

import androidx.annotation.NonNull;

/**
 * @author zzh
 * @date 2018/1/10
 */

public class ResponseBean {

    private int customCode;
    private String message;

    ResponseBean() {
    }

    ResponseBean(int customCode, String message) {
        this.customCode = customCode;
        this.message = message;
    }

    int getCustomCode() {
        return customCode;
    }

    void setCustomCode(int customCode) {
        this.customCode = customCode;
    }

    String getMessage() {
        return message;
    }

    void setMessage(String message) {
        this.message = message;
    }

    @NonNull
    @Override
    public String toString() {
        return "ResponseBean{" +
                "customCode=" + customCode +
                ", message='" + message + '\'' +
                '}';
    }
}
