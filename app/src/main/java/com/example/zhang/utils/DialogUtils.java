package com.example.zhang.utils;

import android.app.Activity;
import android.app.Dialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;

import com.example.zhang.R;
import com.example.zhang.widget.CustomDialog;

public class DialogUtils {
    public volatile static DialogUtils instance;

    private DialogUtils() {
    }

    public static DialogUtils getInstance() {
        if (instance == null) {
            synchronized (DialogUtils.class) {
                if (instance == null) {
                    instance = new DialogUtils();
                }
            }
        }
        return instance;
    }

    /**
     * 展示反馈对话框
     *
     * @param activity activity
     * @return
     */
    public static Dialog showFeedbackDialog(Activity activity) {
        View view = LayoutInflater.from(activity).inflate(R.layout.dialog_feedback, null);
        CustomDialog customDialog = new CustomDialog(activity, R.style.dialog_fade_style);
        customDialog.setContentView(view);
        AppCompatEditText et_feedback_content = view.findViewById(R.id.et_feedback_content);
        AppCompatTextView tv_feedback_cancel = view.findViewById(R.id.tv_feedback_cancel);
        AppCompatTextView tv_feedback_ok = view.findViewById(R.id.tv_feedback_ok);
        tv_feedback_cancel.setOnClickListener(v -> {
            if (customDialog.isShowing()) {
                customDialog.dismiss();
            }
        });
        tv_feedback_ok.setSelected(true);
        tv_feedback_ok.setOnClickListener(v -> {
            if (!TextUtils.isEmpty(et_feedback_content.getText())) {
                if (customDialog.isShowing()) {
                    customDialog.dismiss();
                }
                ToastUtils.showToast(activity, "您的反馈我们已经收到，感谢您的支持");
            }

        });
        if (!activity.isFinishing()) {
            customDialog.show();
        }
        return customDialog;
    }
}
