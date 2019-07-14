package com.example.zhang.mvp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.core.content.ContextCompat;

import com.example.zhang.R;
import com.example.zhang.mvp.service.FloatService;
import com.example.zhang.utils.LogUtils;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;


public class ServiceActivity extends RxAppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_service_start})
    public void onClick(View view) {
        if (R.id.btn_service_start == view.getId()) {
            LogUtils.error("ddd", "onCreate");
            startService1();
        }

    }

    public void startService1() {
        Intent intent = new Intent(this, FloatService.class);
//        ContextCompat.startForegroundService(this,intent);
        startService(intent);
    }
}
