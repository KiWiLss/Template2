package com.magicsoft.template2.utils.http;


import com.magicsoft.template2.model.AppBack2;
import com.magicsoft.template2.model.HttpResult;
import com.magicsoft.template2.utils.general.LUtils;

import org.reactivestreams.Publisher;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.FlowableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class RxUtils {
    private static FlowableTransformer ioToMainThreadSchedulerTransformer;
    private static FlowableTransformer newThreadToMainThreadSchedulerTransformer;

    static {
        ioToMainThreadSchedulerTransformer = createIOToMainThreadScheduler();
        newThreadToMainThreadSchedulerTransformer = createNewThreadToMainThreadScheduler();
    }

    private static <T> FlowableTransformer<T, T> createIOToMainThreadScheduler() {
        return tObservable -> tObservable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 从IO线程切换到主线程
     * @param <T>
     * @return
     */
    public static <T> FlowableTransformer<T, T> applyIOToMainThreadSchedulers() {
        return ioToMainThreadSchedulerTransformer;
    }

    private static <T> FlowableTransformer<T, T> createNewThreadToMainThreadScheduler() {
        return tObservable -> tObservable.subscribeOn(Schedulers.newThread())
                .unsubscribeOn(Schedulers.computation())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static <T> FlowableTransformer<T, T> applyNewThreadToMainThreadSchedulers() {
        return newThreadToMainThreadSchedulerTransformer;
    }

    /**
     * 处理服务器返回的数据，进一步处理错误信息
     * @param <T>
     * @return
     */
    public static <T> FlowableTransformer<AppBack2<T>, T> handleResult(){
        return new FlowableTransformer<AppBack2<T>, T>() {
            @Override
            public Publisher<T> apply(@NonNull Flowable<AppBack2<T>> flowable) {
                return flowable.flatMap(new Function<AppBack2<T>, Publisher<T>>() {
                    @Override
                    public Publisher<T> apply(@NonNull AppBack2<T> tBaseBean) throws Exception {
                        LUtils.e(tBaseBean.getStatus());
                        if (tBaseBean.isSuccess()){
                            return createData(tBaseBean.getResult());
                        }
                        return Flowable.error(new ServerException("返回服务器返回的错误信息"));
                    }
                }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            }
        };

    }
    /**
     * 处理服务器返回的数据，进一步处理错误信息
     * @param <T>
     * @return
     */
    public static <T> FlowableTransformer<HttpResult<T>, T> handleResult2(){
        return new FlowableTransformer<HttpResult<T>, T>() {
            @Override
            public Publisher<T> apply(@NonNull Flowable<HttpResult<T>> flowable) {
                return flowable.flatMap(new Function<HttpResult<T>, Publisher<T>>() {
                    @Override
                    public Publisher<T> apply(@NonNull HttpResult<T> tBaseBean) throws Exception {

                        if (tBaseBean.isOk()){
                            return createData(tBaseBean.getData());
                        }
                        return Flowable.error(new ServerException(tBaseBean.getMsg()));
                    }
                }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            }
        };

    }
    /**
     * 创建成功的数据
     *
     * @param data
     * @param <T>
     * @return
     */
    private static <T> Flowable<T> createData(T data) {
        return Flowable.create(new FlowableOnSubscribe<T>() {
            @Override
            public void subscribe(@NonNull FlowableEmitter<T> flowableEmitter) throws Exception {
                try {
                    flowableEmitter.onNext(data);
                    flowableEmitter.onComplete();
                }catch (Exception e){
                    flowableEmitter.onError(e);
                }
            }
        }, BackpressureStrategy.BUFFER);
    }

   /* *//**
     * 自定义 服务器返回异常
     *//*
    public static class ServerException extends Throwable {
        public ServerException(String msg) {
        }
    }*/


}

