package com.example.zhang.mvp.ui.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;

import android.view.View;
import android.widget.ImageView;

import com.example.zhang.R;
import com.example.zhang.base.BaseActivity;
import com.example.zhang.mvp.contract.FrameAnimationContract;
import com.example.zhang.mvp.presenter.FrameAnimationPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author : zzh
 * @date : 2019/5/9
 * @desc :帧动画
 */
public class FrameAnimationActivity extends BaseActivity<FrameAnimationPresenter> implements FrameAnimationContract.IFrameAnimationView {
    @BindView(R.id.iv_frame)
    ImageView ivFrame;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame_animation);
        ButterKnife.bind(this);
        presenter = new FrameAnimationPresenter(this);
    }

    @OnClick({R.id.btn_frame})
    void onClick(View v) {
        if (v.getId() == R.id.btn_frame) {
            presenter.startFrameAnimation(this, ivFrame);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.stopFrameAnimation();
    }
}
