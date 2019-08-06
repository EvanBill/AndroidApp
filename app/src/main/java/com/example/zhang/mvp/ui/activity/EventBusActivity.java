package com.example.zhang.mvp.ui.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.example.zhang.R;
import com.example.zhang.base.BaseActivity;
import com.example.zhang.mvp.contract.EventBusContract;
import com.example.zhang.mvp.presenter.EventBusPresenter;

public class EventBusActivity extends BaseActivity<EventBusPresenter> implements EventBusContract.IEventBusView {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus);
        presenter = new EventBusPresenter(this);
    }

}
