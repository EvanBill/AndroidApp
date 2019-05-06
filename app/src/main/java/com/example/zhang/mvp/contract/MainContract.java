package com.example.zhang.mvp.contract;

import com.example.zhang.base.BaseView;
import com.example.zhang.mvp.model.bean.ProductBean;

import java.util.List;

/**
 * @author zzh
 */
public interface MainContract {
    interface IMainView extends BaseView {
        /**
         * 展示数据
         *
         * @param productBeanList 数据
         */
        void showContent(List<ProductBean> productBeanList);
    }
}
