package com.magicsoft.template2.contract;

import com.magicsoft.template2.base.BasePresenter;
import com.magicsoft.template2.base.BaseView;

/**
 * -----------------------------------------------------------------
 * Copyright (C) 2014-2016, by your company, All rights reserved.
 * -----------------------------------------------------------------
 *
 * @File: MainContract.java
 * @Author: winding.kiwi.lss
 * @Version: V100R001C01
 * @Create: 2017/11/24 10:50
 * @Changes (from 2017/11/24)
 * -----------------------------------------------------------------
 * 2017/11/24 : Create MainContract.java (winding);
 * -----------------------------------------------------------------
 * @description ${DESCRIPTION}
 */

public interface MainContract {

    interface View extends BaseView{

    }
    abstract class Present extends BasePresenter<View>{

    }
}
