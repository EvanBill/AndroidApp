package com.example.zhang.app;

import android.app.Application;

import com.example.zhang.utils.LogUtils;

public class AppApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        LogUtils.isLogging = true;
    }
}
