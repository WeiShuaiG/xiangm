package com.umeng.soexample.utils;

import java.util.concurrent.TimeUnit;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by W on 2018/12/28.
 */

public class OKHttpUtils {

    private OkHttpClient okHttpClient;

    private OKHttpUtils() {
        //日志拦截器
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .callTimeout(20, TimeUnit.SECONDS)
                .addInterceptor(loggingInterceptor)
                .build();
    }

    public static OKHttpUtils getInstance() {
        return OkHolder.okUtils;
    }

    static class OkHolder {
        private static final OKHttpUtils okUtils = new OKHttpUtils();
    }
    //get
    public void getAsync(String url, Callback callback) {
        Request request = new Request.Builder().url(url).build();
        okHttpClient.newCall(request).enqueue(callback);
    }

    //post
    public void postAsync(String url, Callback callback) {
        RequestBody body = new FormBody.Builder().add("key", "value").build();
        Request request = new Request.Builder().url(url).post(body).build();
        okHttpClient.newCall(request).enqueue(callback);
    }


}
