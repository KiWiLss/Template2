package com.magicsoft.template2.api;

import com.magicsoft.template2.utils.http.RetrofitFactory;

/**
 * -----------------------------------------------------------------
 * Copyright (C) 2014-2016, by your company, All rights reserved.
 * -----------------------------------------------------------------
 *
 * @File: Api.java
 * @Author: winding.kiwi.lss
 * @Version: V100R001C01
 * @Create: 2017/11/24 10:12
 * @Changes (from 2017/11/24)
 * -----------------------------------------------------------------
 * 2017/11/24 : Create Api.java (winding);
 * -----------------------------------------------------------------
 * @description ${DESCRIPTION}
 */

public class Api {
    private static ApiService apiService;
    public static ApiService getApiService(){
        if (apiService==null){
            apiService = RetrofitFactory.getRetrofit().create(ApiService.class);
        }
        return apiService;
    }
}
