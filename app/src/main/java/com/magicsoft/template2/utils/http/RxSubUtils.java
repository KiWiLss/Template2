package com.magicsoft.template2.utils.http;

import android.content.Context;
import android.util.Log;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.subscribers.DisposableSubscriber;

public abstract class RxSubUtils<T> extends DisposableSubscriber<T> {
    public static final String TAG="MMM";
    private CompositeDisposable compositeDisposable;
    private Context mContext;
    private String msg;

    public RxSubUtils(CompositeDisposable mCompositeSubscription) {
        this.compositeDisposable = mCompositeSubscription;
    }

    public RxSubUtils(){}
    public RxSubUtils(Context context){
        mContext=context;
    }

    /**
     * @param context context
     * @param msg     dialog message
     */
    public RxSubUtils(CompositeDisposable mCompositeSubscription, Context context, String msg) {
        this.compositeDisposable = mCompositeSubscription;
        this.mContext = context;
        this.msg = msg;
    }

    /**
     * @param context context
     */
    public RxSubUtils(CompositeDisposable mCompositeSubscription, Context context) {
        this(mCompositeSubscription, context, "请稍后...");
    }

    /**
     * 这个一定要有 Presenter的逻辑在这里处理
     * @param t
     */
    @Override
    public void onNext(T t) {
        _onNext(t);
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        //LoadingDialogManager.getLoadingDialog().hideDialog();

//        if (!NetWorkUtils.isNetworkAvailable()) {
//            ToastUtil.show(R.string.net_error);
//        } else if (e instanceof RxUtils.ServerException) {
////            String s = ((RxUtils.ServerException)e).getMsg();
////            //token 过期了
////            if(TextUtils.equals(s, RxUtils.TOKEN_OVER_TIME) && mContext != null){
////                Utils.startLoginActivity(mContext);
////            }
////            ToastUtil.show(s);
//        } else {
//            ToastUtil.show(R.string.error);
//        }
        Log.e(TAG, "onError: "+e.getMessage());
        _onError();
    }

    @Override
    public void onComplete() {
        if (compositeDisposable != null)
            compositeDisposable.clear();
        Log.e(TAG, "onComplete: " );
        //LoadingDialogManager.getLoadingDialog().hideDialog();
    }

    @Override
    public void onStart() {
        super.onStart();
        /*if (mContext != null) {
            LoadingDialogManager.getLoadingDialog().showDialog(mContext);
        }*/
        Log.e(TAG, "onStart: ");
    }

    protected abstract void _onNext(T t);

    /**
     * 错误处理，需要的话重写这个方法
     */
    protected void _onError(){

    }

}

