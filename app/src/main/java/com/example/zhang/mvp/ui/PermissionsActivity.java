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
import com.example.zhang.mvp.contract.PermissionsContract;
import com.example.zhang.mvp.presenter.PermissionsPresenter;

import butterknife.ButterKnife;
import butterknife.OnClick;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions
public class PermissionsActivity extends BaseActivity<PermissionsPresenter> implements PermissionsContract.IPermissionsView {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permissions);
        presenter = new PermissionsPresenter(this);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_permissions_test)
    public void onClick() {
        PermissionsActivityPermissionsDispatcher.requestPermissionWithPermissionCheck(this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionsActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    /**
     * 只有全部成功时，才会调用
     */
    @NeedsPermission({Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_CALENDAR})
    public void requestPermission() {
        ToastUtils.showShort("申请权限成功");
    }

    /**
     * 只要有一个权限没有申请成功，且没有选择"以后不再询问"，则提示失败
     */
    @OnPermissionDenied({Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_CALENDAR})
    public void requestPermissionDenied() {
        ToastUtils.showShort("申请权限失败");
    }

    /**
     * 申请失败后，再申请则调用
     *
     * @param permissionRequest
     */
    @OnShowRationale({Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_CALENDAR})
    public void requestPermissionShowRationale(final PermissionRequest permissionRequest) {
        new AlertDialog.Builder(this).setMessage("申请读取存储权限")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {//确定后，则弹出申请权限的提示
                        permissionRequest.proceed();
                    }
                })
                .setNegativeButton("取笑", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {//取消后，调用requestPermissionDenied
                        permissionRequest.cancel();
                    }
                }).show();
    }

    /**
     * 只要有一个申请失败，且选择了"以后不再询问"，则提示
     */
    @OnNeverAskAgain({Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_CALENDAR})
    public void requestPermissionNeverAskAgain() {
        ToastUtils.showShort("申请权限失败，且不再提示");
    }

}
