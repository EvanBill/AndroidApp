package com.example.zhang.presenter;

import com.blankj.utilcode.util.TimeUtils;
import com.example.zhang.base.BasePresenter;
import com.example.zhang.bean.ProductBean;
import com.example.zhang.model.MainModel;
import com.example.zhang.model.contract.MainContract;
import com.example.zhang.utils.LogUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
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
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
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
                        if (integer == 2) {
                            disposable.dispose();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.error(TAG, "rxJavaCreateExample---:onError");
                        disposable.dispose();
                    }

                    @Override
                    public void onComplete() {
                        LogUtils.error(TAG, "rxJavaCreateExample---:onComplete");
                        disposable.dispose();
                    }
                });
    }

    /**
     * RxJava -Map语法练习
     */
    public void rxJavaMapExample() {
        Disposable subscribe = model.getRxJavaCreateExampleData()
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .map(new Function<Integer, String>() {
                    @Override
                    public String apply(Integer integer) throws Exception {
                        LogUtils.error(TAG, "rxJavaMapExample--Function--:" + Thread.currentThread().getName() + "--:" + integer);
                        return "this is from map " + integer;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        LogUtils.error(TAG, "rxJavaMapExample--Consumer--:" + Thread.currentThread().getName() + "--:" + s);
                    }
                });
        compositeDisposable.add(subscribe);
    }

    /**
     * RxJava-zip语法练习
     */
    public void rxJavaZipExample() {
        Disposable subscribe = Observable.zip(model.getRxJavaCreateExampleData().subscribeOn(Schedulers.io()), model.getRxJavaStringData().subscribeOn(Schedulers.io())
                , new BiFunction<Integer, String, String>() {
                    @Override
                    public String apply(Integer integer, String s) throws Exception {
                        LogUtils.error(TAG, "rxJavaZipExample--zip--:" + Thread.currentThread().getName() + "--:" + integer + "--:" + s);
                        return "this is zip method --:" + integer + "--:" + s;
                    }
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        LogUtils.error(TAG, "rxJavaZipExample--Consumer--:" + Thread.currentThread().getName() + "--:" + s);
                    }
                });
        compositeDisposable.add(subscribe);

    }

    /**
     * RxJava语法-Concat练习
     */
    public void rxJavaConcatExample() {
        Disposable disposable = Observable.concat(model.getRxJavaCreateExampleData().subscribeOn(Schedulers.io()), model.getRxJavaStringData().subscribeOn(Schedulers.io()))
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Serializable>() {
                    @Override
                    public void accept(Serializable serializable) throws Exception {
                        LogUtils.error(TAG, "rxJavaConcatExample--Consumer--:" + Thread.currentThread().getName() + "--:" + serializable);
                    }
                });
        compositeDisposable.add(disposable);
    }

    /**
     * RxJava语法-FlatMap练习
     */
    public void rxJavaFlatMapExample() {
        Disposable disposable = model.getRxJavaCreateExampleData()
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .flatMap(new Function<Integer, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(Integer integer) throws Exception {
                        LogUtils.error(TAG, "rxJavaFlatMapExample--flatmap--:" + Thread.currentThread().getName() + "--:" + integer);
                        List<String> list = new ArrayList<>();
                        for (int i = 0; i < 3; i++) {
                            list.add("this is flatMap--" + integer + "---i--:" + i);
                        }
                        long delayTime = 1 + (int) (10 * Math.random());
                        return Observable.fromIterable(list).delay(delayTime, TimeUnit.SECONDS);
                    }
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        LogUtils.error(TAG, "rxJavaFlatMapExample--Consumer--:" + Thread.currentThread().getName() + "--:" + s);
                    }
                });
        compositeDisposable.add(disposable);
    }

    /**
     * RxJava语法-ConcatMap练习
     */
    public void rxJavaConcatMapExample() {
        Disposable disposable = model.getRxJavaCreateExampleData()
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .concatMap(new Function<Integer, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(Integer integer) throws Exception {
                        LogUtils.error(TAG, "rxJavaConcatMapExample--flatmap--:" + Thread.currentThread().getName() + "--:" + integer);
                        List<String> list = new ArrayList<>();
                        for (int i = 0; i < 3; i++) {
                            list.add("this is ConcatMap--" + integer + "---i--:" + i);
                        }
                        long delayTime = 1 + (int) (10 * Math.random());
                        return Observable.fromIterable(list).delay(delayTime, TimeUnit.SECONDS);//delay方法内部切换了线程
                    }
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        LogUtils.error(TAG, "rxJavaConcatMapExample--Consumer--:" + Thread.currentThread().getName() + "--:" + s);
                    }
                });
        compositeDisposable.add(disposable);
    }

    /**
     * RxJava语法-distinct练习
     */
    public void rxJavaDistinctExample() {
        Disposable disposable = model.getRxJavaDistinctData()
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .distinct()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        LogUtils.error(TAG, "rxJavaDistinctExample--Consumer--:" + Thread.currentThread().getName() + "--:" + integer);
                    }
                });
        compositeDisposable.add(disposable);
    }

    /**
     * RxJava语法-filter练习
     */
    public void rxJavaFilterExample() {
        Disposable disposable = model.getRxJavaDistinctData()
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .filter(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer integer) throws Exception {
                        LogUtils.error(TAG, "rxJavaFilterExample--filter--:" + Thread.currentThread().getName() + "--:" + integer);
                        return integer > 1;
                    }
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        LogUtils.error(TAG, "rxJavaFilterExample--Consumer--:" + Thread.currentThread().getName() + "--:" + integer);
                    }
                });
        compositeDisposable.add(disposable);
    }

    /**
     * RxJava语法-buffer
     */
    public void rxJavaBufferExample() {
        Disposable disposable = model.getRxJavaDistinctData()
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .buffer(3, 2)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Integer>>() {
                    @Override
                    public void accept(List<Integer> integers) throws Exception {
                        LogUtils.error(TAG, "rxJavaBufferExample--Consumer--:" + Thread.currentThread().getName() + "--size:" + integers.size());
                        for (int integer : integers) {
                            LogUtils.error(TAG, "rxJavaBufferExample--Consumer--:" + Thread.currentThread().getName() + "--value:" + integer + "\n");
                        }
                    }
                });
        compositeDisposable.add(disposable);
    }

    /**
     * RxJava语法-time练习
     */
    public void rxJavaTimeExample() {
        LogUtils.error(TAG, "rxJavaTimeExample--start--:" + Thread.currentThread().getName() + "--:" + TimeUtils.getNowString());
        Disposable disposable = Observable.timer(4, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        LogUtils.error(TAG, "rxJavaTimeExample--end--:" + Thread.currentThread().getName() + "--:" + TimeUtils.getNowString() + "---long:" + aLong);
                    }
                });
        compositeDisposable.add(disposable);
    }

    /**
     * rxJava-interval
     */
    public void rxJavaIntervalExample() {
        LogUtils.error(TAG, "rxJavaIntervalExample--start--:" + Thread.currentThread().getName() + "--:" + TimeUtils.getNowString());
        Disposable disposable = Observable.interval(3, 2, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        LogUtils.error(TAG, "rxJavaIntervalExample--end--:" + Thread.currentThread().getName() + "--:" + TimeUtils.getNowString() + "---long:" + aLong);
                    }
                });
        compositeDisposable.add(disposable);
    }

    /**
     * RxJava -doOnNext
     */
    public void rxJavaDoOnNextExample() {
        Disposable disposable = model.getRxJavaCreateExampleData()
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .doOnNext(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        LogUtils.error(TAG, "rxJavaDoOnNextExample--:" + Thread.currentThread().getName() + "-doOnNext-:" + integer);
                    }
                })
                .doAfterNext(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        LogUtils.error(TAG, "rxJavaDoOnNextExample--:" + Thread.currentThread().getName() + "-doAfterNext-:" + integer);
                    }
                })
                .doOnComplete(new Action() {
                    @Override
                    public void run() throws Exception {
                        LogUtils.error(TAG, "rxJavaDoOnNextExample--:" + Thread.currentThread().getName() + "-doOnComplete-:");
                    }
                })
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        LogUtils.error(TAG, "rxJavaDoOnNextExample--:" + Thread.currentThread().getName() + "-doFinally-:");
                    }
                })
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        LogUtils.error(TAG, "rxJavaDoOnNextExample--:" + Thread.currentThread().getName() + "-Consumer-:" + integer);
                    }
                });
        compositeDisposable.add(disposable);
    }

    /**
     * skip(long count) count 跳过count个
     */
    public void rxJavaSkipExample() {
        Disposable disposable = model.getRxJavaCreateExampleData()
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .skip(2)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        LogUtils.error(TAG, "rxJavaSkipExample--:" + Thread.currentThread().getName() + "-Consumer-:" + integer);
                    }
                });
        compositeDisposable.add(disposable);
    }

    /**
     * take(long count) count 能接收的最多count个数据
     */
    public void rxJavaTakeExample() {
        Disposable disposable = model.getRxJavaCreateExampleData()
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .take(2)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        LogUtils.error(TAG, "rxJavaTakeExample--:" + Thread.currentThread().getName() + "-Consumer-:" + integer);
                    }
                });
        compositeDisposable.add(disposable);
    }

    public void rxJavaSingleExample() {

        Single.just(1)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Integer>() {
                    Disposable disposable;

                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onSuccess(Integer integer) {
                        LogUtils.error(TAG, "rxJavaSingleExample--:" + Thread.currentThread().getName() + "-onSuccess-:" + integer);
                        disposable.dispose();
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.error(TAG, "rxJavaSingleExample--:" + Thread.currentThread().getName() + "-onError-:" + e.toString());
                        disposable.dispose();
                    }
                });

    }

    public void rxJavaDebounceExample() {
        Disposable disposable = model.getRxJavaDebounceData()
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .debounce(150, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        LogUtils.error(TAG, "rxJavaDebounceExample--:" + Thread.currentThread().getName() + "-consumer-:" + integer);
                    }
                });
        compositeDisposable.add(disposable);
    }

    public void rxJavaDeferExample() {
        Disposable disposable = Observable.defer(new Callable<ObservableSource<?>>() {
            @Override
            public ObservableSource<?> call() throws Exception {
                return model.getRxJavaCreateExampleData();
            }
        }).subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        LogUtils.error(TAG, "rxJavaDeferExample--:" + Thread.currentThread().getName() + "-consumer-:" + o.toString());
                    }
                });
        compositeDisposable.add(disposable);
    }

    public void rxJavaLastExample() {
        Disposable disposable = model.getRxJavaCreateExampleData()
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .last(4)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        LogUtils.error(TAG, "rxJavaDeferExample--:" + Thread.currentThread().getName() + "-consumer-:" + integer);
                    }
                });
        compositeDisposable.add(disposable);
    }

    public void rxJavaMergeExample() {
        Disposable disposable = Observable.merge(model.getRxJavaCreateExampleData().subscribeOn(Schedulers.io()), model.getRxJavaStringData().subscribeOn(Schedulers.io()))
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Serializable>() {
                    @Override
                    public void accept(Serializable serializable) throws Exception {
                        LogUtils.error(TAG, "rxJavaMergeExample--:" + Thread.currentThread().getName() + "-consumer-:" + serializable);
                    }
                });
        compositeDisposable.add(disposable);
    }

    public void rxJavaReduceExample() {
        Disposable disposable = model.getRxJavaCreateExampleData()
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .reduce(new BiFunction<Integer, Integer, Integer>() {
                    @Override
                    public Integer apply(Integer integer, Integer integer2) throws Exception {
                        LogUtils.error(TAG, "rxJavaReduceExample--:" + Thread.currentThread().getName() + "-reduce-:" + integer + "---" + integer2);
                        return integer + integer2;
                    }
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        LogUtils.error(TAG, "rxJavaReduceExample--:" + Thread.currentThread().getName() + "-consumer-:" + integer);
                    }
                });
        compositeDisposable.add(disposable);
    }

    public void rxJavaScanExample() {
        Disposable disposable = model.getRxJavaCreateExampleData()
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .scan(new BiFunction<Integer, Integer, Integer>() {
                    @Override
                    public Integer apply(Integer integer, Integer integer2) throws Exception {
                        LogUtils.error(TAG, "rxJavaScanExample--:" + Thread.currentThread().getName() + "-scan-:" + integer + "---" + integer2);
                        return integer + integer2;
                    }
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        LogUtils.error(TAG, "rxJavaReduceExample--:" + Thread.currentThread().getName() + "-consumer-:" + integer);
                    }
                });
        compositeDisposable.add(disposable);
    }

    public void rxJavaWindowExample() {
        Disposable disposable = model.getRxJavaDistinctData()
                .window(3)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Observable<Integer>>() {
                    @Override
                    public void accept(Observable<Integer> longObservable) throws Exception {
                        LogUtils.error(TAG, "rxJavaReduceExample--:" + Thread.currentThread().getName() + "-consumer1-:" );
                        Disposable disposable1 = longObservable.subscribeOn(Schedulers.io())
                                .unsubscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new Consumer<Integer>() {
                                    @Override
                                    public void accept(Integer integer) throws Exception {
                                        LogUtils.error(TAG, "rxJavaReduceExample--:" + Thread.currentThread().getName() + "-consumer2-:" + integer);
                                    }
                                });
                        compositeDisposable.add(disposable1);
                    }
                });
        compositeDisposable.add(disposable);
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