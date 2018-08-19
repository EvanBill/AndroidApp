package com.example.zhang.app;

import android.app.Application;

import com.blankj.utilcode.util.Utils;
import com.example.zhang.utils.LogUtils;

public class AppApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        LogUtils.isLogging = true;
        Utils.init(this);//AndroidUtils初始化
    }
}
