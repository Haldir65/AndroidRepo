package com.me.harris.lazydex;

import android.text.TextUtils;
import android.util.Log;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by Fermi on 2018/3/11.
 */

public class ReflectionHelper {
    public static final String TAG = ReflectionHelper.class.getSimpleName();

    static Method getMethod(Class<?> clz, final String mtdName, Class<?>[] mtdArgs) {
        if (clz == null || TextUtils.isEmpty(mtdName) || mtdArgs == null) {
            return null;
        }
        Method mtd = null;
        try {
            mtd = clz.getDeclaredMethod(mtdName, mtdArgs);
            mtd.setAccessible(true);
        } catch (NoSuchMethodException e) {
            Log.d(TAG, e.getMessage(), e);
        }
        return mtd;
    }

    static Method getMethod(final Class<?> clz, final String mtdName) {
        if (clz == null || TextUtils.isEmpty(mtdName)) {
            return null;
        }
        Method mtd = null;
        try {
            for (Method m : clz.getDeclaredMethods()) {
                if (m.getName().equals(mtdName)) {
                    mtd = m;
                    mtd.setAccessible(true);
                    break;
                }
            }
        } catch (Exception e) {
            Log.d(TAG, e.getMessage(), e);
        }
        return mtd;
    }

    static Field getField(Class<?> clz, final String fldName) {
        if (clz == null || TextUtils.isEmpty(fldName)) {
            return null;
        }
        Field fld = null;
        try {
            fld = clz.getDeclaredField(fldName);
            fld.setAccessible(true);
        } catch (NoSuchFieldException e) {
            Log.d(TAG, e.getMessage(), e);
        }
        return fld;
    }
}
