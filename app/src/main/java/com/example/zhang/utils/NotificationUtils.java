package com.example.zhang.utils;

import android.app.Notification;
import android.content.Context;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.zhang.app.AppApplication;

/**
 * 通知工具类
 */
public class NotificationUtils {
    private static volatile NotificationUtils instance;
    public static int NOTIFICATION_ID=34;
    private static Context context = AppApplication.getInstance().getApplicationContext();
    private NotificationManagerCompat notificationManagerCompat;

    private NotificationUtils() {
        createNotificationChannel();
    }

    public static NotificationUtils getInstance() {
        if (instance == null) {
            synchronized (NotificationUtils.class) {
                if (instance == null) {
                    instance = new NotificationUtils();
                }
            }
        }
        return instance;
    }

    /**
     * 创建通知渠道
     */
    private void createNotificationChannel() {
        notificationManagerCompat = NotificationManagerCompat.from(context);
    }

    /**
     * 创建普通的通知
     *
     * @param channelId
     * @return
     */
    public  Notification getCommonNotification(String channelId) {
        Notification notification = new NotificationCompat.Builder(context, channelId)
                .setContentTitle("111").setContentText("222").setContentInfo("333").build();
        return notification;
    }
}
