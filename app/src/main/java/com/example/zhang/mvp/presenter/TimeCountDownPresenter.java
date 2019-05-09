package com.example.zhang.mvp.presenter;

import android.os.CountDownTimer;

import com.example.zhang.base.BasePresenter;
import com.example.zhang.mvp.contract.TimeCountDownContract;
import com.example.zhang.mvp.model.TimeCountDownModel;
import com.example.zhang.mvp.ui.TimeCountDownActivity;

/**
 * @author : zzh
 * @date : 2019/5/9
 * @desc :
 */
public class TimeCountDownPresenter extends BasePresenter<TimeCountDownContract.ITimeCountDownView, TimeCountDownModel> {
    private CountDownTimer countDownTimer;

    public TimeCountDownPresenter(final TimeCountDownContract.ITimeCountDownView view) {
        super(view);
        model = new TimeCountDownModel();
        final TimeCountDownActivity activity = (TimeCountDownActivity) view;
        countDownTimer = new CountDownTimer(10 * 1000 + 300, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {

                if (!activity.isFinishing()) {
                    view.showTime((int) (millisUntilFinished / 1000));
                }
            }

            @Override
            public void onFinish() {
                if (!activity.isFinishing()) {
                    view.showTime(0);
                }
            }
        };
    }

    /**
     * 开始倒计时
     */
    public void startTimeCountDown() {
        if (countDownTimer != null) {
            countDownTimer.start();
        }
    }

    /**
     * 取消倒计时
     */
    public void cancelTime() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }
}
