package com.example.zhang.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

public class DevicesUtils {
    public volatile static DevicesUtils instance;
    public static DisplayMetrics metrics;

    private DevicesUtils(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        if (windowManager != null) {
            metrics = new DisplayMetrics();
            windowManager.getDefaultDisplay().getMetrics(metrics);

        }
    }

    public static DevicesUtils getInstance(Context context) {
        if (instance == null) {
            synchronized (DevicesUtils.class) {
                if (instance == null) {
                    instance = new DevicesUtils(context);
                }
            }
        }
        return instance;
    }

    /**
     * 获得屏幕宽度
     *
     * @return
     */
    public  int getWidth() {
        int result = 0;
        if (metrics != null) {
            result = metrics.widthPixels;
        }
        return result;
    }

    /**
     * 获得屏幕高度
     *
     * @return
     */
    public static int getHeight() {
        int result = 0;
        if (metrics != null) {
            result = metrics.heightPixels;
        }
        return result;
    }

    /**
     * 获得显示器的逻辑密度
     *
     * @return
     */
    public static float getDensity() {
        float result = 0;
        if (metrics != null) {
            result = metrics.density;
        }
        return result;
    }

    /**
     * 获得屏幕密度表示为每英寸点数。
     *
     * @return
     */
    public static int getDensityDpi() {
        int result = 0;
        if (metrics != null) {
            result = metrics.densityDpi;
        }
        return result;
    }

    /**
     * 获得显示屏上显示的字体缩放系数
     *
     * @return
     */
    public static float getScaleDensity() {
        float result = 0;
        if (metrics != null) {
            result = metrics.scaledDensity;
        }
        return result;
    }
}
