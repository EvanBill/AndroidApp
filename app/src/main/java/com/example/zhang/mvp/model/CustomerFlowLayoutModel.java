package com.example.zhang.mvp.model;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

public class CustomerFlowLayoutModel {
    public Observable<String> getContent(){
       return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("flow1");
                emitter.onComplete();
            }
        });

    }
}
