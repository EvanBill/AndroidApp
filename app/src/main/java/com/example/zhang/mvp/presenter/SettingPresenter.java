package com.example.zhang.mvp.presenter;

import com.example.zhang.base.BasePresenter;
import com.example.zhang.mvp.contract.SettingContract;
import com.example.zhang.mvp.model.SettingModel;

public class SettingPresenter extends BasePresenter<SettingContract.ISettingView, SettingModel> {
    public SettingPresenter(SettingContract.ISettingView view) {
        super(view);
        model = new SettingModel();
    }
}
