package com.example.zhang.mvp.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.LinearLayoutCompat;

import com.example.zhang.R;
import com.example.zhang.base.BaseFragment;
import com.example.zhang.utils.DialogUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_setting, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick({R.id.ll_setting_feedback})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_setting_feedback:
                DialogUtils.showFeedbackDialog(getActivity());
                break;
            default:
                break;
        }

    }
}
