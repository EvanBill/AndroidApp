package com.example.zhang.mvp.model;

import com.example.zhang.mvp.model.bean.ProductBean;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;

/**
 * @author zzh
 */
public class MainModel {


    /**
     * 获得首页产品数据
     *
     * @return Flowable<List<ProductBean>>
     */
    public Flowable<List<ProductBean>> getProductData() {
        List<ProductBean> productBeanList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            ProductBean productBean = new ProductBean("productName-" + i);
            productBeanList.add(productBean);
        }
        return Flowable.just(productBeanList);
    }
}
