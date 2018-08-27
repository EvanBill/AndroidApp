package com.example.zhang.mvp.model.service.api;

import com.example.zhang.mvp.model.bean.MainDataBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface MainActivityApi {
    @GET("news/latest")
    Observable<MainDataBean> getMainData();
}
