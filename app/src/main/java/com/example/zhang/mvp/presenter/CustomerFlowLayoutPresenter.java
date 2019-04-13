package com.example.zhang.mvp.presenter;

import com.example.zhang.base.BasePresenter;
import com.example.zhang.http.CustomerSubscribe;
import com.example.zhang.mvp.contract.CustomerFlowLayoutContract;
import com.example.zhang.mvp.model.CustomerFlowLayoutModel;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class CustomerFlowLayoutPresenter extends BasePresenter<CustomerFlowLayoutContract.ICustomerFlowLayoutView, CustomerFlowLayoutModel> {
    public CustomerFlowLayoutPresenter(CustomerFlowLayoutContract.ICustomerFlowLayoutView view) {
        super(view);
        model = new CustomerFlowLayoutModel();
    }

    public void getContent() {
        model.getContent()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CustomerSubscribe<String>() {
                    @Override
                    public void onNext(String content) {
                        view.setContent(content);
                    }
                });
    }
}
