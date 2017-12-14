package com.magicsoft.template2.utils.http;

import android.content.Context;
import android.text.TextUtils;

import com.blankj.utilcode.util.NetworkUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.magicsoft.template2.R;
import com.xiasuhuei321.loadingdialog.view.LoadingDialog;

import java.lang.ref.WeakReference;

import io.reactivex.subscribers.DisposableSubscriber;

public abstract class RxSubUtils<T> extends DisposableSubscriber<T> {
    private static final String TAG="MMM";
    //private CompositeDisposable compositeDisposable;
    //弱引用
    private WeakReference<Context> mContext;
    //private Context mContext;
    private String msg;//提示内容
    private boolean isShow;
    private LoadingDialog loadingDialog;

//    public RxSubUtils(CompositeDisposable mCompositeSubscription) {
//        this.compositeDisposable = mCompositeSubscription;
//    }

    //public RxSubUtils(){}//无参数,一般不用提示对话框

    public RxSubUtils(Context context,String hint){//自定义提示对话框内容
        this(context,hint,true);
    }

    public RxSubUtils(Context context){//默认对话框内容
        //mContext=context;
        //this.mContext=new WeakReference<Context>(context);
        this(context,"初始化...");
    }

    public RxSubUtils(Context context,String hint,boolean isShow){
        this.mContext=new WeakReference<Context>(context);
        this.msg=hint;
        this.isShow=isShow;
    }

   /* *//**
     * @param context context
     * @param msg     dialog message
     *//*
    public RxSubUtils(CompositeDisposable mCompositeSubscription, Context context, String msg) {
        this.compositeDisposable = mCompositeSubscription;
        //this.mContext = context;
        this.mContext=new WeakReference<Context>(context);
        this.msg = msg;
    }*/

  /*  *//**
     * @param context context
     *//*
    public RxSubUtils(CompositeDisposable mCompositeSubscription, Context context) {
        this(mCompositeSubscription, context, "初始化...");
    }*/

    /**
     * 这个一定要有 Presenter的逻辑在这里处理
     * @param t
     */
    @Override
    public void onNext(T t) {
        // token验证失败，重新登陆
        _onNext(t);
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        if (isShow){
            hintDialog();
        }
        String err ;//返回错误原因
        if (e instanceof ServerException){
            err=e.getMessage();
        }else if (!NetworkUtils.isAvailableByPing()){
            err=mContext.get().getResources().getString(R.string.network_is_unavailable);
        }else {
            err=mContext.get().getResources().getString(R.string.request_fail_please_try_again);
        }
        _onError(err);
    }

    @Override
    public void onComplete() {
//        if (compositeDisposable != null)
//            compositeDisposable.clear();
//        Log.e(TAG, "onComplete: " );
        //LoadingDialogManager.getLoadingDialog().hideDialog();
        if (isShow){
            hintDialog();
        }
    }

    private void hintDialog() {
        if (loadingDialog!=null){
            loadingDialog.close();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        /*if (mContext != null) {
            LoadingDialogManager.getLoadingDialog().showDialog(mContext);
        }*/
        //获取context
        if (isShow){
            initDialog();
        }
    }

    private void initDialog() {
        Context context = mContext.get();
        if (context==null){
            return;
        }
        loadingDialog = new LoadingDialog(context);
        loadingDialog.setLoadingText(msg)
                .setInterceptBack(false)
                .show();
    }

    protected abstract void _onNext(T t);

    /**
     * 错误处理，需要的话重写这个方法
     */
    protected void _onError(String err){
        if (!TextUtils.isEmpty(err)) {
            ToastUtils.showShort(err);
        }
    }

}

