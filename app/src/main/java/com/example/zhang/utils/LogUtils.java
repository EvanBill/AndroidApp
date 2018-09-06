package com.example.zhang.utils;

import android.util.Log;

import com.example.zhang.app.Constants;

public class LogUtils {
    public static void error(String tag, String msg) {
        if (Constants.ISLOGING) {
            Log.e(tag, msg);
        }
    }

    public static void warn(String tag, String msg) {
        if (Constants.ISLOGING) {
            Log.w(tag, msg);
        }
    }

    public static void debug(String tag, String msg) {
        if (Constants.ISLOGING) {
            Log.d(tag, msg);
        }
    }

    public static void info(String tag, String msg) {
        if (Constants.ISLOGING) {
            Log.i(tag, msg);
        }
    }
}
