package com.example.zhang.mvp.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.zhang.R;
import com.example.zhang.base.BaseFragment;
import com.example.zhang.utils.IntentUtils;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ToolsFragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_tools, container, false);
        ButterKnife.bind(this, view);

        return view;

    }

    @OnClick({R.id.btn_main_rxJava, R.id.btn_main_lifecycle, R.id.btn_main_permissions, R.id.btn_main_glide
            , R.id.btn_main_smart_refresh, R.id.btn_main_web_view, R.id.btn_main_touch, R.id.btn_main_aidl_client
            , R.id.btn_main_customer_flow_layout, R.id.btn_main_frame_animation, R.id.btn_main_time_count_down,
            R.id.btn_main_video_recording, R.id.btn_main_event_bus, R.id.btn_main_file_provider
            , R.id.btn_main_service,R.id.btn_main_ripple})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_main_rxJava:
                IntentUtils.startRxJavaActivity(getContext());
                break;
            case R.id.btn_main_lifecycle:
                IntentUtils.startRxLifeCycleActivity(getContext());
                break;
            case R.id.btn_main_permissions:
                IntentUtils.startPermissionsActivity(getContext());
                break;
            case R.id.btn_main_glide:
                IntentUtils.startGlideActivity(getContext());
                break;
            case R.id.btn_main_smart_refresh:
                IntentUtils.startSmartRefreshActivity(getContext());
                break;
            case R.id.btn_main_web_view:
                IntentUtils.startWebViewActivity(getContext());
                break;
            case R.id.btn_main_touch:
                IntentUtils.startTouchActivity(getContext());
                break;
            case R.id.btn_main_aidl_client:
                IntentUtils.startAidlClientActivity(getContext());
                break;
            case R.id.btn_main_customer_flow_layout:
                IntentUtils.startCustomerFlowLayoutActivity(getContext());
                break;
            case R.id.btn_main_frame_animation:
                IntentUtils.startFrameAnimationActivity(getContext());
                break;
            case R.id.btn_main_time_count_down:
                IntentUtils.startTimeCountDownActivity(getContext());
                break;
            case R.id.btn_main_video_recording:
                IntentUtils.startVideoRecordingActivity(getContext());
                break;
            case R.id.btn_main_event_bus:
                IntentUtils.startEventBusActivity(getContext());
                break;
            case R.id.btn_main_file_provider:
                IntentUtils.startFileProviderActivity(getContext());
                break;
            case R.id.btn_main_service:
                IntentUtils.startServiceActivity(getContext());
                break;
            case R.id.btn_main_ripple:
                IntentUtils.startRippleActivity(getContext());
                break;
            default:
                break;
        }
    }
}
