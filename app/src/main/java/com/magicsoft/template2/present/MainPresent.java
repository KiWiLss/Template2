package com.magicsoft.template2.present;

import com.magicsoft.template2.api.Api;
import com.magicsoft.template2.contract.MainContract;
import com.magicsoft.template2.model.demo.MessageList;
import com.magicsoft.template2.utils.general.LUtils;
import com.magicsoft.template2.utils.http.RxSubUtils;
import com.magicsoft.template2.utils.http.RxUtils;

/**
 * -----------------------------------------------------------------
 * Copyright (C) 2014-2016, by your company, All rights reserved.
 * -----------------------------------------------------------------
 *
 * @File: MainPresent.java
 * @Author: winding.kiwi.lss
 * @Version: V100R001C01
 * @Create: 2017/11/24 10:53
 * @Changes (from 2017/11/24)
 * -----------------------------------------------------------------
 * 2017/11/24 : Create MainPresent.java (winding);
 * -----------------------------------------------------------------
 * @description ${DESCRIPTION}
 */

public class MainPresent extends MainContract.Present{


    @Override
    public void getTestData() {
        Api.getApiService().getMessageList("ef56e5cc-3d87-42ae-aeb4-50c958b9126e"
                ,"mj67887u","0","10")
                .compose(this.bindToLifecycle())
                .compose(RxUtils.handleResult2())
                .subscribe(new RxSubUtils<MessageList>(mContext) {
                    @Override
                    protected void _onNext(MessageList messageList) {
                        LUtils.ee(messageList);
                        mView.getTestData(messageList);
                    }
                });
    }
}
