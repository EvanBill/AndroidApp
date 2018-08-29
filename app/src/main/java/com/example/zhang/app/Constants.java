package com.example.zhang.app;

import com.blankj.utilcode.util.AppUtils;

import java.io.File;

public class Constants {
    public static  final  boolean ISLOG= true;
    public static final String PATH_DATA =
            AppApplication.getInstance().getCacheDir().getAbsolutePath() + File.separator + "data";
    public static final String PATH_CACHE = PATH_DATA + "/NetCache";

    public static final  String HOST = "http://www.wanandroid.com/";
}
