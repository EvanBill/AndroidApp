package com.example.zhang.mvp.ui.activity;

import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.zhang.R;
import com.example.zhang.base.BaseActivity;
import com.example.zhang.mvp.contract.VideoRecordingContract;
import com.example.zhang.mvp.presenter.VideoRecordingPresenter;
import com.otaliastudios.cameraview.CameraListener;
import com.otaliastudios.cameraview.CameraView;
import com.otaliastudios.cameraview.VideoResult;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author : zzh
 * @date : 2019/5/9
 * @desc :视频录制
 */
public class VideoRecordingActivity extends BaseActivity<VideoRecordingPresenter> implements VideoRecordingContract.IVideoRecordingView {
    @BindView(R.id.cv_video_recording)
    CameraView cvVideoRecording;
    @BindView(R.id.tv_video_recording_address)
    TextView tvVideoRecordingAddress;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_recording);
        ButterKnife.bind(this);
        cvVideoRecording.addCameraListener(new CameraListener() {
            @Override
            public void onVideoTaken(@NonNull VideoResult result) {
                super.onVideoTaken(result);
                tvVideoRecordingAddress.setText(result.getFile().getAbsolutePath());
            }
        });
        presenter = new VideoRecordingPresenter(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        cvVideoRecording.open();
    }

    @Override
    protected void onPause() {
        super.onPause();
        cvVideoRecording.close();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cvVideoRecording.destroy();
    }

    @OnClick({R.id.btn_time_count_down_click_start})
    void onClick(View v) {
        if (v.getId() == R.id.btn_time_count_down_click_start) {
            presenter.startTakeCamera(cvVideoRecording);
        }
    }

    @Override
    public void showFileAddress(String fileAddress) {
        tvVideoRecordingAddress.setText(fileAddress);
    }

}
