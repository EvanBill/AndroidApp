package com.example.zhang.mvp.model;

import com.blankj.utilcode.util.TimeUtils;
import com.example.zhang.utils.LogUtils;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;

public class RxLifeCycleModel {
    private static final String TAG = RxLifeCycleModel.class.getSimpleName();

    public Flowable<Integer> getIntervalData() {
        return Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> emitter) throws Exception {
                for (int i = 1; ; i++) {
                    LogUtils.error(TAG, "getIntervalData--emitter--:" + Thread.currentThread().getName() + "--:" + i + "-----iscancelled:" + emitter.isCancelled());
                    LogUtils.error(TAG, "getIntervalData--emitter--:" + Thread.currentThread().getName() + "--:" + TimeUtils.getNowString() + "-----request:" + emitter.requested());
                    if (emitter.isCancelled()) {
                        break;
                    }else {
                        while (emitter.requested() == 0) {

                        }
                    }
                    emitter.onNext(i);

                }
            }
        }, BackpressureStrategy.ERROR);
    }
}

