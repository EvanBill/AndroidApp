package com.example.zhang.mvp.contract;

import com.example.zhang.base.BaseView;

/**
 * @author zzh
 */
public interface RxLifeCycleContract {
    interface IRxLifeCycleView extends BaseView {
        /**
         * 展示数据
         */
        void showContent();
    }
}
