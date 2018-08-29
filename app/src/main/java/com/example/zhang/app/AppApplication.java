package com.example.zhang.app;

import android.app.Application;

import com.blankj.utilcode.util.Utils;
import com.example.zhang.utils.LogUtils;

public class AppApplication extends Application {
    private static AppApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        LogUtils.isLogging = true;
        Utils.init(this);//AndroidUtils初始化
    }

    public static AppApplication getInstance() {
        return instance;
    }
}
