package com.magicsoft.template2.base;

import android.content.Context;
import android.view.View;



public abstract class BasePresenter<T> {
    protected T mView;
    protected Context mContext;

    /**
     * 注入view.
     */
    public void attach(T mView) {
        this.mView = mView;
        getContext();
    }

    /**
     * 销毁操作
     */
    public void detach() {
        mView = null;
        mContext = null;
    }

    /**
     * 获取context.
     */
    private void getContext() {
        if (mView instanceof BaseActivity) {
            mContext = (Context) mView;
        } else if (mView instanceof BaseFragment) {
            mContext = ((BaseFragment) mView).getActivity();
        } else if (mView instanceof View) {
            mContext = ((View) mView).getContext();
        } else {
            throw new IllegalArgumentException("view must instanceof activity or fragment");
        }
    }


}
