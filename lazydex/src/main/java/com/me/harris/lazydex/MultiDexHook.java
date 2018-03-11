package com.me.harris.lazydex;

import android.app.Instrumentation;
import android.util.Log;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * Created by Fermi on 2018/3/11.
 */

public class MultiDexHook {
    public static final String TAG = MultiDexHook.class.getSimpleName();

    private static Map<String, Integer> sModule2DexIdx;

    /**
     * Install dex in right time.
     *
     * @param module2DexIdx Module Activity fullName with DexIndex
     */
    public static void lazyInstall(Map<String, Integer> module2DexIdx) {
        if (module2DexIdx == null) {
            throw new IllegalArgumentException("[JLLKMultiDexHook] lazyInstall args can't be null.");
        }
        sModule2DexIdx = module2DexIdx;
        hook();
    }

    static int getModuleDexIdx(String name) {
        int ret = 0;
        if (sModule2DexIdx.containsKey(name)) {
            ret = sModule2DexIdx.get(name);
        }
        Log.d(TAG, "[JLLKMultiDexHook] getModuleDexIdx: " + ret);
        return ret;
    }

    private static void hook() {
        Log.d(TAG, "[JLLKMultiDexHook] hook..");
        try {
            Class<?> clz_ActivityThread =  Class.forName("android.app.ActivityThread");
            Method mtd_currentActivityThread = ReflectionHelper.getMethod(clz_ActivityThread, "currentActivityThread");
            Object obj_sCurrentActivityThread = mtd_currentActivityThread.invoke(null);

            Field fld_mInstrumentation = ReflectionHelper.getField(clz_ActivityThread, "mInstrumentation");
            Object obj_mInstrumentation = fld_mInstrumentation.get(obj_sCurrentActivityThread);

            InstrumentationProxy instrumentationProxy = new InstrumentationProxy((Instrumentation) obj_mInstrumentation);
            fld_mInstrumentation.set(obj_sCurrentActivityThread, instrumentationProxy);

        } catch (Exception e) {
            Log.d(TAG, e.getMessage(), e);
        }
        Log.d(TAG, "[JLLKMultiDexHook] hook done.");
    }
}
