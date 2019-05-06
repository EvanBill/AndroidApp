package com.example.zhang.mvp.model;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

/**
 * @author zzh
 */
public class CustomerFlowLayoutModel {
    public Observable<String> getContent() {
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter)  {
                emitter.onNext("flow1");
                emitter.onComplete();
            }
        });

    }
}
