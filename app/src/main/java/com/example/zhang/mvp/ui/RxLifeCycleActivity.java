package com.example.zhang.mvp.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.example.zhang.R;
import com.example.zhang.base.BaseActivity;
import com.example.zhang.mvp.contract.RxLifeCycleContract;
import com.example.zhang.mvp.presenter.RxLifeCyclePresenter;
import com.example.zhang.utils.IntentUtils;
import com.trello.rxlifecycle2.android.ActivityEvent;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author zzh
 */
public class RxLifeCycleActivity extends BaseActivity<RxLifeCyclePresenter> implements RxLifeCycleContract.IRxLifeCycleView {
    private RxLifeCyclePresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxlifecycle);
        presenter = new RxLifeCyclePresenter(this);
        ButterKnife.bind(this);
    }

    @SuppressWarnings("AlibabaRemoveCommentedCode")
    @Override
    protected void onStart() {
        super.onStart();
//        presenter.interval();
        presenter.intervalRxLifeCycle(ActivityEvent.DESTROY);
    }

    @OnClick(R.id.btn_rxLifecycle_click)
    public void onClick() {
        IntentUtils.startPermissionsActivity(this);
    }

    @Override
    public void showContent() {

    }
}
