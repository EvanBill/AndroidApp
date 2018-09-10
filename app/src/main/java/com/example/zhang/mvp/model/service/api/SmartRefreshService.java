package com.example.zhang.mvp.model.service.api;

import com.example.zhang.mvp.model.bean.SmartRefreshBean;

import java.util.List;


import io.reactivex.Observable;
import retrofit2.http.GET;

public interface SmartRefreshService {
    @GET("v4/categories")
    Observable<List<SmartRefreshBean>> getSmartRefreshData();
}
