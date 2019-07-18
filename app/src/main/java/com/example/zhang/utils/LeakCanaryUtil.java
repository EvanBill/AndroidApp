package com.example.zhang.utils;

import com.example.zhang.mvp.ui.MainActivity;

import leakcanary.LeakSentry;
import leakcanary.RefWatcher;

public class LeakCanaryUtil {
    /**
     * 获得检测对象工具
     */
    public static RefWatcher getRefWatcher() {
        return LeakSentry.INSTANCE.getRefWatcher();
    }

    public static void setLeakCanary() {
//        LeakSentry.config = LeakSentry.config.copy(watchFragmentViews = false)
        LeakSentry.INSTANCE.getConfig().copy(true, true, true, true, 100);
    }
}
