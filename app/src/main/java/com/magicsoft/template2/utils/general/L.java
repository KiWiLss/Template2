package com.magicsoft.template2.utils.general;

import com.alibaba.fastjson.JSON;
import com.blankj.utilcode.util.LogUtils;

/**
 * 日志相关工具类
 */
public class L {
    public static String TAG = "MMM";
    public static boolean isLog=true;

    public static void setIsLog(boolean isShow){
        isLog=isShow;
    }

    public static void setTag(String tag){
        TAG=tag;
    }

    public static void d(Object o){
        if (isLog) {
            LogUtils.d(TAG,o.toString());
        }
    }
    public static void i(Object o){
        if (isLog) {
            LogUtils.i(TAG,o.toString());
        }
    }

    public static void e(Object o){
        if (isLog) {
            LogUtils.e(TAG,o.toString());
        }
    }
    public static void w(Object o){
        if (isLog) {
            LogUtils.w(TAG,o.toString());
        }
    }
    public static void json(String o){
        if (isLog) {
            LogUtils.json(TAG,o);
        }
    }
    public static void xml(String o){
        if (isLog) {
            LogUtils.xml(TAG,o);
        }
    }

    /**object --> json type
     * @param o
     */
    public static void ii(Object o){
        if (isLog) {
            LogUtils.i(TAG,JSON.toJSONString(o));
        }
    }
    public static void ee(Object o){
        if (isLog) {
            LogUtils.e(TAG,JSON.toJSONString(o));
        }
    }

}