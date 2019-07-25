package com.example.zhang.utils;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;

import com.example.zhang.BuildConfig;
import com.example.zhang.R;
import com.example.zhang.app.AppApplication;

/**
 * 通知工具类
 */
public class NotificationUtils {
    private static volatile NotificationUtils instance;
    private static Context context = AppApplication.getInstance().getApplicationContext();
    /**
     * 通知id不一样时，在通知栏会增加一个，而不会更新
     */
    public static int NOTIFICATION_ID = 34;
    public static String channelId = BuildConfig.APPLICATION_ID;
    public static CharSequence channelName = "ZzhDemo";
    public static String channelDescription = "DefaultChannel";

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
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int channelImportance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel notificationChannel = new NotificationChannel(channelId, channelName, channelImportance);
            // 设置描述 最长30字符
            notificationChannel.setDescription(channelDescription);
            // 该渠道的通知是否使用震动
            notificationChannel.enableVibration(true);
            // 设置显示模式
            notificationChannel.setLockscreenVisibility(NotificationCompat.VISIBILITY_PUBLIC);
            // 开启指示灯，如果设备有的话
            notificationChannel.enableLights(true);
            // 设置指示灯颜色
            notificationChannel.setLightColor(ContextCompat.getColor(context, R.color.colorPrimary));
            // 是否在久按桌面图标时显示此渠道的通知
            notificationChannel.setShowBadge(true);
            // 设置是否应在锁定屏幕上显示此频道的通知
            notificationChannel.setLockscreenVisibility(NotificationCompat.VISIBILITY_PRIVATE);
            // 设置绕过免打扰模式
            notificationChannel.setBypassDnd(true);
            NotificationManager notificationManager =
                    (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(notificationChannel);

        }
    }

    /**
     * 创建默认渠道的通知
     *
     * @return
     */
    public Notification getDefaultChannelNotification() {
        return getDefaultChannelNotification(context.getResources().getString(R.string.str_notification_title),
                context.getResources().getString(R.string.str_notification_content_start));
    }

    /**
     * 创建默认渠道的通知
     *
     * @return
     */
    public Notification getDefaultChannelNotification(String contentText) {
        return getDefaultChannelNotification(context.getResources().getString(R.string.str_notification_title),
                contentText);
    }

    /**
     * 创建默认渠道的通知
     *
     * @return
     */
    public Notification getDefaultChannelNotification(String contentTitle, String contentText) {
        return getChannelNotification(channelId, contentTitle, contentText);
    }

    /**
     * 创建通知
     *
     * @return
     */
    public Notification getChannelNotification(String channelId, String contentTitle, String contentText) {
        return new NotificationCompat.Builder(context, channelId)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(contentTitle)
                .setContentText(contentText)
                .build();
    }

    /**
     * 显示通知
     *
     * @param id
     * @param notification
     */
    public void updateNotification(int id, Notification notification) {
        NotificationManagerCompat.from(context).notify(id, notification);
    }
}
