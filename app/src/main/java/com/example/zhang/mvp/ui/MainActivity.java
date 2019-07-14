package com.example.zhang.mvp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.blankj.utilcode.constant.TimeConstants;
import com.blankj.utilcode.util.TimeUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.example.zhang.R;
import com.example.zhang.base.BaseActivity;
import com.example.zhang.client.AidlClientActivity;
import com.example.zhang.mvp.contract.MainContract;
import com.example.zhang.mvp.model.bean.ProductBean;
import com.example.zhang.mvp.presenter.MainPresenter;
import com.example.zhang.utils.StringLogUtils;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * @author zzh
 */
public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.IMainView {
    private long backTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        presenter = new MainPresenter(this);
//        StringLogUtils.Companion.logString();
    }

    @OnClick({R.id.btn_main_rxJava, R.id.btn_main_lifecycle, R.id.btn_main_permissions, R.id.btn_main_glide
            , R.id.btn_main_smart_refresh, R.id.btn_main_web_view, R.id.btn_main_touch, R.id.btn_main_aidl_client
            , R.id.btn_main_customer_flow_layout, R.id.btn_main_frame_animation, R.id.btn_main_time_count_down,
            R.id.btn_main_video_recording, R.id.btn_main_event_bus, R.id.btn_main_file_provider
            , R.id.btn_main_service})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_main_rxJava:
                Intent intentRxJava = new Intent(this, RxJavaActivity.class);
                startActivity(intentRxJava);
                break;
            case R.id.btn_main_lifecycle:
                Intent intentLifecycle = new Intent(this, RxLifeCycleActivity.class);
                startActivity(intentLifecycle);
                break;
            case R.id.btn_main_permissions:
                Intent intentPermissions = new Intent(this, PermissionsActivity.class);
                startActivity(intentPermissions);
                break;
            case R.id.btn_main_glide:
                Intent intentGlide = new Intent(this, GlideActivity.class);
                startActivity(intentGlide);
                break;
            case R.id.btn_main_smart_refresh:
                Intent intentSmartRefresh = new Intent(this, SmartRefreshActivity.class);
                startActivity(intentSmartRefresh);
                break;
            case R.id.btn_main_web_view:
                Intent intentWebView = new Intent(this, WebViewActivity.class);
                startActivity(intentWebView);
                break;
            case R.id.btn_main_touch:
                Intent intentTouch = new Intent(this, TouchActivity.class);
                startActivity(intentTouch);
                break;
            case R.id.btn_main_aidl_client:
                Intent btnMainAidlClient = new Intent(this, AidlClientActivity.class);
                startActivity(btnMainAidlClient);
                break;
            case R.id.btn_main_customer_flow_layout:
                Intent btnMainCustomerFlowLayout = new Intent(this, CustomerFlowLayoutActivity.class);
                startActivity(btnMainCustomerFlowLayout);
                break;
            case R.id.btn_main_frame_animation:
                Intent frameAnimationIntent = new Intent(this, FrameAnimationActivity.class);
                startActivity(frameAnimationIntent);
                break;
            case R.id.btn_main_time_count_down:
                Intent timeCountDownIntent = new Intent(this, TimeCountDownActivity.class);
                startActivity(timeCountDownIntent);
                break;
            case R.id.btn_main_video_recording:
                Intent videoRecordingIntent = new Intent(this, VideoRecordingActivity.class);
                startActivity(videoRecordingIntent);
                break;
            case R.id.btn_main_event_bus:
                Intent eventBusIntent = new Intent(this, EventBusActivity.class);
                startActivity(eventBusIntent);
                break;
            case R.id.btn_main_file_provider:
                Intent fileProviderIntent = new Intent(this, FileProviderActivity.class);
                startActivity(fileProviderIntent);
                break;
            case R.id.btn_main_service:
                Intent serviceIntent = new Intent(this, ServiceActivity.class);
                startActivity(serviceIntent);
                break;
            default:
                break;
        }
    }

    @Override
    public void showContent(List<ProductBean> productBeanList) {

    }

    @Override
    public void onBackPressed() {
        int backTimeInterval = 2;
        if (TimeUtils.getTimeSpan(TimeUtils.getNowMills(), backTime, TimeConstants.SEC) > backTimeInterval) {
            backTime = TimeUtils.getNowMills();
            ToastUtils.showShort(getString(R.string.str_back_press_info));
        } else {
            super.onBackPressed();
        }
    }

}
