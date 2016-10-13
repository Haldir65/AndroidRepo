package com.me.harris.androidanimations;

import android.app.Application;

import com.me.harris.androidanimations.utils.CrashHandler;

/**
 * Created by Harris on 2016/10/13.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        if (!BuildConfig.DEBUG) { // 开发过程中无需打开
            CrashHandler crashHandler = CrashHandler.getInstance();
            crashHandler.init(getApplicationContext());
            //获取到当前线程，设置未捕获异常的处理
            Thread.currentThread().setUncaughtExceptionHandler(crashHandler);
        }
    }
}
