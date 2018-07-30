package com.example.zhang.model;

import com.example.zhang.bean.ProductBean;

import java.util.ArrayList;
import java.util.List;

public class MainModel {
    /**
     * 获得首页产品数据
     *
     * @return
     */
    public List<ProductBean> getProductData() {
        List<ProductBean> productBeanList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            ProductBean productBean = new ProductBean("productName-" + i, 100 + i);
            productBeanList.add(productBean);
        }
        return productBeanList;
    }
}
