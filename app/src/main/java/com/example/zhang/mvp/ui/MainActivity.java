package com.example.zhang.mvp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.blankj.utilcode.constant.TimeConstants;
import com.blankj.utilcode.util.TimeUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.example.zhang.R;
import com.example.zhang.base.BaseActivity;
import com.example.zhang.mvp.contract.MainContract;
import com.example.zhang.mvp.model.bean.ProductBean;
import com.example.zhang.mvp.presenter.MainPresenter;

import java.sql.Time;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;


public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.IMainView {
    private long backTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        presenter = new MainPresenter(this);

    }

    @OnClick({R.id.btn_main_rxjava, R.id.btn_main_lifecycle, R.id.btn_main_permissions, R.id.btn_main_glide
            , R.id.btn_main_smart_refresh})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_main_rxjava:
                Intent intent_rxjava = new Intent(this, RxJavaActivity.class);
                startActivity(intent_rxjava);
                break;
            case R.id.btn_main_lifecycle:
                Intent intent_lifecycle = new Intent(this, RxLifeCycleActivity.class);
                startActivity(intent_lifecycle);
                break;
            case R.id.btn_main_permissions:
                Intent intent_permissions = new Intent(this, PermissionsActivity.class);
                startActivity(intent_permissions);
                break;
            case R.id.btn_main_glide:
                Intent intent_glide = new Intent(this, GlideActivity.class);
                startActivity(intent_glide);
                break;
            case R.id.btn_main_smart_refresh:
                Intent intent_smart_refresh = new Intent(this, SmartRefreshActivity.class);
                startActivity(intent_smart_refresh);
                break;
        }
    }

    @Override
    public void showContent(List<ProductBean> productBeanList) {

    }

    @Override
    public void onBackPressed() {
        if (TimeUtils.getTimeSpan(TimeUtils.getNowMills(), backTime, TimeConstants.SEC) > 2) {
            backTime = TimeUtils.getNowMills();
            ToastUtils.showShort(getString(R.string.str_back_press_info));
        } else {
            super.onBackPressed();
        }
    }
}
