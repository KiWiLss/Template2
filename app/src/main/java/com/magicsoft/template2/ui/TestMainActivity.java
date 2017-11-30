package com.magicsoft.template2.ui;

import com.magicsoft.template2.R;
import com.magicsoft.template2.api.Api;
import com.magicsoft.template2.base.BaseActivity;
import com.magicsoft.template2.base.BasePresenter;
import com.magicsoft.template2.config.N;
import com.magicsoft.template2.model.demo.TestBean;
import com.magicsoft.template2.utils.general.LUtils;
import com.magicsoft.template2.utils.http.RxSubUtils;
import com.magicsoft.template2.utils.http.RxUtils;

/**
 * -----------------------------------------------------------------
 * Copyright (C) 2014-2016, by your company, All rights reserved.
 * -----------------------------------------------------------------
 *
 * @File: TestMainActivity.java
 * @Author: winding.kiwi.lss
 * @Version: V100R001C01
 * @Create: 2017/11/30 11:52
 * @Changes (from 2017/11/30)
 * -----------------------------------------------------------------
 * 2017/11/30 : Create TestMainActivity.java (winding);
 * -----------------------------------------------------------------
 * @description ${DESCRIPTION}占位置,测试不用present
 */

public class TestMainActivity extends BaseActivity {
    @Override
    protected void setUpView() {

        //网络测试
        Api.getApiService().getUrl2(N.APP_CFG)
                .compose(this.bindToLifecycle())
        .compose(RxUtils.handleResult())
        .subscribe(new RxSubUtils<TestBean>() {
            @Override
            protected void _onNext(TestBean testBean) {
                LUtils.ee(testBean);
            }
        });


    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_test_main;
    }
}
