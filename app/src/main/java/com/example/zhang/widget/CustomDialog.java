package com.example.zhang.widget;

import android.app.Application;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.zhang.R;
import com.example.zhang.app.AppApplication;
import com.example.zhang.utils.DensityUtil;
import com.example.zhang.utils.DevicesUtils;
import com.example.zhang.utils.LogUtils;

import java.util.Objects;

/**
 * 自定义对话框
 */
public class CustomDialog extends Dialog {
    /**
     * 设置对话框布局
     *
     * @param context 上下文
     */
    public CustomDialog(@NonNull Context context) {
        super(context);
    }

    /**
     * 设置对话框布局
     *
     * @param context    上下文
     * @param themeResId 布局id
     */
    public CustomDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    /**
     * 设置对话框布局
     *
     * @param context        上下文
     * @param cancelable     能否取消
     * @param cancelListener 取消后监听
     */
    protected CustomDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    public void setContentView(@NonNull View view) {
        super.setContentView(view);
        resetDialogWith(this);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        resetDialogWith(this);
    }

    @Override
    public void setContentView(@NonNull View view, @Nullable ViewGroup.LayoutParams params) {
        super.setContentView(view, params);
        resetDialogWith(this);
    }

    /**
     * 设置对话框布局
     *
     * @param view   view
     * @param margin 对话框距离屏幕的宽度 单位dp
     */
    public void setContentView(@NonNull View view, int margin) {
        super.setContentView(view);
        resetDialogWith(this, margin);
    }

    /**
     * 设置对话框布局
     *
     * @param layoutResID 布局id
     * @param margin      对话框距离屏幕的宽度 单位dp
     */
    public void setContentView(int layoutResID, int margin) {
        super.setContentView(layoutResID);
        resetDialogWith(this, margin);
    }

    /**
     * 设置对话框布局
     *
     * @param view   布局
     * @param params 布局参数
     * @param margin 对话框距离屏幕的宽度 单位dp
     */
    public void setContentView(@NonNull View view, @Nullable ViewGroup.LayoutParams params, int margin) {
        super.setContentView(view, params);
        resetDialogWith(this, margin);
    }

    /**
     * 设置默认对话框距离屏幕宽度
     *
     * @param dialog 对话框
     */
    private void resetDialogWith(Dialog dialog) {
        resetDialogWith(this, dialog.getContext().getResources().getDimension(R.dimen.dp_dialog_margin));
    }

    /**
     * 自定义对话框距离屏幕宽度
     *
     * @param dialog 对话框
     * @param margin 单位dp
     */
    private void resetDialogWith(Dialog dialog, float margin) {
        int dialogWith = DevicesUtils.getInstance(AppApplication.getInstance()).getWidth() - 2 * DensityUtil.dp2px(margin);
        WindowManager.LayoutParams lp = Objects.requireNonNull(dialog.getWindow()).getAttributes();
        lp.width = dialogWith;
        dialog.getWindow().setAttributes(lp);
    }
}

