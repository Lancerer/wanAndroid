package com.lancer.wanandroid.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * created by Lancer on 2019/1/14
 * desc
 */
public class Sputils {
    public static void setInt(Context context, String key, int value) {
        SharedPreferences sp = context.getSharedPreferences(Constant.CONFIGFILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putInt(key, value);
        edit.commit();
    }

    public static int getInt(Context context, String key, int defValue) {
        SharedPreferences sp = context.getSharedPreferences(Constant.CONFIGFILE, Context.MODE_PRIVATE);
        int value = sp.getInt(key, defValue);
        return value;
    }

    public static Boolean getBoolean(Context context, String key, boolean defValue) {
        SharedPreferences sp = context.getSharedPreferences(Constant.CONFIGFILE, Context.MODE_PRIVATE);
        boolean spBoolean = sp.getBoolean(key, defValue);
        return spBoolean;
    }

    public static void setBoolean(Context context, String key, boolean Value) {
        SharedPreferences sp = context.getSharedPreferences(Constant.CONFIGFILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(key, Value).commit();
    }

    public static String getString(Context context, String key, String defvalue) {
        SharedPreferences sp = context.getSharedPreferences(Constant.CONFIGFILE, Context.MODE_PRIVATE);
        String string = sp.getString(key, defvalue);
        return string;
    }

    public static void setString(Context context, String key, String Value) {
        SharedPreferences sp = context.getSharedPreferences(Constant.CONFIGFILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, Value).commit();
    }


}
