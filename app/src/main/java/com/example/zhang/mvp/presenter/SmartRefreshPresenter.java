package com.example.zhang.mvp.presenter;

import com.example.zhang.base.BasePresenter;
import com.example.zhang.http.CustomerSubscribe;
import com.example.zhang.mvp.contract.SmartRefreshContract;
import com.example.zhang.mvp.model.SmartRefreshModel;
import com.example.zhang.mvp.model.bean.SmartRefreshBean;
import com.example.zhang.utils.RxLifeCycleUtils;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SmartRefreshPresenter extends BasePresenter<SmartRefreshContract.ISmartRefreshView, SmartRefreshModel> {
    public SmartRefreshPresenter(SmartRefreshContract.ISmartRefreshView view) {
        super(view);
        model = new SmartRefreshModel();
    }

    public void getData() {
        model.getSmartRefreshData()
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(RxLifeCycleUtils.<List<SmartRefreshBean>>bindToLifecycle(view))
                .subscribe(new CustomerSubscribe<List<SmartRefreshBean>>() {
                    @Override
                    public void onNext(List<SmartRefreshBean> smartRefreshBeans) {
                        view.showContent(smartRefreshBeans);
                    }
                });
    }
}
