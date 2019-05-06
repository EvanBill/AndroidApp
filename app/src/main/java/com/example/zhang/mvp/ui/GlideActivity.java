package com.example.zhang.mvp.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.example.zhang.GlideApp;
import com.example.zhang.R;
import com.example.zhang.base.BaseActivity;
import com.example.zhang.mvp.contract.GlideContract;
import com.example.zhang.mvp.presenter.GlidePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author zzh
 */
public class GlideActivity extends BaseActivity<GlidePresenter> implements GlideContract.IGlideView {
    @BindView(R.id.iv_glide)
    ImageView ivGlide;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide);
        ButterKnife.bind(this);
        presenter = new GlidePresenter(this);
    }

    @OnClick(R.id.btn_glide_test)
    public void onClick() {
        String url = "http://www.wanandroid.com/blogimgs/50c115c2-cf6c-4802-aa7b-a4334de444cd.png";
        GlideApp.with(this).load(url).placeholder(R.mipmap.ic_launcher).circleCrop().into(ivGlide);
    }
}
