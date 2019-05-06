package com.example.zhang.mvp.model;

import android.annotation.SuppressLint;

import com.example.zhang.mvp.model.bean.BannerBean;
import com.example.zhang.mvp.model.bean.LoginResponseBean;
import com.example.zhang.mvp.model.bean.MainDataBean;
import com.example.zhang.mvp.model.bean.RegisterParamBean;
import com.example.zhang.mvp.model.bean.RegisterResponseBean;
import com.example.zhang.mvp.model.bean.UrlRequestBean;
import com.example.zhang.mvp.model.service.ServiceManager;
import com.example.zhang.mvp.model.service.api.RxJavaService;
import com.example.zhang.utils.LogUtils;

import java.util.Map;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import retrofit2.Response;

/**
 * @author zzh
 */
public class RxJavaModel {
    private final String TAG = RxJavaModel.class.getSimpleName();

    /**
     * 获得创建RxJava数据
     *
     * @return Observable<Integer>
     */
    public Observable<Integer> getRxJavaCreateExampleData() {
        return Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) {
                LogUtils.debug(TAG, "getRxJavaCreateExampleData---:" + Thread.currentThread().getName() + "--:" + 1);
                emitter.onNext(1);

                LogUtils.debug(TAG, "getRxJavaCreateExampleData---:" + Thread.currentThread().getName() + "--:" + 2);
                emitter.onNext(2);
//                Thread.sleep(5000);
                LogUtils.debug(TAG, "getRxJavaCreateExampleData---:" + Thread.currentThread().getName() + "--:" + 3);
                emitter.onNext(3);
                emitter.onComplete();
                LogUtils.debug(TAG, "getRxJavaCreateExampleData---:" + Thread.currentThread().getName() + "--:" + 4);
                emitter.onNext(4);

            }
        });
    }

    public Observable<String> getRxJavaStringData() {
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) {
                LogUtils.debug(TAG, "getRxJavaStringData---:" + Thread.currentThread().getName() + "--:A");
                emitter.onNext("A");
                LogUtils.debug(TAG, "getRxJavaStringData---:" + Thread.currentThread().getName() + "--:B");
                emitter.onNext("B");
                LogUtils.debug(TAG, "getRxJavaStringData---:" + Thread.currentThread().getName() + "--:C");
                emitter.onNext("C");
            }
        });
    }

    public Observable<Integer> getRxJavaDistinctData() {
        return Observable.just(1, 2, 2, 1, 1, 2, 3, 4, 5, 2);
    }

    public Observable<Integer> getRxJavaDebounceData() {
        return Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                LogUtils.error(TAG, "getRxJavaDebounceData--:" + Thread.currentThread().getName() + "-emitter-:" + 1);
                emitter.onNext(1);
                Thread.sleep(500);
                LogUtils.error(TAG, "getRxJavaDebounceData--:" + Thread.currentThread().getName() + "-emitter-:" + 2);
                emitter.onNext(2);
                Thread.sleep(100);
                LogUtils.error(TAG, "getRxJavaDebounceData--:" + Thread.currentThread().getName() + "-emitter-:" + 3);
                emitter.onNext(3);
                Thread.sleep(200);
                LogUtils.error(TAG, "getRxJavaDebounceData--:" + Thread.currentThread().getName() + "-emitter-:" + 4);
                emitter.onNext(4);
                Thread.sleep(10);
                LogUtils.error(TAG, "getRxJavaDebounceData--:" + Thread.currentThread().getName() + "-emitter-:" + 5);
                emitter.onNext(5);
                Thread.sleep(500);
                LogUtils.error(TAG, "getRxJavaDebounceData--:" + Thread.currentThread().getName() + "-emitter-:" + 6);
                emitter.onNext(6);
            }
        });
    }


    public Flowable<Integer> getRxJavaFlowableData() {
        return Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> emitter) {
                LogUtils.debug(TAG, "getRxJavaFlowableData---:" + Thread.currentThread().getName() + "-request-:" + emitter.requested());
                LogUtils.debug(TAG, "getRxJavaFlowableData---:" + Thread.currentThread().getName() + "--:" + 1);
                emitter.onNext(1);
                LogUtils.debug(TAG, "getRxJavaFlowableData---:" + Thread.currentThread().getName() + "-request-:" + emitter.requested());
                LogUtils.debug(TAG, "getRxJavaFlowableData---:" + Thread.currentThread().getName() + "--:" + 2);
                emitter.onNext(2);
                // Thread.sleep(5000);
                LogUtils.debug(TAG, "getRxJavaFlowableData---:" + Thread.currentThread().getName() + "-request-:" + emitter.requested());
                LogUtils.debug(TAG, "getRxJavaFlowableData---:" + Thread.currentThread().getName() + "--:" + 3);
                emitter.onNext(3);
                LogUtils.debug(TAG, "getRxJavaFlowableData---:" + Thread.currentThread().getName() + "-request-:" + emitter.requested());
                LogUtils.debug(TAG, "getRxJavaFlowableData---:" + Thread.currentThread().getName() + "--:onComplete");
                emitter.onComplete();
                LogUtils.debug(TAG, "getRxJavaFlowableData---:" + Thread.currentThread().getName() + "-request-:" + emitter.requested());
                LogUtils.debug(TAG, "getRxJavaFlowableData---:" + Thread.currentThread().getName() + "--:" + 4);
                emitter.onNext(4);
            }
        }, BackpressureStrategy.ERROR);
    }

    public Flowable<Integer> getRxJavaFlowableSizeData() {
        return Flowable.create(new FlowableOnSubscribe<Integer>() {
            boolean isFlag = false;

            @SuppressWarnings("InfiniteLoopStatement")
            @Override
            public void subscribe(FlowableEmitter<Integer> emitter) {
                LogUtils.debug(TAG, "getRxJavaFlowableData---:" + Thread.currentThread().getName() + "-request-start:" + emitter.requested());

                for (int i = 0; ; i++) {
                    if (!isFlag) {
                        LogUtils.debug(TAG, "getRxJavaFlowableData---:" + Thread.currentThread().getName() + "-request-emit:" + emitter.requested());
                        if (emitter.requested() > 0) {
                            LogUtils.debug(TAG, "getRxJavaFlowableData---:" + Thread.currentThread().getName() + "--:" + i);
                            emitter.onNext(i);
                        } else {
                            isFlag = true;
                            LogUtils.debug(TAG, "getRxJavaFlowableData---:" + Thread.currentThread().getName() + "-request-emit: end ");
                        }
                    }

                }

            }
        }, BackpressureStrategy.ERROR);
    }

    public Flowable<Integer> getRxJavaFlowableRealExample() {
        return Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> emitter) {
                for (int i = 0; ; i++) {
                    if (emitter.isCancelled()) {
                        break;
                    }
                    while (emitter.requested() == 0) {
                    }
                    LogUtils.debug(TAG, "getRxJavaFlowableRealExample---:" + Thread.currentThread().getName() + "-request-emit:" + emitter.requested());
                    LogUtils.debug(TAG, "getRxJavaFlowableRealExample---:" + Thread.currentThread().getName() + "--:" + i);
                    emitter.onNext(i);
                }

            }
        }, BackpressureStrategy.ERROR);
    }

    @SuppressLint("all")
    public Flowable<Integer> getRxJavaFlowable128Data() {
        final int count = 129;
        return Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> emitter) {
                for (int i = 0; i < count; i++) {
                    LogUtils.debug(TAG, "getRxJavaFlowable128Data---:" + Thread.currentThread().getName() + "--:" + i);
                    emitter.onNext(i);
                }
            }
        }, BackpressureStrategy.ERROR);
    }

    public Observable<MainDataBean> getMainData(String headerTimestamp, int id, Map<String, String> requestParams) {
        return ServiceManager.getInstance().retrofit.create(RxJavaService.class).getMainData(headerTimestamp, id, requestParams);
    }

    public Observable<UrlRequestBean> getUrlData(String url) {
        return ServiceManager.getInstance().retrofit.create(RxJavaService.class).getUrlData(url);
    }

    public Observable<BannerBean> getBanner(String headerTimestamp, String time, Map<String, String> requestParams) {
        return ServiceManager.getInstance().retrofit.create(RxJavaService.class).getBannersData(headerTimestamp, time, requestParams);
    }

    public Observable<LoginResponseBean> postLogin(Map<String, String> map) {
        return ServiceManager.getInstance().retrofit.create(RxJavaService.class).postLogin(map);
    }

    public Observable<LoginResponseBean> postLogin(String username, String password, String nick) {
        return ServiceManager.getInstance().retrofit.create(RxJavaService.class).postLogin(username, password, nick);
    }

    public Observable<Response<Void>> postRegister(RegisterParamBean registerParamBean) {
        return ServiceManager.getInstance().retrofit.create(RxJavaService.class).postRegister(registerParamBean);
    }

    public Observable<RegisterResponseBean> postRegister(Map<String, String> params) {
        return ServiceManager.getInstance().retrofit.create(RxJavaService.class).postRegister(params);
    }

}
