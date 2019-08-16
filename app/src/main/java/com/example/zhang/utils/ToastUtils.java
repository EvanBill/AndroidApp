package com.example.zhang.utils;

import android.content.Context;
import android.widget.Toast;

public class ToastUtils {
    /**
     * 展示toast
     * @param context 上下文
     * @param charSequence
     */
    public static void showToast(Context context, CharSequence charSequence) {
        Toast.makeText(context, charSequence, Toast.LENGTH_LONG).show();
    }

}
