package com.example.zhang.mvp.ui;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.blankj.utilcode.util.TimeUtils;
import com.example.zhang.R;
import com.example.zhang.base.BaseActivity;
import com.example.zhang.mvp.contract.MainContract;
import com.example.zhang.mvp.model.bean.ProductBean;
import com.example.zhang.mvp.presenter.MainPresenter;
import com.example.zhang.utils.LogUtils;
import com.example.zhang.utils.RxLifeCycleUtils;
import com.jakewharton.rxbinding2.view.RxView;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.IMainView {
    private static final String TAG = MainActivity.class.getSimpleName();
    @BindView(R.id.btn_main_click)
    Button btn_main_click;
    @BindView(R.id.tv_main_content)
    TextView tv_main_content;
    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        presenter = new MainPresenter(this);
        LogUtils.error(TAG, "MainActivity--:" + getExternalCacheDir().getAbsolutePath());
    }

    @OnClick({R.id.btn_main_click, R.id.btn_main_lifecycle})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_main_click:
//                testExample();
//                checkPermissions();
                checkPermissionRequest();
                break;
            case R.id.btn_main_lifecycle:
//                Intent intent = new Intent(this, RxLifeCycleActivity.class);
//                startActivity(intent);

                checkPermissionRequestEach();
                break;
        }
    }

    @Override
    public void showContent(List<ProductBean> productBeanList) {
        StringBuilder sb = new StringBuilder();
        sb.append("调用次数" + (++count) + "\n");
        for (ProductBean item : productBeanList) {
            sb.append(item.getName() + "\n");
        }
        tv_main_content.setText(sb.toString());
        count = 0;
    }

    private void testExample() {
//                presenter.getData();
//        presenter.rxJavaCreateExample();
//        presenter.rxJavaMapExample();
//        presenter.rxJavaZipExample();
//        presenter.rxJavaConcatExample();
//        presenter.rxJavaFlatMapExample();
//        presenter.rxJavaConcatMapExample();
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
//        presenter.getMainData(TimeUtils.getNowString(), 2, TimeUtils.getNowString());
//        presenter.postRegisterBy("zzz123456", "123456", "123456");
//        presenter.postRegister("zzz123456", "123456", "123456");
//        presenter.postLogin("zzz123456", "123456");
//        presenter.getUrlData("https://www.baidu.com");
//        presenter.postLoginAgain("zzz123456", "123456","evan");
    }

    /**
     * 检查权限
     */
    @SuppressLint("CheckResult")
    public void checkPermissions() {
        final RxPermissions permissions = new RxPermissions(this);
        permissions.setLogging(true);
        Observable.timer(100, TimeUnit.MILLISECONDS)
                .compose(permissions.ensureEach(Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_CALENDAR))
                .subscribe(new Consumer<Permission>() {
                    @Override
                    public void accept(Permission permission) throws Exception {
                        LogUtils.error(TAG, "checkPermissions--:" + "-permission-:" + permission.name + "---------------");
                        if (permission.name.equalsIgnoreCase(Manifest.permission.READ_EXTERNAL_STORAGE)) {
                            if (permission.granted) {
                                LogUtils.error(TAG, "checkPermissions--:" + "-READ_EXTERNAL_STORAGE-:" + true);
                            } else {
                                LogUtils.error(TAG, "checkPermissions--:" + "-READ_EXTERNAL_STORAGE-:" + false);
                            }
                        }
                        if (permission.name.equalsIgnoreCase(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                            if (permission.granted) {
                                LogUtils.error(TAG, "checkPermissions--:" + "-WRITE_EXTERNAL_STORAGE-:" + true);
                            } else {
                                LogUtils.error(TAG, "checkPermissions--:" + "-WRITE_EXTERNAL_STORAGE-:" + false);
                            }
                        }
                        if (permission.name.equalsIgnoreCase(Manifest.permission.READ_CALENDAR)) {
                            if (permission.granted) {
                                LogUtils.error(TAG, "checkPermissions--:" + "-READ_CALENDAR-:" + true);
                            } else {
                                LogUtils.error(TAG, "checkPermissions--:" + "-READ_CALENDAR-:" + false);
                            }
                        }
                    }
                });
    }

    /**
     * request(String arg...)统一请求权限，返回一个值
     * 当所有的权限都同意时返回true
     * 当有一个拒绝时返回false
     */

    @SuppressLint("CheckResult")
    public void checkPermissionRequest() {
        RxPermissions permissions = new RxPermissions(this);
        permissions.setLogging(true);
        permissions.request(Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_CALENDAR)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        LogUtils.error(TAG, "checkPermission22--:" + aBoolean);
                    }
                });

    }

    @SuppressLint("CheckResult")
    public void checkPermissionRequestEach() {
        RxPermissions permissions = new RxPermissions(this);
        permissions.setLogging(true);
        permissions.requestEach(Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_CALENDAR)
                .subscribe(new Consumer<Permission>() {
                    @Override
                    public void accept(Permission permission) throws Exception {
                        LogUtils.error(TAG, "checkPermissionRequestEach--:" + "-permission-:" + permission.name + "---------------");
                        if (permission.name.equalsIgnoreCase(Manifest.permission.READ_EXTERNAL_STORAGE)) {
                            if (permission.granted) {
                                LogUtils.error(TAG, "checkPermissionRequestEach--:" + "-READ_EXTERNAL_STORAGE-:" + true);
                            } else {
                                LogUtils.error(TAG, "checkPermissionRequestEach--:" + "-READ_EXTERNAL_STORAGE-:" + false);
                            }
                        }
                        if (permission.name.equalsIgnoreCase(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                            if (permission.granted) {
                                LogUtils.error(TAG, "checkPermissionRequestEach--:" + "-WRITE_EXTERNAL_STORAGE-:" + true);
                            } else {
                                LogUtils.error(TAG, "checkPermissionRequestEach--:" + "-WRITE_EXTERNAL_STORAGE-:" + false);
                            }
                        }
                        if (permission.name.equalsIgnoreCase(Manifest.permission.READ_CALENDAR)) {
                            if (permission.granted) {
                                LogUtils.error(TAG, "checkPermissionRequestEach--:" + "-READ_CALENDAR-:" + true);
                            } else {
                                LogUtils.error(TAG, "checkPermissionRequestEach--:" + "-READ_CALENDAR-:" + false);
                            }
                        }
                    }
                });
    }
}
