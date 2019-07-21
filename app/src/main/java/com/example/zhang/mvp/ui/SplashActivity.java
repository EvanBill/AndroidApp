package com.example.zhang.mvp.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.zhang.utils.IntentUtils;

/**
 * @author zzh
 */
public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        IntentUtils.startMainActivity(this);
        finish();

    }
}
