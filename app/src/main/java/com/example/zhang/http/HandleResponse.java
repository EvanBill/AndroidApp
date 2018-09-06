package com.example.zhang.http;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;

import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.text.ParseException;

import okhttp3.ResponseBody;
import retrofit2.HttpException;
import retrofit2.Response;


/**
 * Created by Administrator on 2018/1/10.
 */

public class HandleResponse {
    //对应HTTP的状态码
    private static final int SUCCESS_200 = 200;
    private static final int SUCCESS_201 = 201;
    private static final int SUCCESS_204 = 204;
    private static final int UNAUTHORIZED = 401;
    private static final int FORBIDDEN = 403;
    private static final int NOT_FOUND = 404;
    private static final int REQUEST_TIMEOUT = 408;
    private static final int INTERNAL_SERVER_ERROR = 500;
    private static final int BAD_GATEWAY = 502;
    private static final int SERVICE_UNAVAILABLE = 503;
    private static final int GATEWAY_TIMEOUT = 504;
    //解析错误
    private static final int PARSE_ERROR = 1001;
    /**
     * 未知错误
     */
    private static final int UNKNOWN = 1000;
    private static final int TIMEDOUT = 1002;
    /**
     * 网络错误
     */
    private static final int NETWORD_ERROR = 1003;

    /**
     * 处理异常，统一封装返回结果
     *
     * @param e
     * @return
     */
    public static ResponseBean handleException(Throwable e) {
        ResponseBean responseBean = new ResponseBean();

        if (e instanceof HttpException) {             //HTTP错误
            HttpException httpException = null;
            try {
                httpException = (HttpException) e;
                if (!TextUtils.isEmpty(httpException.response().errorBody().string())) {
                    Gson gson = new Gson();
                    responseBean = gson.fromJson(httpException.response().errorBody().string(), ResponseBean.class);
                }
            } catch (Exception ex) {
                Log.e("HandleResponse", e.toString());
            }
            if (TextUtils.isEmpty(responseBean.getMessage())) {
                switch (httpException.code()) {
                    case NOT_FOUND:
                        responseBean.setCustomCode(httpException.code());
                        responseBean.setMessage("服务器竟然制障了");
                        break;
                    default: {
                        responseBean.setCustomCode(httpException.code());
                        responseBean.setMessage("网络竟然崩溃了");
                    }
                }
            }
        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException) {
            responseBean = new ResponseBean(PARSE_ERROR, "解析错误");
        } else if (e instanceof ConnectException) {
            responseBean = new ResponseBean(NETWORD_ERROR, " 网络竟然崩溃了");
        } else if (e instanceof SocketTimeoutException) {
            responseBean = new ResponseBean(TIMEDOUT, " 网络竟然崩溃了");
        } else {
            responseBean = new ResponseBean(UNKNOWN, "未知错误");
        }
        if (responseBean.getCustomCode() == FORBIDDEN) {
//            RxBus.getInstance().send(new LogoutEvent());
        }
        return responseBean;
    }

    /**
     * 封装返回结果
     *
     * @param response
     * @return
     */
    public static ResponseBean handleResponse(Response<Void> response) {
        ResponseBean responseBean = new ResponseBean();
        responseBean.setCustomCode(response.code());
        responseBean.setMessage("");
        try {
            ResponseBody responseBody = response.errorBody();
            if (responseBody != null) {
                String str = responseBody.string();
                if (!TextUtils.isEmpty(str)) {
                    Gson gson = new Gson();
                    responseBean  = gson.fromJson(str, ResponseBean.class);
                }
            }

        } catch (Exception e) {
            Log.e("HandleResponse:", e.toString());
        }
        if (responseBean.getCustomCode() == FORBIDDEN) {
//            RxBus.getInstance().send(new LogoutEvent());
        }
        return responseBean;
    }

    /**
     * 处理返回结果
     *
     * @param e
     */
    public static void processException(Throwable e, ResponseListener responseListener) {
        ResponseBean responseBean = handleException(e);
        processResponse(responseBean, responseListener);
    }

    /**
     * 处理返回结果
     *
     * @param e
     * @param responseErrorListener
     */
    public static void processException(Throwable e, ResponseErrorListener responseErrorListener) {
        ResponseBean responseBean = handleException(e);
        processResponse(responseBean, responseErrorListener);
    }

    /**
     * 处理返回结果
     *
     * @param response
     */
    public static void processResponse(Response<Void> response, ResponseListener responseListener) {
        ResponseBean responseBean = handleResponse(response);
        processResponse(responseBean, responseListener);
    }

    /**
     * 处理返回结果
     *
     * @param response
     */
    public static void processResponse(Response<Void> response, ResponseErrorListener responseErrorListener) {
        ResponseBean responseBean = handleResponse(response);
        processResponse(responseBean, responseErrorListener);
    }

    /**
     * 处理返回结果
     *
     * @param responseBean
     */
    public static void processResponse(ResponseBean responseBean, ResponseListener responseListener) {
        if (responseBean != null) {
            switch (responseBean.getCustomCode()) {
                case SUCCESS_200:
                case SUCCESS_201:
                case SUCCESS_204: {
                    responseListener.onSuccess();
                }
                break;
                default:
                    responseListener.onError(responseBean.getCustomCode(), responseBean.getMessage());

            }
        }
    }

    /**
     * 处理返回结果
     *
     * @param responseErrorListener
     */
    public static void processResponse(ResponseBean responseBean, ResponseErrorListener responseErrorListener) {
        if (responseBean != null) {
            switch (responseBean.getCustomCode()) {
                case SUCCESS_200:
                case SUCCESS_201:
                case SUCCESS_204: {
                }
                break;
                default:
                    responseErrorListener.onError(responseBean.getCustomCode(), responseBean.getMessage());

            }
        }
    }

    public interface ResponseErrorListener {
        void onError(int code, String msg);
    }

    public interface ResponseListener {
        void onSuccess();

        void onError(int code, String msg);
    }

}
