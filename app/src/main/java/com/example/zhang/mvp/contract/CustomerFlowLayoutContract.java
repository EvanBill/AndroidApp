package com.example.zhang.mvp.contract;

import com.example.zhang.base.BaseView;

/**
 * @author zzh
 */
public interface CustomerFlowLayoutContract {
    interface ICustomerFlowLayoutView extends BaseView {
        /**
         * 展示数据
         *
         * @param content 数据
         */
        void setContent(String content);
    }
}
