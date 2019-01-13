package com.android.alex.secondweektest.data.utils;

import com.android.alex.secondweektest.data.interceptor.LogInterceptor;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * @author Alex
 * @date 2019/1/7.
 * GitHub：https://github.com/wangshuaialex
 */
public class OKHttpUtil {
    private static OKHttpUtil okHttpUtil;
    private final OkHttpClient client;

    private OKHttpUtil() {
        client = new OkHttpClient.Builder().
                addInterceptor(new LogInterceptor()).
                build();
    }

    public static OKHttpUtil getInstance() {
        if (null == okHttpUtil) {
            synchronized (OKHttpUtil.class) {
                if (null == okHttpUtil) {
                    okHttpUtil = new OKHttpUtil();
                }
            }
        }
        return okHttpUtil;
    }

    public void get(String urlString, Callback callback) {
        Request request = new Request.Builder()
                .url(urlString).build();
        client.newCall(request).enqueue(callback);
    }
}
