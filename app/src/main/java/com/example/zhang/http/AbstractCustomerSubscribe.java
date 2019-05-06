package com.example.zhang.http;


import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 *
 * @author zzh
 * @date 2018/1/10
 */

public abstract class AbstractCustomerSubscribe<T> implements Observer<T> {
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
