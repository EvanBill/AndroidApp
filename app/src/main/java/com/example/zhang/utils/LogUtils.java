package com.example.zhang.utils;

import android.util.Log;

import com.example.zhang.app.Constants;
/**
 * @author zzh
 */
public class LogUtils {
    public static void error(String tag, String msg) {
        if (Constants.IS_LOGIN) {
            Log.e(tag, msg);
        }
    }

    public static void warn(String tag, String msg) {
        if (Constants.IS_LOGIN) {
            Log.w(tag, msg);
        }
    }

    public static void debug(String tag, String msg) {
        if (Constants.IS_LOGIN) {
            Log.d(tag, msg);
        }
    }

    public static void info(String tag, String msg) {
        if (Constants.IS_LOGIN) {
            Log.i(tag, msg);
        }
    }
}
