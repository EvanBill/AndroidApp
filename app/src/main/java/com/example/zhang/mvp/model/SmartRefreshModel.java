package com.example.zhang.mvp.model;


import com.example.zhang.mvp.model.bean.SmartRefreshBean;
import com.example.zhang.mvp.model.service.ServiceManager;
import com.example.zhang.mvp.model.service.api.SmartRefreshService;

import java.util.List;

import io.reactivex.Observable;


public class SmartRefreshModel {
    public Observable<List<SmartRefreshBean>> getSmartRefreshData() {
        return ServiceManager.getInstance().retrofit.create(SmartRefreshService.class).getSmartRefreshData();
    }
}
