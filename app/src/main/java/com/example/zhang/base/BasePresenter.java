package com.example.zhang.base;

public class BasePresenter<T, K> {
    protected T model;
    protected K view;

    public BasePresenter(K view) {
        this.view = view;
    }
}
