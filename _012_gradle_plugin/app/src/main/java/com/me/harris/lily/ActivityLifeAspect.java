package com.me.harris.lily;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;

@Aspect
public class ActivityLifeAspect {

    private static final String TAG = ActivityLifeAspect.class.getSimpleName();

    @Before("execution (* com.me.harris.lily.MainActivity.onCreate(..))")
    public void adviceOnCreate(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Toast.makeText((Context) joinPoint.getTarget(), "aspectj"+signature.toShortString(), Toast.LENGTH_SHORT).show();
        Log.e(TAG, ""+joinPoint.getTarget());
    }

    @Before("execution (* com.me.harris.lily.SecondActivity.onCreate(..))")
    public void adviceOnCreate2(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Toast.makeText((Context) joinPoint.getTarget(), "aspectj"+signature.toShortString(), Toast.LENGTH_SHORT).show();
        Log.e(TAG, ""+joinPoint.getTarget());
    }

}
