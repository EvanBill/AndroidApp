package com.example.zhang.mvp.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.zhang.R;
import com.example.zhang.base.BaseActivity;
import com.example.zhang.mvp.contract.RxJavaContract;
import com.example.zhang.mvp.presenter.RxJavaPresenter;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class RxJavaActivity extends BaseActivity<RxJavaPresenter> implements RxJavaContract.IRxJavaView {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava);
        presenter = new RxJavaPresenter(this);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_rxjava_test)
    public void onClick() {
        testExample();
    }

    private void testExample() {
//        presenter.rxJavaCreateExample();
//        presenter.rxJavaMapExample();
//        presenter.rxJavaZipExample();
//        presenter.rxJavaConcatExample();
//        presenter.rxJavaFlatMapExample();
        presenter.rxJavaConcatMapExample();
//        presenter.rxJavaDistinctExample();
//        presenter.rxJavaFilterExample();
//        presenter.rxJavaBufferExample();
//        presenter.rxJavaTimeExample();
//        presenter.rxJavaIntervalExample();
//        presenter.rxJavaDoOnNextExample();
//        presenter.rxJavaSkipExample();
//        presenter.rxJavaTakeExample();
//        presenter.rxJavaSingleExample();
//        presenter.rxJavaDebounceExample();
//        presenter.rxJavaDeferExample();
//        presenter.rxJavaLastExample();
//        presenter.rxJavaMergeExample();
//        presenter.rxJavaReduceExample();
//        presenter.rxJavaScanExample();
//        presenter.rxJavaWindowExample();
//        presenter.rxJavaRangeExample();
//        presenter.rxJavaRepeatExample();
//        presenter.rxJavaSchedulersExample();
//        presenter.rxJavaFlowableCreateExample();
//        presenter.rxJavaFlowableSizeExample();
//        presenter.rxJavaFlowableRealExample();
//        presenter.rxJavaFlowableConsumeExample();
//        presenter.getMainData(TimeUtils.getNowString(), 2);
//        presenter.postRegisterBy("zzz123456", "123456", "123456");
//        presenter.postRegister("zzz123456", "123456", "123456");
//        presenter.postLogin("zzz123456", "123456");
//        presenter.getUrlData("http://www.wanandroid.com/hotkey/json");
//        presenter.getBanner(TimeUtils.getNowString(), TimeUtils.getNowString());
//        presenter.postLoginAgain("zzz123456", "123456","evan");
    }

}
