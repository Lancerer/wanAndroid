package com.lancer.wanandroid;

import android.app.Application;
import android.content.Context;

import com.squareup.leakcanary.LeakCanary;

/**
 * created by Lancer on 2019/1/4
 * desc
 */
public class MyApplication extends Application {

    private static MyApplication myApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
        //初始化leakcanary
        LeakCanary.install(this);
    }

    public static Context getAppContext() {
        return myApplication.getApplicationContext();
    }

    public static MyApplication getInstance() {
        return myApplication;
    }
}
