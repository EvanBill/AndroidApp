package com.example.zhang.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;

import com.example.zhang.app.Constants;
import com.example.zhang.client.AidlClientActivity;
import com.example.zhang.mvp.service.FloatService;
import com.example.zhang.mvp.ui.CustomerFlowLayoutActivity;
import com.example.zhang.mvp.ui.EventBusActivity;
import com.example.zhang.mvp.ui.FileProviderActivity;
import com.example.zhang.mvp.ui.FrameAnimationActivity;
import com.example.zhang.mvp.ui.GlideActivity;
import com.example.zhang.mvp.ui.MainActivity;
import com.example.zhang.mvp.ui.PermissionsActivity;
import com.example.zhang.mvp.ui.RxJavaActivity;
import com.example.zhang.mvp.ui.RxLifeCycleActivity;
import com.example.zhang.mvp.ui.ServiceActivity;
import com.example.zhang.mvp.ui.SmartRefreshActivity;
import com.example.zhang.mvp.ui.TimeCountDownActivity;
import com.example.zhang.mvp.ui.TouchActivity;
import com.example.zhang.mvp.ui.VideoRecordingActivity;
import com.example.zhang.mvp.ui.WebViewActivity;

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
     * 开启FloatService
     *
     * @param context
     */
    public static void startFloatService(Context context) {
        Intent intent = new Intent(context, FloatService.class);
//        ContextCompat.startForegroundService(this,intent);
        intent.setAction(Constants.INTENT_ACTION_CREATE_NOTIFICATION);
        context.startService(intent);
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
