package com.magicsoft.template2.base;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.WindowManager;

import com.magicsoft.template2.manager.ActivityCollector;
import com.magicsoft.template2.utils.general.TUtils;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;


public abstract class BaseActivity<V, T extends BasePresenter<V>> extends RxAppCompatActivity {
    public  String TAG = "MMM";
    public  boolean isLog=true;
    public T mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        doBeforeSetContenetView();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        if (getLayoutId() != 0){
            setContentView(getLayoutId());
        }
        //设置屏幕方向
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        ButterKnife.bind(this);
        //EventBus.getDefault().register(this);
        mPresenter = initPresenter();
        if (mPresenter != null){
            mPresenter.attach((V) this);
        }
        ActivityCollector.getInstance().addActivity(this);
        setUpView();


    }

    public void toast(String msg){
        TUtils.show(msg);
    }


    /**进入另一个界面
     * @param
     */
    public void gotoActivity(Class clz){
        startActivity(new Intent(this,clz));
    }
    public void gotoActivity(Class clz, String key, String value){
        Intent intent = new Intent(this, clz);
        intent.putExtra(key,value);
        startActivity(intent);
    }
    public void gotoActivity(Class clz, int requestCode){
        startActivityForResult(new Intent(this,clz),requestCode);
    }

    /**
     * 界面操作
     */
    protected abstract void setUpView();

    /**
     * 设置界面之前的操作
     */
    private void doBeforeSetContenetView() {
    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        //if (mPresenter != null) mPresenter.attach((V) this);
////        if(getRequestedOrientation()!= ActivityInfo.SCREEN_ORIENTATION_USER_PORTRAIT){
////            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_USER_PORTRAIT);
////        }
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) mPresenter.detach();
        EventBus.getDefault().unregister(this);
        ActivityCollector.getInstance().removeActivity(this);
    }

    public abstract T initPresenter();

    /**
     * 对应的布局.
     */
    @LayoutRes
    public abstract int getLayoutId();



}
