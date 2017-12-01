package com.magicsoft.template2.ui.smms;

import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;

import com.magicsoft.template2.R;
import com.magicsoft.template2.api.Api;
import com.magicsoft.template2.base.BaseActivity;
import com.magicsoft.template2.base.BasePresenter;
import com.magicsoft.template2.config.N;
import com.magicsoft.template2.config.Phone;
import com.magicsoft.template2.utils.general.LUtils;
import com.magicsoft.template2.utils.http.RxSubUtils;
import com.magicsoft.template2.utils.http.RxUtils;

import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * -----------------------------------------------------------------
 * Copyright (C) 2014-2016, by your company, All rights reserved.
 * -----------------------------------------------------------------
 *
 * @File: SmmsActivity.java
 * @Author: winding.kiwi.lss
 * @Version: V100R001C01
 * @Create: 2017/11/30 13:11
 * @Changes (from 2017/11/30)
 * -----------------------------------------------------------------
 * 2017/11/30 : Create SmmsActivity.java (winding);
 * -----------------------------------------------------------------
 * @description ${DESCRIPTION}
 */

public class SmmsActivity extends BaseActivity{
    @BindView(R.id.tv_smms_info)
    TextView tvInfo;
    private CountDownTimer mCdt;

    String phone;
    String mType="车位地图";

    @Override
    protected void setUpView() {
        initTimer();
    }

    private void initTimer() {
        mCdt = new CountDownTimer(1000*60*10,1000*60) {
            @Override
            public void onTick(long l) {
                startGetZHCode();

                startGetCWCode();
            }

            @Override
            public void onFinish() {
                tvInfo.setText("end**");
            }
        };

    }

    private void startGetCWCode() {//不使用封装的用法
        Api.getApiService().sendSMS("http://www.cheweiditu.com/mapi/open/sms/send?" +
                "methods=common&product="+mType+"&mobile="+Phone.ZHANGMIN)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(this.bindToLifecycle())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object jsonObject) throws Exception {
                        mCount++;
                        LUtils.ee(jsonObject);
                        tvInfo.setText("count:"+mCount+",phone="+phone);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        tvInfo.setText(throwable.getMessage());
                    }
                });


    }

    int mCount;
    private void startGetZHCode() {

        Api.getApiService().getUrl(N.ZHURL+N.ZH_PHONE+ Phone.ZHANGMIN)
                .compose(this.bindToLifecycle())
                .compose(RxUtils.handleResult())
                .subscribe(new RxSubUtils<Map<String, Object>>() {
                    @Override
                    protected void _onNext(Map<String, Object> stringObjectMap) {
                        LUtils.ee(stringObjectMap);
                        mCount++;
                        tvInfo.setText("count:"+mCount+",phone="+phone);
                    }
                });



    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mCdt != null) {
            mCdt.cancel();
        }
    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_smms;
    }



    @OnClick({R.id.btn_smms_one, R.id.btn_smms_two, R.id.btn_smms_three
            , R.id.btn_smms_start, R.id.btn_smms_pause})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_smms_one:
                break;
            case R.id.btn_smms_two:
                break;
            case R.id.btn_smms_three:
                break;
            case R.id.btn_smms_start:
                //mCdt.start();
                startGetCWCode();
                break;
            case R.id.btn_smms_pause:
                mCdt.cancel();
                break;
        }
    }
}
