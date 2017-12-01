package com.magicsoft.template2.api;

import com.magicsoft.template2.config.N;
import com.magicsoft.template2.model.AppBack2;
import com.magicsoft.template2.model.HttpResult;
import com.magicsoft.template2.model.demo.MessageList;
import com.magicsoft.template2.model.demo.TestBean;

import java.util.Map;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * -----------------------------------------------------------------
 * Copyright (C) 2014-2016, by your company, All rights reserved.
 * -----------------------------------------------------------------
 *
 * @File: ApiService.java
 * @Author: winding.kiwi.lss
 * @Version: V100R001C01
 * @Create: 2017/11/24 10:11
 * @Changes (from 2017/11/24)
 * -----------------------------------------------------------------
 * 2017/11/24 : Create ApiService.java (winding);
 * -----------------------------------------------------------------
 * @description ${DESCRIPTION}
 */

public interface ApiService {
    //获取消息列表
    @POST(N.MESSAGE_LIST)
    Flowable<HttpResult<MessageList>> getMessageList(@Query("token") String token
            , @Query("member.id")String id
            , @Query("page")String page, @Query("size")String size);

    @POST(N.MESSAGE_LIST)
    Flowable<HttpResult<Map<String,Object>>> getMessageList2(@Query("token") String token
            , @Query("member.id")String id
            , @Query("page")String page, @Query("size")String size);


    @GET
    Flowable<AppBack2<Map<String,Object>>> getUrl(@Url String url);

    @GET
    Flowable<AppBack2<TestBean>> getUrl2(@Url String url);

    @POST
    //@FormUrlEncoded
    Observable<Object> sendSMS(@Url String url);
}
