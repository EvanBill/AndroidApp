package com.example.zhang.mvp.ui;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.example.zhang.R;
import com.example.zhang.base.BaseActivity;
import com.example.zhang.mvp.contract.MainContract;
import com.example.zhang.mvp.model.bean.ProductBean;
import com.example.zhang.mvp.presenter.MainPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions
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

    }

    @OnClick({R.id.btn_main_click, R.id.btn_main_lifecycle})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_main_click:
//                testExample();
                MainActivityPermissionsDispatcher.requestPermissionWithPermissionCheck(this);
                break;
            case R.id.btn_main_lifecycle:
                Intent intent = new Intent(this, SettingActivity.class);
                startActivity(intent);

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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        MainActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
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
