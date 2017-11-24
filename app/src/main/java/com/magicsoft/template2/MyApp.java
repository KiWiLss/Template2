package com.magicsoft.template2;


import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

import com.blankj.utilcode.util.Utils;

public class MyApp extends Application {

    private static Context mContext;
    @SuppressLint("StaticFieldLeak")
    private static MyApp instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;
        mContext = getApplicationContext();
        //初始化工具
        Utils.init(this);




    }

    public static Context getContext(){
        return mContext;
    }

    public static MyApp getInstance(){
        return instance;
    }

}
