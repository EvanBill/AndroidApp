package com.example.zhang.mvp.presenter;

import com.example.zhang.base.BasePresenter;
import com.example.zhang.mvp.contract.TouchContract;
import com.example.zhang.mvp.model.TouchModel;

public class TouchPresenter extends BasePresenter<TouchContract.ITouchView, TouchModel> {
    public TouchPresenter(TouchContract.ITouchView view) {
        super(view);
        model = new TouchModel();
    }
}
