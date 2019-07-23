package com.example.zhang.mvp.service;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.text.TextUtils;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.zhang.app.Constants;
import com.example.zhang.utils.LogUtils;
import com.example.zhang.utils.NotificationUtils;

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
        if (intent != null) {
            String action = intent.getAction();
            if (!TextUtils.isEmpty(action)) {
                switch (action) {
                    case Constants.INTENT_ACTION_CREATE_NOTIFICATION:
                        Notification channelId_1 = NotificationUtils.getInstance().getCommonNotification("channelId_1");
                        this.startForeground(NotificationUtils.NOTIFICATION_ID, channelId_1);
                        break;
                }
            }
        }
        return START_REDELIVER_INTENT;

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtils.error(TAT, "onDestroy");
    }
}
