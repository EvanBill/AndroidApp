package com.example.zhang.mvp.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.zhang.R;
import com.example.zhang.base.BaseActivity;
import com.example.zhang.mvp.contract.SettingContract;
import com.example.zhang.mvp.presenter.SettingPresenter;

import butterknife.ButterKnife;

public class SettingActivity extends BaseActivity<SettingPresenter> implements SettingContract.ISettingView {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        presenter = new SettingPresenter(this);
        ButterKnife.bind(this);
    }
}
