package com.example.zhang.mvp.model.service;

import com.blankj.utilcode.util.NetworkUtils;
import com.example.zhang.app.Constants;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceManager {
    private static volatile ServiceManager instance;
    public Retrofit retrofit;

    private ServiceManager() {
        retrofit = new Retrofit.Builder()
                .baseUrl(Constants.HOST)
                .client(getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public static ServiceManager getInstance() {
        if (null == instance) {
            synchronized (ServiceManager.class) {
                if (instance == null) {
                    instance = new ServiceManager();
                }
            }

        }
        return instance;
    }

    private OkHttpClient getOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (Constants.ISLOG) {
            builder.addNetworkInterceptor(getHttpLoggingInterceptor());
        }
        //设置统一的请求头部参数
        builder.addInterceptor(getHttpHeaderInterceptor());
        //设置统一的添加参数的拦截器
        builder.addInterceptor(getHttpParamsInterceptor());
        //设置缓存
        File cacheFile = new File(Constants.PATH_CACHE);
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 50);
        builder.addNetworkInterceptor(getHttpCacheInterceptor());
        builder.addInterceptor(getHttpCacheInterceptor());
        builder.cache(cache);
        //设置超时
        builder.connectTimeout(10, TimeUnit.SECONDS);
        builder.readTimeout(20, TimeUnit.SECONDS);
        builder.writeTimeout(20, TimeUnit.SECONDS);
        //错误重连
        builder.retryOnConnectionFailure(true);
        return builder.build();
    }

    /**
     * 获取HTTP 打印log的拦截器
     *
     * @return
     */
    private HttpLoggingInterceptor getHttpLoggingInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }

    /**
     * 获取HTTP 添加header的拦截器
     *
     * @return
     */
    private Interceptor getHttpHeaderInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                request = request.newBuilder()
                        .addHeader("User-Agent", "Android")
                        .method(request.method(), request.body())
                        .build();
                return chain.proceed(request);
            }
        };
    }

    /**
     * 获取HTTP 添加公共参数的拦截器
     * 暂时支持get、head请求&Post put patch的表单数据请求
     *
     * @return
     */
    private Interceptor getHttpParamsInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();

                if (request.method().equalsIgnoreCase("GET") || request.method().equalsIgnoreCase("HEAD")) {
                    HttpUrl httpUrl = request.url().newBuilder()
                            .addQueryParameter("version", "1.1.0")
                            .addQueryParameter("devices", "android")
                            .build();
                    request = request.newBuilder().url(httpUrl).build();
                } else {
                    RequestBody originalBody = request.body();
                    if (originalBody instanceof FormBody) {
                        FormBody.Builder builder = new FormBody.Builder();
                        FormBody formBody = (FormBody) originalBody;
                        for (int i = 0; i < formBody.size(); i++) {
                            builder.addEncoded(formBody.encodedName(i), formBody.encodedValue(i));
                        }
                        FormBody newFormBody = builder.addEncoded("version", "1.1.0")
                                .addEncoded("devices", "android")
                                .build();
                        if (request.method().equalsIgnoreCase("POST")) {
                            request = request.newBuilder().post(newFormBody).build();
                        } else if (request.method().equalsIgnoreCase("PATCH")) {
                            request = request.newBuilder().patch(newFormBody).build();
                        } else if (request.method().equalsIgnoreCase("PUT")) {
                            request = request.newBuilder().put(newFormBody).build();
                        }

                    } else if (originalBody instanceof MultipartBody) {

                    }

                }
                return chain.proceed(request);
            }
        };
    }

    /**
     * 获得HTTP 缓存的拦截器
     *
     * @return
     */
    public Interceptor getHttpCacheInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                // 无网络时，始终使用本地Cache
                if (!NetworkUtils.isConnected()) {
                    request = request.newBuilder()
                            .cacheControl(CacheControl.FORCE_CACHE)
                            .build();
                }
                Response response = chain.proceed(request);
                if (NetworkUtils.isConnected()) {
                    //有网的时候读接口上的@Headers里的配置，你可以在这里进行统一的设置
                    String cacheControl = request.cacheControl().toString();
                    return response.newBuilder()
                            .header("Cache-Control", cacheControl)
                            .removeHeader("Pragma")
                            .build();
                } else {
                    // 无网络时，设置超时为4周
                    int maxStale = 60 * 60 * 24 * 28;
                    return response.newBuilder()
                            //这里的设置的是我们的没有网络的缓存时间，想设置多少就是多少。
                            .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                            .removeHeader("Pragma")
                            .build();
                }

            }
        };
    }
}
