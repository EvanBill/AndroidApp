package com.example.zhang.model.contract;

import com.example.zhang.base.BaseView;
import com.example.zhang.bean.ProductBean;

import java.util.List;

public interface MainContract {
    interface IMainView extends BaseView{
        void showContent(List<ProductBean> productBeanList);
    }
}
