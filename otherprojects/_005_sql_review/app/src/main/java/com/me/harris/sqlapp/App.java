package com.me.harris.sqlapp;

import android.app.Application;
import android.content.Context;

public class App extends Application {
    static Context mApplicationContxt;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplicationContxt = getApplicationContext();
    }

    public static Context getApplicationContxt() {
        return mApplicationContxt;
    }
}
