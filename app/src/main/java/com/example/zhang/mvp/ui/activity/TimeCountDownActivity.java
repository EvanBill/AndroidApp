package com.example.zhang.mvp.ui.activity;

import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.example.zhang.R;
import com.example.zhang.base.BaseActivity;
import com.example.zhang.mvp.contract.TimeCountDownContract;
import com.example.zhang.mvp.presenter.TimeCountDownPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author : zzh
 * @date : 2019/5/9
 * @desc :
 */
public class TimeCountDownActivity extends BaseActivity<TimeCountDownPresenter> implements TimeCountDownContract.ITimeCountDownView {
    @BindView(R.id.tv_time_count_down_content)
    TextView tvTimeCountDownContent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_count_down);
        ButterKnife.bind(this);
        presenter = new TimeCountDownPresenter(this);
    }

    @OnClick({R.id.btn_time_count_down_click_start, R.id.btn_time_count_down_click_stop})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_time_count_down_click_start:
                presenter.startTimeCountDown();
                break;
            case R.id.btn_time_count_down_click_stop:
                presenter.cancelTime();
                break;
            default:
                break;
        }
    }


    @Override
    public void showTime(int time) {
        tvTimeCountDownContent.setText(getResources().getString(R.string.str_time_count_down, time));
    }
}
