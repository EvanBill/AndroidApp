package com.example.zhang.base;

import androidx.fragment.app.Fragment;

import com.example.zhang.app.AppApplication;

import java.util.Objects;

/**
 * @author : zzh
 * @date : 2019/5/13
 * @desc :Fragment基类
 */
public class BaseFragment extends Fragment {
    @Override
    public void onDestroy() {
        super.onDestroy();
        AppApplication.getRefWatcher(Objects.requireNonNull(this.getContext())).watch(this);
    }
}
