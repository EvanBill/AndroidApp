package com.example.zhang.app;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.multidex.MultiDex;

import com.blankj.utilcode.util.Utils;
import com.example.zhang.R;
import com.facebook.stetho.Stetho;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;
import com.tencent.bugly.Bugly;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author zzh
 */

public class AppApplication extends Application {
    private static AppApplication instance;
    private RefWatcher refWatcher;

    //static 代码段可以防止内存泄露
    static {
        //设置全局的Header构建器

        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {

            @NonNull
            @Override
            public RefreshHeader createRefreshHeader(@NonNull Context context, @NonNull RefreshLayout layout) {
                //全局设置主题颜色
                layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white);
                //指定为经典Header，默认是 贝塞尔雷达Header
//                return new ClassicsHeader(context)
//                        .setTimeFormat(new DynamicTimeFormat("更新于 %s"));
//                        .setTimeFormat(new SimpleDateFormat("上次更新 yyyy-MM-dd HH:mm:ss", Locale.getDefault()));
                return new MaterialHeader(instance);
            }
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
            @NonNull
            @Override
            public RefreshFooter createRefreshFooter(@NonNull Context context, @NonNull RefreshLayout layout) {
                //指定为经典Footer，默认是 BallPulseFooter
                return new ClassicsFooter(context).setDrawableSize(20);
            }
        });
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        //AndroidUtils初始化
        Utils.init(this);
        Stetho.initializeWithDefaults(this);
        //测试阶段建议设置成true，发布时设置为false
        Bugly.init(instance, "9ebbf090a8", false);
        closeAndroidPDialog();
        refWatcher = setUpLeakCanary();
    }

    public static AppApplication getInstance() {
        return instance;
    }

    /**
     * 在MIUI 10升级到 Android P 后 每次进入程序都会弹一个提醒弹窗
     */
    @SuppressWarnings("unchecked")
    private void closeAndroidPDialog() {
        try {
            @SuppressLint("PrivateApi") Class aClass = Class.forName("android.content.pm.PackageParser$Package");
            Constructor declaredConstructor = aClass.getDeclaredConstructor(String.class);
            declaredConstructor.setAccessible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            @SuppressLint("PrivateApi") Class cls = Class.forName("android.app.ActivityThread");
            Method declaredMethod = cls.getDeclaredMethod("currentActivityThread");
            declaredMethod.setAccessible(true);
            Object activityThread = declaredMethod.invoke(null);
            Field mHiddenApiWarningShown = cls.getDeclaredField("mHiddenApiWarningShown");
            mHiddenApiWarningShown.setAccessible(true);
            mHiddenApiWarningShown.setBoolean(activityThread, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private RefWatcher setUpLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return RefWatcher.DISABLED;
        }
        return LeakCanary.install(this);
    }

    /**
     * 获得检测对象工具
     *
     * @param context 上下文
     */
    public static RefWatcher getRefWatcher(Context context) {
        AppApplication leakApplication = (AppApplication) context.getApplicationContext();
        return leakApplication.refWatcher;
    }

}
