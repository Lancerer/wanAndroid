package com.lancer.wanandroid;

import android.app.Application;
import android.content.Context;

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
    }

    public static Context getAppContext() {
        return myApplication.getApplicationContext();
    }

    public static MyApplication getInstance() {
        return myApplication;
    }
}
