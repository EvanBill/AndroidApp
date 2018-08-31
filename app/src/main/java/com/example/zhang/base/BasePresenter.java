package com.example.zhang.base;

public class BasePresenter<K, T> {
    protected K view;
    protected T model;

    public BasePresenter(K view) {
        this.view = view;
    }


}
