package com.example.zhang.mvp.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.zhang.R;
import com.example.zhang.base.BaseActivity;
import com.example.zhang.mvp.contract.TouchContract;
import com.example.zhang.mvp.presenter.TouchPresenter;
import com.example.zhang.utils.LogUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TouchActivity extends BaseActivity<TouchPresenter> implements TouchContract.ITouchView {
    private static final String TAG = TouchActivity.class.getSimpleName();
    @BindView(R.id.ll_touch)
    LinearLayout ll_touch;
    @BindView(R.id.button1)
    Button button1;
    @BindView(R.id.button2)
    Button button2;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch);
        ButterKnife.bind(this);
        presenter = new TouchPresenter(this);
        button1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                LogUtils.debug(TAG, "button1--touch");
                return true;
            }
        });
    }

    @OnClick({R.id.ll_touch, R.id.button1, R.id.button2})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_touch:
                LogUtils.debug(TAG, "ll_touch");
                break;
            case R.id.button1:
                LogUtils.debug(TAG, "button1");
                break;
            case R.id.button2:
                LogUtils.debug(TAG, "button2");
                break;
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    @Override
    public void onUserInteraction() {
        super.onUserInteraction();
        LogUtils.debug(TAG, "onUserInteraction");
    }
}
