package com.magicsoft.template2.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.ToastUtils;
import com.trello.rxlifecycle2.components.support.RxFragment;

import butterknife.ButterKnife;



public abstract class BaseFragment<V, T extends BasePresenter<V>> extends RxFragment {

    public T mPresenter;
    public View mView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(getLayoutId(), container, false);
        ButterKnife.bind(this, mView);
        initPresenter();
//        Tracup.showIntrMessage(getActivity());//参数类型是Activity
        setUpView();
        return mView;
    }

    public void toast(String msg){
        ToastUtils.showShort(msg);
    }
    /**进入另一个界面
     * @param clz
     */
    public void gotoActivity(Class clz){
        startActivity(new Intent(getContext(),clz));
    }
    public void gotoActivity(Class clz, int requestCode){
        startActivityForResult(new Intent(getContext(),clz),requestCode);
    }

    protected abstract void setUpView();

    @Override
    public void onResume() {
        super.onResume();
        if (mPresenter != null) mPresenter.attach((V) mView);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mPresenter != null) mPresenter.detach();
    }

    @LayoutRes
    public abstract int getLayoutId();

    public abstract T initPresenter();
}
