package com.example.zhang.base;

import android.os.Bundle;
import androidx.annotation.Nullable;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

/**
 * @author zzh
 */
public abstract class BaseActivity<T> extends RxAppCompatActivity implements BaseView {
    protected T presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
