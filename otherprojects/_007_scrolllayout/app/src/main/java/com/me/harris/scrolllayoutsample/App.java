package com.me.harris.scrolllayoutsample;

import android.app.Application;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

public class App extends Application {

    public static OkHttpClient client;

    @Override
    public void onCreate() {
        super.onCreate();
        client = new OkHttpClient.Builder()
                .readTimeout(15, TimeUnit.SECONDS)
                .connectTimeout(15, TimeUnit.SECONDS)
                .build();
    }
}
