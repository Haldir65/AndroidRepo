package com.me.harris.lazydex;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import com.github.jllk.multidex.JLLKMultiDexInstaller;

import java.lang.reflect.Method;

/**
 * Created by Fermi on 2018/3/11.
 */

public class InstrumentationProxy extends Instrumentation{
    public static final String TAG = InstrumentationProxy.class.getSimpleName();
    private Method mMtd_execStartActivity;

    private Instrumentation mInstrumentation;

    public InstrumentationProxy(Instrumentation instrumentation) {
        mInstrumentation = instrumentation;
        mMtd_execStartActivity = ReflectionHelper.getMethod(mInstrumentation.getClass(), "execStartActivity");
    }

    private ActivityResult execStartActivityProxy(Context who, Intent intent, IExecStartActivityDelegate delegate) {
        String className = null;
        if (intent.getComponent().getClassName() == null) {
            ResolveInfo resolveInfo = getContext().getPackageManager()
                    .resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY);
            if (resolveInfo != null) {
                className = resolveInfo.activityInfo.name;
            }
        } else {
            className = intent.getComponent().getClassName();
        }
        Log.d(TAG, "[execStartActivityProxy] " + className);

        // Install secondary dex by index.
        int dexIdx = MultiDexHook.getModuleDexIdx(className);
        if (dexIdx > 0) {
            JLLKMultiDexInstaller.installOne(who, dexIdx);
        }
        return delegate.execStartActivity();
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public ActivityResult execStartActivity(
            Context who,
            IBinder contextThread,
            IBinder token,
            Activity target,
            Intent intent,
            int requestCode,
            Bundle bundle) {
        return execStartActivityProxy(
                who,
                intent,
                new ExecStartActivityDelegateV17(who, contextThread, token, target,
                        intent, requestCode, bundle));
    }

    class ExecStartActivityDelegateV17 implements IExecStartActivityDelegate {

        final Context who;
        final IBinder contextThread;
        final IBinder token;
        final Activity target;
        final Intent intent;
        final int requestCode;
        final Bundle bundle;

        public ExecStartActivityDelegateV17(
                Context who,
                IBinder contextThread,
                IBinder token,
                Activity target,
                Intent intent,
                int requestCode,
                Bundle bundle){
            this.who = who;
            this.bundle = bundle;
            this.token = token;
            this.target = target;
            this.intent = intent;
            this.requestCode = requestCode;
            this.contextThread = contextThread;
        }

        @Override
        public ActivityResult execStartActivity() {
            try {
                Log.e("=====================", "starting Activity");
                return (ActivityResult) mMtd_execStartActivity.invoke(
                        mInstrumentation, who, contextThread, token,
                        target, intent, requestCode, bundle);
            } catch (Exception e) {
                Log.d(TAG, e.getMessage(), e);
            }
            return null;
        }
    }

    interface IExecStartActivityDelegate {
        ActivityResult execStartActivity();
    }
}
