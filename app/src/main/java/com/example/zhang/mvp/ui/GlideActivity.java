package com.example.zhang.mvp.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.zhang.GlideApp;
import com.example.zhang.R;
import com.example.zhang.base.BaseActivity;
import com.example.zhang.mvp.contract.GlideContract;
import com.example.zhang.mvp.presenter.GlidePresenter;
import com.example.zhang.utils.LogUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GlideActivity extends BaseActivity<GlidePresenter> implements GlideContract.IGlideView {
    private static final String TAG = GlideActivity.class.getSimpleName();
    private String url = "http://www.wanandroid.com/blogimgs/50c115c2-cf6c-4802-aa7b-a4334de444cd.png";
    @BindView(R.id.iv_glide)
    ImageView iv_glide;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide);
        ButterKnife.bind(this);
        presenter = new GlidePresenter(this);
    }

    @OnClick(R.id.btn_glide_test)
    public void onClick() {
//        Glide.with(this).load(url).into(iv_glide);
        GlideApp.with(this).load(url).into(iv_glide);
    }
}
