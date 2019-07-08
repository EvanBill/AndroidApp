package com.example.zhang.mvp.ui;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import com.example.zhang.GlideApp;
import com.example.zhang.R;
import com.example.zhang.base.BaseActivity;
import com.example.zhang.mvp.presenter.FileProviderPresenter;
import com.example.zhang.utils.FileProviderUtils;
import com.example.zhang.utils.LogUtils;
import com.example.zhang.utils.RxLifeCycleUtils;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;

public class FileProviderActivity extends BaseActivity<FileProviderPresenter> {
    @BindView(R.id.iv_file_provider)
    ImageView iv_file_provider;
    private File file;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_provider);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_file_provider})
    public void onClick(View view) {
        if (view.getId() == R.id.btn_file_provider) {
            cameraPicture(this);
        }

    }


    @SuppressLint("CheckResult")
    public void cameraPicture(FragmentActivity activity) {
        RxPermissions rxPermissions = new RxPermissions(activity);
        rxPermissions.requestEachCombined(Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA)
                .compose(RxLifeCycleUtils.bindToLifecycle(this))
                .subscribe(new Consumer<Permission>() {
                    @Override
                    public void accept(Permission permission) throws Exception {
                        LogUtils.error("ddd", "----rxPermissions-----:" + permission.granted);
                        if (permission.granted) {
                            cameraPic();
                        }
                    }
                });
    }

    public void cameraPic() {
        file = new File(Environment.getExternalStorageDirectory() + "/camera_photos/", System.currentTimeMillis() + ".jpg");
        if (!file.getParentFile().exists()) file.getParentFile().mkdirs();
        Uri imageUri = FileProviderUtils.getFileUri(this, file);
        LogUtils.error("ddd", imageUri.toString());
        Intent intent = new Intent();
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);//设置Action为拍照
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);//将拍取的照片保存到指定URI
        startActivityForResult(intent, 1006);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 1006) {
            if (file != null && file.exists()) {
                GlideApp.with(this).load(file).into(iv_file_provider);
            }
        }
    }
}
