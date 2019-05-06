package com.example.zhang.mvp.presenter;

import com.example.zhang.base.BasePresenter;
import com.example.zhang.http.AbstractCustomerSubscribe;
import com.example.zhang.mvp.contract.SmartRefreshContract;
import com.example.zhang.mvp.model.SmartRefreshModel;
import com.example.zhang.mvp.model.bean.SmartRefreshBean;
import com.example.zhang.utils.RxLifeCycleUtils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * @author zzh
 */
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
                .map(new Function<List<SmartRefreshBean>, List<SmartRefreshBean>>() {
                    @Override
                    public List<SmartRefreshBean> apply(List<SmartRefreshBean> smartRefreshBeanList) {
                        List<SmartRefreshBean> list = new ArrayList<>();
                        for (int i = 0; i < smartRefreshBeanList.size(); i++) {
                            if (i % 2 == 0) {
                                smartRefreshBeanList.get(i).setItemType(1);
                            } else {
                                smartRefreshBeanList.get(i).setItemType(2);
                            }
                            list.add(smartRefreshBeanList.get(i));
                        }
                        return list;
                    }
                })
                .subscribe(new AbstractCustomerSubscribe<List<SmartRefreshBean>>() {
                    @Override
                    public void onNext(List<SmartRefreshBean> smartRefreshBeans) {
                        view.showContent(smartRefreshBeans);
                    }
                });
    }
}
