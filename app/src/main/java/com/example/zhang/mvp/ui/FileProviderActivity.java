package com.example.zhang.mvp.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;

import androidx.annotation.Nullable;

import com.blankj.utilcode.util.ToastUtils;
import com.example.zhang.R;
import com.example.zhang.base.BaseActivity;
import com.example.zhang.mvp.presenter.FileProviderPresenter;

import java.io.File;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class FileProviderActivity extends BaseActivity<FileProviderPresenter> {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_provider);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_file_provider})
    public void onClick(View view) {
        if (view.getId() == R.id.btn_file_provider) {
            cameraPic();
        }

    }

    public void cameraPic() {
//        android.os.FileUriExposedException: file:///storage/emulated/0/temp/1562320277379.jpg exposed beyond app through ClipData.Item.getUri()
        File file=new File(Environment.getExternalStorageDirectory(), "/temp/"+System.currentTimeMillis() + ".jpg");
        if (!file.getParentFile().exists())file.getParentFile().mkdirs();
        Uri imageUri = Uri.fromFile(file);
        Intent intent = new Intent();
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);//设置Action为拍照
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);//将拍取的照片保存到指定URI
        startActivityForResult(intent,1006);

    }
}
