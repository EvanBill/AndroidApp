package com.example.zhang.mvp.contract;

import com.example.zhang.base.BaseView;

/**
 * @author : zzh
 * @date : 2019/5/9
 * @desc :倒计时
 */
public interface TimeCountDownContract {
    interface ITimeCountDownView extends BaseView {
        /**
         * 展示时间
         *
         * @param time 时间
         */
        void showTime(int time);
    }
}
