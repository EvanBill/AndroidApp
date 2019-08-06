package com.example.zhang.mvp.ui.activity;

import android.os.Bundle;
import android.view.View;

import com.example.zhang.R;
import com.example.zhang.base.BaseSimpleActivity;
import com.example.zhang.utils.IntentUtils;

import butterknife.ButterKnife;
import butterknife.OnClick;


public class ServiceActivity extends BaseSimpleActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_service_start, R.id.btn_service_update})
    public void onClick(View view) {
        switch (view.getId()) {
            //创建服务，并创建notification
            case R.id.btn_service_start:
                IntentUtils.startFloatService(this);
                break;
            //更新notification
            case R.id.btn_service_update:
             IntentUtils.startFloatServiceForUpdateNotification(this);
                break;
            default:
                break;
        }
    }

}
