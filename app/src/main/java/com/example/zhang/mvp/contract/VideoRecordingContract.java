package com.example.zhang.mvp.contract;

import com.example.zhang.base.BaseView;

/**
 * @author : zzh
 * @date : 2019/5/9
 * @desc :
 */
public interface VideoRecordingContract {
    interface IVideoRecordingView extends BaseView {
        /**
         * 展示视频录制存储地址
         *
         * @param fileAddress 地址
         */
        void showFileAddress(String fileAddress);
    }
}
