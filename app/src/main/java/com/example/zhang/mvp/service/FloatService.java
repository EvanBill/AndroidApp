package com.example.zhang.mvp.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.example.zhang.utils.LogUtils;

public class FloatService extends Service {
    private static final String TAT = FloatService.class.getSimpleName();

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtils.error(TAT, "onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        LogUtils.error(TAT, "onStartCommand");
        return START_REDELIVER_INTENT;

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtils.error(TAT, "onDestroy");
    }
}
