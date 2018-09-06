package com.example.zhang.http;


import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Administrator on 2018/1/10.
 */

public abstract class CustomerSubscribe<T> implements Observer<T> {
    @Override
    public void onSubscribe(Disposable d) {

    }


    @Override
    public void onError(Throwable e) {
        ResponseBean responseBean = HandleResponse.handleException(e);
        onError(responseBean);
    }

    @Override
    public void onComplete() {

    }

    public void onError(ResponseBean responseBean) {

    }
}
