package com.magicsoft.template2;

import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.magicsoft.template2.api.Api;
import com.magicsoft.template2.base.BaseActivity;
import com.magicsoft.template2.contract.MainContract;
import com.magicsoft.template2.model.demo.MessageList;
import com.magicsoft.template2.present.MainPresent;
import com.magicsoft.template2.ui.TestMainActivity;
import com.magicsoft.template2.ui.smms.SmmsActivity;
import com.magicsoft.template2.utils.general.LUtils;
import com.magicsoft.template2.utils.http.RxSubUtils;
import com.magicsoft.template2.utils.http.RxUtils;

import butterknife.OnClick;

public class MainActivity extends BaseActivity<MainContract.View, MainContract.Present> implements MainContract.View {

    private CountDownTimer countDownTimer;

    @Override
    protected void setUpView() {




        Api.getApiService().getMessageList("ef56e5cc-3d87-42ae-aeb4-50c958b9126"
                ,"mj67887u","0","10")
                .compose(RxUtils.handleResult2())
                .subscribe(new RxSubUtils<MessageList>(this) {
                    @Override
                    protected void _onNext(MessageList messageList) {
                        LUtils.ee(messageList);
                        LUtils.e("$$$"+ JSON.toJSON(messageList));
                    }

                    @Override
                    protected void _onError(String err) {
                        //super._onError(err);
                        LUtils.e(err);
                    }
                });

       /* LUtils.i("9999999999999");
        LUtils.e("&&&&&&&&&&&&&&&&");
        LogUtils.e("MMM","$$$$$$$$$$");
        LogUtils.eTag("MMM","&&&&&&&&&");

        Api.getApiService().getMessageList("b02387d0-be44-482b-a378-6a7346dc8dee"
                ,"mjms98nh","0","10")
                .compose(RxUtils.handleResult2())
                .subscribe(new RxSubUtils<MessageList>() {
                    @Override
                    protected void _onNext(MessageList messageList) {
                        LUtils.ee(messageList);
                        LUtils.e("$$$"+JSON.toJSON(messageList));
                    }


                });


        Api.getApiService().getMessageList2("b02387d0-be44-482b-a378-6a7346dc8dee"
                ,"mjms98nh","0","10")
                .compose(RxUtils.handleResult2())
                .subscribe(new RxSubUtils<Map<String, Object>>() {
                    @Override
                    protected void _onNext(Map<String, Object> stringObjectMap) {
                        LUtils.e("*****"+JSON.toJSON(stringObjectMap));
                        toast("$$");

                    }
                });

        Api.getApiService().getUrl2(N.APP_CFG)
                .compose(this.bindToLifecycle())
                .compose(RxUtils.handleResult())
                .subscribe(new RxSubUtils<TestBean>() {
                    @Override
                    protected void _onNext(TestBean testBean) {
                        LUtils.ee(testBean);
                    }
                });*/

        /*countDownTimer = new CountDownTimer(1000*60*10,1000*60) {
            @Override
            public void onTick(long l) {
                Log.e(TAG, "onTick: "+(l/1000));
            }

            @Override
            public void onFinish() {

            }
        };

        countDownTimer.start();*/

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer!=null){
            countDownTimer.cancel();
        }
        Log.e(TAG, "onDestroy: ");
    }

    @Override
    public MainContract.Present initPresenter() {
        return new MainPresent();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }


    @OnClick({R.id.btn_main_smms, R.id.btn_main_smms2,R.id.btn_main_test})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_main_smms:
                gotoActivity(TestMainActivity.class);
                break;
            case R.id.btn_main_smms2:
                gotoActivity(SmmsActivity.class);
                break;
            case R.id.btn_main_test://测试网络数据
                mPresenter.getTestData();
                break;
        }
    }

    /**
     * 点击获取测试数据
     * @param messageList
     */
    @Override
    public void getTestData(MessageList messageList) {
        LUtils.ee(messageList);
    }
}
