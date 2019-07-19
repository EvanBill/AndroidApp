package com.example.zhang.utils;

import android.content.Context;
import android.content.Intent;

import com.example.zhang.mvp.service.FloatService;

public class IntentUtils {
    /**
     * 开启FloatService
     *
     * @param context
     */
    public static void startFloatService(Context context) {
        Intent intent = new Intent(context, FloatService.class);
//        ContextCompat.startForegroundService(this,intent);
        context.startService(intent);
    }
}
