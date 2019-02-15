package com.example.zhang.mvp.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.zhang.R;
import com.example.zhang.base.BaseActivity;
import com.example.zhang.mvp.contract.TouchContract;
import com.example.zhang.mvp.presenter.TouchPresenter;

public class TouchActivity extends BaseActivity<TouchPresenter> implements TouchContract.ITouchView {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch);
        presenter = new TouchPresenter(this);
    }
}
