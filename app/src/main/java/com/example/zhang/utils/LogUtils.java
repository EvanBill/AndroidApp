package com.example.zhang.utils;

import android.util.Log;

public class LogUtils {
    public static boolean isLogging = false;

    public static void error(String tag, String msg) {
        if (isLogging) {
            Log.e(tag, msg);
        }
    }

    public static void warn(String tag, String msg) {
        if (isLogging) {
            Log.w(tag, msg);
        }
    }

    public static void debug(String tag, String msg) {
        if (isLogging) {
            Log.d(tag, msg);
        }
    }

    public static void info(String tag, String msg) {
        if (isLogging) {
            Log.i(tag, msg);
        }
    }
}
