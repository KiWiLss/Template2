package com.magicsoft.template2.manager;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

public class ActivityCollector {
    public static volatile ActivityCollector instance;
    public List<Activity> activityList=new ArrayList<>();//活动集合

    public static ActivityCollector getInstance(){
        if (instance==null){
            synchronized (ActivityCollector.class){
                if (instance==null) {
                    instance=new ActivityCollector();
                }
            }
        }
        return instance;
    }

    public void addActivity(Activity activity){
        if (!activityList.contains(activity)){
            activityList.add(activity);
        }
    }

    public void removeActivity(Activity activity){
        if (activityList.contains(activity)){
            activityList.remove(activity);
            activity.finish();
        }
    }

    public void finishAll(){
        for (int i = 0; i < activityList.size(); i++) {
            if (!activityList.get(i).isFinishing()){
                activityList.get(i).finish();
            }
        }
    }

    public void finishAnyOne(Class clz){//销毁任意一个
        for (int i = 0; i < activityList.size(); i++) {
            if (clz.equals(activityList.get(i).getClass())){
                if (!activityList.get(i).isFinishing()){
                    activityList.get(i).finish();
                }
            }
        }
    }
}