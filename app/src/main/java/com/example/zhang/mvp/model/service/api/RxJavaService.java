package com.example.zhang.mvp.model.service.api;

import com.example.zhang.mvp.model.bean.BannerBean;
import com.example.zhang.mvp.model.bean.LoginResponseBean;
import com.example.zhang.mvp.model.bean.MainDataBean;
import com.example.zhang.mvp.model.bean.RegisterParamBean;
import com.example.zhang.mvp.model.bean.RegisterResponseBean;
import com.example.zhang.mvp.model.bean.UrlRequestBean;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * @author zzh
 */
public interface RxJavaService {
    /**
     * [@Headers 添加固定头部信息]
     *
     * @param timestamp @Header 添加动态头部信息
     * @param id        @Path {占位符}
     * @param params    @QueryMap添加url请求参数
     * @return Observable<MainDataBean>
     */
    @Headers({"Cache-Control: public, max-age=10"})
    @GET("article/list/{id}/json")
    Observable<MainDataBean> getMainData(@Header("timestamp") String timestamp, @Path("id") int id, @QueryMap Map<String, String> params);

    /**
     * [@Url 注解与BaseURL关系]
     *
     * @param url 1、在注解中提供的url是完整的url，则url将作为请求的url
     *            2、在注解中提供的url是不完整的url，且不以 / 开头，则请求的url为baseUrl+注解中提供的值
     *            3、在注解中提供的url是不完整的url，且以 / 开头，则请求的url为baseUrl的主机部分+注解中提供的值
     * @return Observable<UrlRequestBean>
     */
    @Headers({"Cache-Control: public, max-age=10"})
    @GET
    Observable<UrlRequestBean> getUrlData(@Url String url);

    /**
     * 获取banner数据
     *
     * @param timestamp 时间戳
     * @param time      时间
     * @param params    查询参数
     * @return Observable<BannerBean>
     */
    @Headers({"Cache-Control: public, max-age=10"})
    @GET("banner/json")
    Observable<BannerBean> getBannersData(@Header("timestamp") String timestamp, @Query("time") String time, @QueryMap Map<String, String> params);

    /**
     * 注册
     *
     * @param registerParamBean @Body GSON格式的Body体
     * @return Observable<Response                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               <                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               Void>>
     */
    @POST("user/register")
    Observable<Response<Void>> postRegister(@Body RegisterParamBean registerParamBean);

    /**
     * 注册
     *
     * @param params @FieldMap 表单格式的Body体--配合 @FormUrlEncoded使用
     * @return Observable<RegisterResponseBean>
     */
    @FormUrlEncoded
    @POST("user/register")
    Observable<RegisterResponseBean> postRegister(@FieldMap Map<String, String> params);

    /**
     * 登录
     *
     * @param params @FieldMap 表单格式的Body体--配合 @FormUrlEncoded使用
     * @return Observable<LoginResponseBean>
     */
    @FormUrlEncoded
    @POST("user/login")
    Observable<LoginResponseBean> postLogin(@FieldMap Map<String, String> params);

    /**
     * 登录
     *
     * @param username@Field 表单格式的Body体--配合 @FormUrlEncoded使用
     * @param password@Field 表单格式的Body体--配合 @FormUrlEncoded使用
     * @param nick@Query     添加URL上请求参数   @Query与@Field区别：@Query是把参数添加到URL后面，而@Field则是Body里面
     * @return Observable<LoginResponseBean>
     */
    @FormUrlEncoded
    @POST("user/login")
    Observable<LoginResponseBean> postLogin(@Field("username") String username, @Field("password") String password, @Query("nick") String nick);

}
