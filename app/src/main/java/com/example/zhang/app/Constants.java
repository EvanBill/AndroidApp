package com.example.zhang.app;

import java.io.File;

/**
 * @author zzh
 */
public class Constants {
    public static final boolean IS_LOGIN = true;
    private static final String PATH_DATA =
            AppApplication.getInstance().getExternalCacheDir().getAbsolutePath() + File.separator + "data";
    public static final String PATH_CACHE = PATH_DATA + "/NetCache";
    public static final String CACHE_DIR_PATH = PATH_DATA + "/WebView";
    /**
     * http://www.wanandroid.com/
     */
    public static final String HOST = "http://baobab.kaiyanapp.com/api/";
}
