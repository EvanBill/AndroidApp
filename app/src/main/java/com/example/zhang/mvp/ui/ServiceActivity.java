package com.example.zhang.mvp.ui;

import android.os.Bundle;
import android.view.View;

import com.example.zhang.R;
import com.example.zhang.base.BaseSimpleActivity;
import com.example.zhang.utils.IntentUtils;
import com.example.zhang.utils.LogUtils;

import butterknife.ButterKnife;
import butterknife.OnClick;


public class ServiceActivity extends BaseSimpleActivity {
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
            IntentUtils.startFloatService(this);
        }
    }

}
