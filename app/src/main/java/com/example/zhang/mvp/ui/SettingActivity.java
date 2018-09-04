package com.example.zhang.mvp.ui;

import android.Manifest;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;

import com.blankj.utilcode.util.ToastUtils;
import com.example.zhang.R;
import com.example.zhang.base.BaseActivity;
import com.example.zhang.mvp.contract.SettingContract;
import com.example.zhang.mvp.presenter.SettingPresenter;

import butterknife.ButterKnife;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions
public class SettingActivity extends BaseActivity<SettingPresenter> implements SettingContract.ISettingView {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        presenter = new SettingPresenter(this);
        ButterKnife.bind(this);

//        SettingActivityPermissionsDispatcher.requestPermissionWithPermissionCheck(this);
    }

    @NeedsPermission({Manifest.permission.CAMERA, Manifest.permission.READ_CONTACTS})
    void requestPermission() {
        ToastUtils.showShort("申请成功");
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        SettingActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    @OnShowRationale({Manifest.permission.CAMERA, Manifest.permission.READ_CONTACTS})
    void requestPermissionShowRationale(final PermissionRequest request) {
        new AlertDialog.Builder(this).setMessage("申请权限")
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        request.cancel();
                    }
                }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                request.proceed();
            }
        }).show();
    }

    @OnPermissionDenied({Manifest.permission.CAMERA, Manifest.permission.READ_CONTACTS})
    void requestPermissionDenied() {
        ToastUtils.showShort("申请失败");
    }

    @OnNeverAskAgain({Manifest.permission.CAMERA, Manifest.permission.READ_CONTACTS})
    void requestPermissionAskAgain() {
        ToastUtils.showShort("申请失败，且不再询问");
    }
}
