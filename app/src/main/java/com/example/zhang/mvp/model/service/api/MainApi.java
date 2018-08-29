package com.example.zhang.mvp.model.service.api;

import com.example.zhang.mvp.model.bean.LoginResponseBean;
import com.example.zhang.mvp.model.bean.MainDataBean;
import com.example.zhang.mvp.model.bean.RegistResponseBean;
import com.example.zhang.mvp.model.bean.RegisterParamBean;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface MainApi {
    @GET("article/list/1/json")
    Observable<MainDataBean> getMainData();

    @POST("user/register")
    Observable<Response<Void>> postRegister(@Body RegisterParamBean registerParamBean);

    @FormUrlEncoded
    @POST("user/register")
    Observable<Response<RegistResponseBean>> postRegister(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST("user/login")
    Observable<LoginResponseBean> postLogin(@FieldMap Map<String, String> params);


}
