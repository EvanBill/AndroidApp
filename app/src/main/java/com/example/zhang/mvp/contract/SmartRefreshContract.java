package com.example.zhang.mvp.contract;

import com.example.zhang.base.BaseView;
import com.example.zhang.mvp.model.bean.SmartRefreshBean;

import java.util.List;

public interface SmartRefreshContract {
    interface ISmartRefreshView extends BaseView{
        void showContent(List<SmartRefreshBean> smartRefreshBeans);
    }
}
