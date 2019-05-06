package com.example.zhang.mvp.presenter;

import android.annotation.SuppressLint;

import com.example.zhang.base.BasePresenter;
import com.example.zhang.mvp.contract.RxLifeCycleContract;
import com.example.zhang.mvp.model.RxLifeCycleModel;
import com.example.zhang.mvp.ui.RxLifeCycleActivity;
import com.example.zhang.utils.LogUtils;
import com.example.zhang.utils.RxLifeCycleUtils;
import com.trello.rxlifecycle2.android.ActivityEvent;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @author zzh
 */
public class RxLifeCyclePresenter extends BasePresenter<RxLifeCycleContract.IRxLifeCycleView, RxLifeCycleModel> {
    private static final String TAG = RxLifeCycleActivity.class.getSimpleName();

    public RxLifeCyclePresenter(RxLifeCycleContract.IRxLifeCycleView view) {
        super(view);
        model = new RxLifeCycleModel();
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    @SuppressLint("CheckResult")
    public void interval() {
        model.getIntervalData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(RxLifeCycleUtils.<Integer>bindToLifecycle(view))
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer aLong) {
                        LogUtils.error(TAG, "rxJavaConcatExample--Consumer--:" + Thread.currentThread().getName() + "--:" + aLong);
                    }
                });
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    @SuppressLint("CheckResult")
    public void intervalRxLifeCycle(ActivityEvent event) {
        model.getIntervalData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(RxLifeCycleUtils.<Integer>bindUntilEvent(view, event))
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer aLong)  {
                        LogUtils.error(TAG, "rxJavaConcatExample--Consumer--:" + Thread.currentThread().getName() + "--:" + aLong);
                    }
                });
    }
}
