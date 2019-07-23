package com.example.zhang.utils;

import android.app.Notification;
import android.content.Context;

import androidx.core.app.NotificationCompat;

/**
 * 通知工具类
 */
public class NotificationUtils {
    /**
     * 创建普通的通知
     *
     * @param context
     * @param channelId
     * @return
     */
    public static Notification getCommonNotification(Context context, String channelId) {
        Notification notification = new NotificationCompat.Builder(context, channelId)
                .setContentTitle("111").setContentText("222").setContentInfo("333").build();
        return notification;
    }
}
