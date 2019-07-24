package com.example.zhang.utils;

import com.example.zhang.BuildConfig;

import leakcanary.LeakSentry;
import leakcanary.RefWatcher;

public class LeakCanaryUtil {
    /**
     * 获得检测对象工具
     */
    public static RefWatcher getRefWatcher() {
        return LeakSentry.INSTANCE.getRefWatcher();
    }

    /**
     * 设置默认配置--检测内存泄漏
     */
    public static void setLeakSentryConfig(boolean leakSentryConfigFlag) {
        LeakSentry.INSTANCE.getConfig().copy(leakSentryConfigFlag,leakSentryConfigFlag,leakSentryConfigFlag,leakSentryConfigFlag,100);
    }

    /**
     * 设置默认配置-检测堆信息
     */
    public static void setLeakCanaryConfig() {
//        LeakCanary.INSTANCE.getConfig().copy()
    }

}