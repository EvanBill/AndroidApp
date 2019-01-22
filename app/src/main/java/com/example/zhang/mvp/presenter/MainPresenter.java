package com.example.zhang.mvp.presenter;

import android.Manifest;
import android.annotation.SuppressLint;
import android.support.v4.app.FragmentActivity;

import com.example.zhang.base.BasePresenter;
import com.example.zhang.mvp.contract.MainContract;
import com.example.zhang.mvp.model.MainModel;
import com.example.zhang.mvp.model.bean.ProductBean;
import com.example.zhang.utils.LogUtils;
import com.example.zhang.utils.RxLifeCycleUtils;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.trello.rxlifecycle2.android.ActivityEvent;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainPresenter extends BasePresenter<MainContract.IMainView, MainModel> {
    private String TAG = MainPresenter.class.getSimpleName();


    public MainPresenter(MainContract.IMainView view) {
        super(view);
        model = new MainModel();
    }



    @SuppressLint("CheckResult")
    public void getData() {
        model.getProductData()
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(RxLifeCycleUtils.<List<ProductBean>>bindUntilEvent(view, ActivityEvent.DESTROY))
                .subscribe(new Consumer<List<ProductBean>>() {
                    @Override
                    public void accept(List<ProductBean> productBeanList) {
                        view.showContent(productBeanList);
                    }
                });

    }


    /**
     * 申请权限
     * request(String arg...)统一请求权限，返回一个值
     * 当所有的权限都同意时返回true
     * 当有一个拒绝时返回false
     */

    @SuppressLint("CheckResult")
    public void checkPermissionsRequest(FragmentActivity activity) {
        RxPermissions permissions = new RxPermissions(activity);
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

    /**
     * 申请权限
     */
    @SuppressLint("CheckResult")
    public void checkPermissionsRequestEach(FragmentActivity activity) {
        RxPermissions permissions = new RxPermissions(activity);
        permissions.setLogging(true);
        permissions.requestEach(Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_CALENDAR)
                .subscribe(new Consumer<Permission>() {
                    @Override
                    public void accept(Permission permission) throws Exception {
                        LogUtils.error(TAG, "checkPermissionRequestEach--:" + "-permission-:" + permission.name + "---------------");
                        if (permission.name.equalsIgnoreCase(Manifest.permission.READ_EXTERNAL_STORAGE)) {
                            if (permission.granted) {//同意后调用
                                LogUtils.error(TAG, "checkPermissionRequestEach--:" + "-READ_EXTERNAL_STORAGE-:" + true);
                            } else if (permission.shouldShowRequestPermissionRationale) {//禁止，但没有选择“以后不再询问”，以后申请权限，会继续弹出提示
                                LogUtils.error(TAG, "checkPermissionRequestEach--:" + "-READ_EXTERNAL_STORAGE-shouldShowRequestPermissionRationale:" + false);
                            } else {//禁止，但选择“以后不再询问”，以后申请权限，不会继续弹出提示
                                LogUtils.error(TAG, "checkPermissionRequestEach--:" + "-READ_EXTERNAL_STORAGE-:" + false);
                            }
                        }
                        if (permission.name.equalsIgnoreCase(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                            if (permission.granted) {
                                LogUtils.error(TAG, "checkPermissionRequestEach--:" + "-WRITE_EXTERNAL_STORAGE-:" + true);
                            } else if (permission.shouldShowRequestPermissionRationale) {
                                LogUtils.error(TAG, "checkPermissionRequestEach--:" + "-READ_EXTERNAL_STORAGE-shouldShowRequestPermissionRationale:" + false);
                            } else {
                                LogUtils.error(TAG, "checkPermissionRequestEach--:" + "-WRITE_EXTERNAL_STORAGE-:" + false);
                            }
                        }
                        if (permission.name.equalsIgnoreCase(Manifest.permission.READ_CALENDAR)) {
                            if (permission.granted) {
                                LogUtils.error(TAG, "checkPermissionRequestEach--:" + "-READ_CALENDAR-:" + true);
                            } else if (permission.shouldShowRequestPermissionRationale) {
                                LogUtils.error(TAG, "checkPermissionRequestEach--:" + "-READ_EXTERNAL_STORAGE-shouldShowRequestPermissionRationale:" + false);
                            } else {
                                LogUtils.error(TAG, "checkPermissionRequestEach--:" + "-READ_CALENDAR-:" + false);
                            }
                        }
                    }
                });
    }

    @SuppressLint("CheckResult")
    public void checkPermissionsRequestEachCombined(FragmentActivity activity) {
        RxPermissions permissions = new RxPermissions(activity);
        permissions.setLogging(true);
        permissions.requestEachCombined(Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_CALENDAR)
                .subscribe(new Consumer<Permission>() {
                    @Override
                    public void accept(Permission permission) throws Exception {
                        LogUtils.error(TAG, "checkPermissionRequestEachCombined--:" + "-permission-:" + permission.name + "---------------");
                        if (permission.granted) {//全部同意后调用
                            LogUtils.error(TAG, "checkPermissionRequestEachCombined--:" + "-READ_EXTERNAL_STORAGE-:" + true);
                        } else if (permission.shouldShowRequestPermissionRationale) {//只要有一个选择：禁止，但没有选择“以后不再询问”，以后申请权限，会继续弹出提示
                            LogUtils.error(TAG, "checkPermissionRequestEachCombined--:" + "-READ_EXTERNAL_STORAGE-shouldShowRequestPermissionRationale:" + false);
                        } else {//只要有一个选择：禁止，但选择“以后不再询问”，以后申请权限，不会继续弹出提示
                            LogUtils.error(TAG, "checkPermissionRequestEachCombined--:" + "-READ_EXTERNAL_STORAGE-:" + false);
                        }
                    }
                });
    }

    @SuppressLint("CheckResult")
    public void checkPermissionsEnsure(FragmentActivity activity) {
        final RxPermissions permissions = new RxPermissions(activity);
        permissions.setLogging(true);
        Observable.timer(100, TimeUnit.MILLISECONDS)
                .compose(permissions.ensure(Manifest.permission.READ_EXTERNAL_STORAGE))
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        LogUtils.error(TAG, "checkPermissionsEnsure--:" + "-READ_EXTERNAL_STORAGE-:" + aBoolean);
                    }
                });
    }

    /**
     * 检查权限
     */
    @SuppressLint("CheckResult")
    public void checkPermissionsEnsureEach(FragmentActivity activity) {
        final RxPermissions permissions = new RxPermissions(activity);
        permissions.setLogging(true);
        Observable.timer(100, TimeUnit.MILLISECONDS)
                .compose(permissions.ensureEach(Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_CALENDAR))
                .subscribe(new Consumer<Permission>() {
                    @Override
                    public void accept(Permission permission) throws Exception {
                        LogUtils.error(TAG, "checkPermissionsEnsureEach--:" + "-permission-:" + permission.name + "---------------");
                        if (permission.name.equalsIgnoreCase(Manifest.permission.READ_EXTERNAL_STORAGE)) {
                            if (permission.granted) {
                                LogUtils.error(TAG, "checkPermissionsEnsureEach--:" + "-READ_EXTERNAL_STORAGE-:" + true);
                            } else {
                                LogUtils.error(TAG, "checkPermissionsEnsureEach--:" + "-READ_EXTERNAL_STORAGE-:" + false);
                            }
                        }
                        if (permission.name.equalsIgnoreCase(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                            if (permission.granted) {
                                LogUtils.error(TAG, "checkPermissionsEnsureEach--:" + "-WRITE_EXTERNAL_STORAGE-:" + true);
                            } else {
                                LogUtils.error(TAG, "checkPermissionsEnsureEach--:" + "-WRITE_EXTERNAL_STORAGE-:" + false);
                            }
                        }
                        if (permission.name.equalsIgnoreCase(Manifest.permission.READ_CALENDAR)) {
                            if (permission.granted) {
                                LogUtils.error(TAG, "checkPermissionsEnsureEach--:" + "-READ_CALENDAR-:" + true);
                            } else {
                                LogUtils.error(TAG, "checkPermissionsEnsureEach--:" + "-READ_CALENDAR-:" + false);
                            }
                        }
                    }
                });
    }

    /**
     * 检查某个权限是否被申请
     *
     * @param activity
     */
    public void checkPermissionsIsGranted(FragmentActivity activity) {
        RxPermissions permissions = new RxPermissions(activity);
        permissions.setLogging(true);
        LogUtils.error(TAG, "checkPermissionsIsGranted--:" + "-WRITE_EXTERNAL_STORAGE-:" + permissions.isGranted(Manifest.permission.WRITE_EXTERNAL_STORAGE));
    }


}
