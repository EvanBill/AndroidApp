package com.example.zhang.app;

import android.app.Application;

import com.blankj.utilcode.util.Utils;
import com.facebook.stetho.Stetho;

public class AppApplication extends Application {
    private static AppApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        Utils.init(this);//AndroidUtils初始化
        Stetho.initializeWithDefaults(this);
    }

    public static AppApplication getInstance() {
        return instance;
    }
}
