package com.me.harris.androidanimations.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Field;

/**
 * Created by Harris on 2016/10/13.
 */

public class CrashHandler implements Thread.UncaughtExceptionHandler {

    private static final String TAG = "MyCrashHandler";
    private Context context;
    //单例设计模式
    private static CrashHandler myCrashHandler;
    private CrashHandler(){

    }
    public synchronized static CrashHandler getInstance(){
        if(myCrashHandler==null){
            myCrashHandler = new CrashHandler();
        }
        return myCrashHandler;
    }
    //对其进行初始化，后面获取应用相关信息需要使用到上下文
    public void init(Context context){
        this.context = context;
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        //1.获取应用程序版本信息
        StringBuilder sb = new StringBuilder();
        PackageManager pm = context.getPackageManager();
        try {
            PackageInfo info = pm.getPackageInfo(context.getPackageName(), 0);
            String versionName = info.versionName;
            sb.append("程序版本号为:").append(versionName);
            sb.append("\n");
            //2.获取手机硬件信息
            Build build = new Build();//手机硬件信息封装在Builde中，通过反射获取其字段，包括私有 暴力破解
            Field[] fields = build.getClass().getDeclaredFields();
            for(int i=1;i<fields.length;i++){
                //暴力访问
                fields[i].setAccessible(true);
                String name = fields[i].getName();
                String value = fields[i].get(null).toString();
                sb.append(name + " = " + value);
                sb.append("\n");
            }
            //3.获取异常报错信息
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            ex.printStackTrace(pw);
            String errorInfo = sw.toString();
            sb.append(errorInfo);
            Log.i(TAG, sb.toString());
            //4.上传到服务器（略 ）
        } catch (Exception e) {
            e.printStackTrace();
        }
        //最后让应用程序自杀
        android.os.Process.killProcess(android.os.Process.myPid());
    }
}
