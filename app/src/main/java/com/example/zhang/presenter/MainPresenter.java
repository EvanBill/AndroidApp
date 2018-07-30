package com.example.zhang.presenter;

import com.example.zhang.base.BasePresenter;
import com.example.zhang.bean.ProductBean;
import com.example.zhang.model.MainModel;
import com.example.zhang.model.contract.MainContract;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainPresenter extends BasePresenter<MainModel, MainContract.IMainView> {
    public MainPresenter(MainContract.IMainView view) {
        super(view);
        model = new MainModel();
    }

    public void getData() {
        Flowable.just(model.getProductData())
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .blockingSubscribe(new Consumer<List<ProductBean>>() {
                    @Override
                    public void accept(List<ProductBean> productBeans) throws Exception {
                        view.showContent(productBeans);
                    }

                });
    }
}
