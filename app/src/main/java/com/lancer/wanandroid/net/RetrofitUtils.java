package com.lancer.wanandroid.net;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.lancer.wanandroid.util.Constant;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * created by Lancer on 2019/1/5
 * desc
 *
 * @author Lancer
 */
public class RetrofitUtils {
    private static long CONNECT_TIMEOUT = 60L;

    public static OkHttpClient mOkHttpClient = new OkHttpClient.Builder()
            .cookieJar(new CookiesManager())
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .build();

    public static <T> T create(Class<T> cls) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .client(mOkHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit.create(cls);
    }
}
