package com.example.zhang.mvp.presenter;

import com.example.zhang.base.BaseModel;
import com.example.zhang.base.BasePresenter;
import com.example.zhang.mvp.contract.VideoRecordingContract;
import com.example.zhang.mvp.ui.VideoRecordingActivity;
import com.otaliastudios.cameraview.CameraView;

import java.io.File;

/**
 * @author : zzh
 * @date : 2019/5/9
 * @desc :
 */
public class VideoRecordingPresenter extends BasePresenter<VideoRecordingContract.IVideoRecordingView, BaseModel> {
    public VideoRecordingPresenter(VideoRecordingContract.IVideoRecordingView view) {
        super(view);
    }

    /**
     * 开始录制视频
     */
    public void startTakeCamera(CameraView camera) {
        VideoRecordingActivity activity = (VideoRecordingActivity) view;
        camera.takeVideo(new File(activity.getExternalCacheDir(), "video.mp4"), 10000);
    }
}
