package com.example.zhang.mvp.contract;

import com.example.zhang.base.BaseView;
import com.example.zhang.mvp.model.bean.SmartRefreshBean;

import java.util.List;

/**
 * @author zzh
 */
public interface SmartRefreshContract {
    interface ISmartRefreshView extends BaseView {
        /**
         * 展示书
         *
         * @param smartRefreshBeans 数据
         */
        void showContent(List<SmartRefreshBean> smartRefreshBeans);
    }
}
