package com.me.harris.androidanimations;

import android.app.Application;

import com.me.harris.androidanimations.utils.CrashHandler;
import com.me.harris.androidanimations.utils.LogUtil;

/**
 * Created by Harris on 2016/10/13.
 */

public class App extends Application {
    public static final String TAG = App.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtil.p("current-process-id is "+android.os.Process.myPid());
        if (!BuildConfig.DEBUG) { // 开发过程中无需打开
            CrashHandler crashHandler = CrashHandler.getInstance();
            crashHandler.init(getApplicationContext());
            //获取到当前线程，设置未捕获异常的处理
            Thread.currentThread().setUncaughtExceptionHandler(crashHandler);
        }
        Runtime runtime = Runtime.getRuntime();
        LogUtil.w(TAG, String.valueOf(toMB(runtime.freeMemory())));
        LogUtil.w(TAG, String.valueOf(toMB(runtime.totalMemory())));


    }

    private String toMB(long number) {
        return String.format("%.2f", number / 1024.0 / 1024.0);
    }
}
