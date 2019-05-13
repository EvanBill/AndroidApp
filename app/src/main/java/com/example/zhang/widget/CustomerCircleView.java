package com.example.zhang.widget;

import android.content.Context;
import android.os.Build;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author zzh
 */
public class CustomerCircleView extends View {
    public CustomerCircleView(Context context) {
        super(context);
    }

    public CustomerCircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomerCircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CustomerCircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

}
