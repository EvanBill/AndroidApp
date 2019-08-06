package com.example.zhang.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;

import androidx.core.content.ContextCompat;

import com.example.zhang.app.Constants;
import com.example.zhang.client.AidlClientActivity;
import com.example.zhang.mvp.service.FloatService;
import com.example.zhang.mvp.ui.activity.CustomerFlowLayoutActivity;
import com.example.zhang.mvp.ui.activity.EventBusActivity;
import com.example.zhang.mvp.ui.activity.FileProviderActivity;
import com.example.zhang.mvp.ui.activity.FrameAnimationActivity;
import com.example.zhang.mvp.ui.activity.GlideActivity;
import com.example.zhang.mvp.ui.MainActivity;
import com.example.zhang.mvp.ui.activity.PermissionsActivity;
import com.example.zhang.mvp.ui.activity.RippleActivity;
import com.example.zhang.mvp.ui.activity.RxJavaActivity;
import com.example.zhang.mvp.ui.activity.RxLifeCycleActivity;
import com.example.zhang.mvp.ui.activity.ServiceActivity;
import com.example.zhang.mvp.ui.activity.SmartRefreshActivity;
import com.example.zhang.mvp.ui.activity.TimeCountDownActivity;
import com.example.zhang.mvp.ui.activity.TouchActivity;
import com.example.zhang.mvp.ui.activity.VideoRecordingActivity;
import com.example.zhang.mvp.ui.activity.WebViewActivity;

/**
 * Intent开始工具类
 */
public class IntentUtils {

    /**
     * MainActivity
     *
     * @param context
     */
    public static void startMainActivity(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    /**
     * RxJavaActivity
     *
     * @param context
     */
    public static void startRxJavaActivity(Context context) {
        Intent intentRxJava = new Intent(context, RxJavaActivity.class);
        context.startActivity(intentRxJava);
    }

    /**
     * RxLifeCycleActivity
     *
     * @param context
     */
    public static void startRxLifeCycleActivity(Context context) {
        Intent intentLifecycle = new Intent(context, RxLifeCycleActivity.class);
        context.startActivity(intentLifecycle);
    }

    /**
     * PermissionsActivity
     *
     * @param context
     */
    public static void startPermissionsActivity(Context context) {
        Intent intentPermissions = new Intent(context, PermissionsActivity.class);
        context.startActivity(intentPermissions);
    }

    /**
     * GlideActivity
     *
     * @param context
     */
    public static void startGlideActivity(Context context) {
        Intent intentGlide = new Intent(context, GlideActivity.class);
        context.startActivity(intentGlide);
    }

    /**
     * SmartRefreshActivity
     *
     * @param context
     */
    public static void startSmartRefreshActivity(Context context) {
        Intent intentSmartRefresh = new Intent(context, SmartRefreshActivity.class);
        context.startActivity(intentSmartRefresh);
    }

    /**
     * WebViewActivity
     *
     * @param context
     */
    public static void startWebViewActivity(Context context) {
        Intent intentWebView = new Intent(context, WebViewActivity.class);
        context.startActivity(intentWebView);
    }

    /**
     * TouchActivity
     *
     * @param context
     */
    public static void startTouchActivity(Context context) {
        Intent intentTouch = new Intent(context, TouchActivity.class);
        context.startActivity(intentTouch);
    }

    /**
     * AidlClientActivity
     *
     * @param context
     */
    public static void startAidlClientActivity(Context context) {
        Intent btnMainAidlClient = new Intent(context, AidlClientActivity.class);
        context.startActivity(btnMainAidlClient);
    }

    /**
     * CustomerFlowLayoutActivity
     *
     * @param context
     */
    public static void startCustomerFlowLayoutActivity(Context context) {
        Intent btnMainCustomerFlowLayout = new Intent(context, CustomerFlowLayoutActivity.class);
        context.startActivity(btnMainCustomerFlowLayout);
    }

    /**
     * FrameAnimationActivity
     *
     * @param context
     */
    public static void startFrameAnimationActivity(Context context) {
        Intent frameAnimationIntent = new Intent(context, FrameAnimationActivity.class);
        context.startActivity(frameAnimationIntent);
    }

    /**
     * TimeCountDownActivity
     *
     * @param context
     */
    public static void startTimeCountDownActivity(Context context) {
        Intent timeCountDownIntent = new Intent(context, TimeCountDownActivity.class);
        context.startActivity(timeCountDownIntent);
    }

    /**
     * VideoRecordingActivity
     *
     * @param context
     */
    public static void startVideoRecordingActivity(Context context) {
        Intent videoRecordingIntent = new Intent(context, VideoRecordingActivity.class);
        context.startActivity(videoRecordingIntent);
    }

    /**
     * EventBusActivity
     *
     * @param context
     */
    public static void startEventBusActivity(Context context) {
        Intent eventBusIntent = new Intent(context, EventBusActivity.class);
        context.startActivity(eventBusIntent);
    }

    /**
     * FileProviderActivity
     *
     * @param context
     */
    public static void startFileProviderActivity(Context context) {
        Intent fileProviderIntent = new Intent(context, FileProviderActivity.class);
        context.startActivity(fileProviderIntent);
    }

    /**
     * 开启ServiceActivity
     *
     * @param context
     */
    public static void startServiceActivity(Context context) {
        Intent serviceIntent = new Intent(context, ServiceActivity.class);
        context.startActivity(serviceIntent);
    }
    /**
     * 开启SRippleActivity
     *
     * @param context
     */
    public static void startRippleActivity(Context context) {
        Intent serviceIntent = new Intent(context, RippleActivity.class);
        context.startActivity(serviceIntent);
    }
    /**
     * 开启FloatService 并创建notification
     *
     * @param context
     */
    public static void startFloatService(Context context) {
        Intent intent = new Intent(context, FloatService.class);
        intent.setAction(Constants.INTENT_ACTION_NOTIFICATION_CREATE);
        ContextCompat.startForegroundService(context, intent);
//        context.startService(intent);
    }

    /**
     * 更新通知
     *
     * @param context
     */
    public static void startFloatServiceForUpdateNotification(Context context) {
        Intent intent = new Intent(context, FloatService.class);
        intent.setAction(Constants.INTENT_ACTION_NOTIFICATION_UPDATE);
        ContextCompat.startForegroundService(context, intent);
    }


    /**
     * 开启系统照相机
     *
     * @param activity
     */
    public static void startSystemCameraForResult(Activity activity, Uri uri, int requestCode) {
        Intent intent = new Intent();
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);//设置Action为拍照
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);//将拍取的照片保存到指定URI
        activity.startActivityForResult(intent, requestCode);
    }
}
