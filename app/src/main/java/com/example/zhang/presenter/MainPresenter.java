package com.example.zhang.presenter;

import com.example.zhang.base.BasePresenter;
import com.example.zhang.bean.ProductBean;
import com.example.zhang.model.MainModel;
import com.example.zhang.model.contract.MainContract;
import com.example.zhang.utils.LogUtils;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainPresenter extends BasePresenter<MainModel, MainContract.IMainView> {
    private String TAG = MainPresenter.class.getSimpleName();
    private CompositeDisposable compositeDisposable;

    public MainPresenter(MainContract.IMainView view) {
        super(view);
        model = new MainModel();
        compositeDisposable = new CompositeDisposable();
    }

    /**
     * 练习RxJava创建过程
     */
    public void rxJavaCreateExample() {
        model.getRxJavaCreateExampleData()
//                .subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Integer>() {
                    private Disposable disposable;

                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                        LogUtils.error(TAG, "rxJavaCreateExample---:onSubscribe");
                    }

                    @Override
                    public void onNext(Integer integer) {
                        LogUtils.error(TAG, "rxJavaCreateExample---:" + integer);
//                        if (integer == 2) {
//                            disposable.dispose();
//                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.error(TAG, "rxJavaCreateExample---:onError");
                    }

                    @Override
                    public void onComplete() {
                        LogUtils.error(TAG, "rxJavaCreateExample---:onComplete");

                    }
                });
    }

    public void getData() {
        Disposable disposable = model.getProductData()
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<ProductBean>>() {
                    @Override
                    public void accept(List<ProductBean> productBeanList) {
                        view.showContent(productBeanList);
                    }
                });
        compositeDisposable.add(disposable);
    }

    public void onFinishActivity() {
        compositeDisposable.dispose();
    }
}
