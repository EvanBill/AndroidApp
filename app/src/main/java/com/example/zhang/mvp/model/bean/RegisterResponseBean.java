package com.example.zhang.mvp.model.bean;

import android.support.annotation.NonNull;

import java.util.List;
/**
 * @author zzh
 */
public class RegisterResponseBean {

    /**
     * data : {"collectIds":[],"email":"","icon":"","id":9812,"password":"123456","token":"","type":0,"username":"zzz123456"}
     * errorCode : 0
     * errorMsg :
     */

    private DataBean data;
    private int errorCode;
    private String errorMsg;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    @NonNull
    @Override
    public String toString() {
        return "RegisterResponseBean{" +
                "data=" + data +
                ", errorCode=" + errorCode +
                ", errorMsg='" + errorMsg + '\'' +
                '}';
    }

    public static class DataBean {
        /**
         * collectIds : []
         * email :
         * icon :
         * id : 9812
         * password : 123456
         * token :
         * type : 0
         * username : zzz123456
         */

        private String email;
        private String icon;
        private int id;
        private String password;
        private String token;
        private int type;
        private String username;
        private List<?> collectIds;

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public List<?> getCollectIds() {
            return collectIds;
        }

        public void setCollectIds(List<?> collectIds) {
            this.collectIds = collectIds;
        }

        @NonNull
        @Override
        public String toString() {
            return "DataBean{" +
                    "email='" + email + '\'' +
                    ", icon='" + icon + '\'' +
                    ", id=" + id +
                    ", password='" + password + '\'' +
                    ", token='" + token + '\'' +
                    ", type=" + type +
                    ", username='" + username + '\'' +
                    ", collectIds=" + collectIds +
                    '}';
        }
    }
}
