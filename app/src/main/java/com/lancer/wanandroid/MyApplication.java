package com.lancer.wanandroid;

import android.app.Application;
import android.content.Context;

/**
 * created by Lancer on 2019/1/4
 * desc
 */
public class MyApplication extends Application {
    private Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public Context getAppContext() {
        mContext = getApplicationContext();
        return mContext;
    }
}
