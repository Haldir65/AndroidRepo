package com.me.harris.androidanimations._044_plugin_sample;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;

public class PluginManager {
    private static PluginManager ourInstance = new PluginManager();
    private Context context;

    private DexClassLoader pluginDexClassLoader;
    private Resources pluginResources;

    public PackageInfo getPluginPackageArchiveInfo() {
        return pluginPackageArchiveInfo;
    }

    private PackageInfo pluginPackageArchiveInfo;

    public static PluginManager getInstance() {
        return ourInstance;
    }

    private PluginManager() {
    }

    public void setContext(Context context) {
        this.context = context.getApplicationContext();
    }

    public void loadApk(String dexPath) {
        //(String dexPath, String optimizedDirectory, String librarySearchPath, ClassLoader parent)
        pluginDexClassLoader = new DexClassLoader(dexPath, context.getDir("dex", Context.MODE_PRIVATE).getAbsolutePath(), null, context.getClassLoader());

        pluginPackageArchiveInfo = context.getPackageManager().getPackageArchiveInfo(dexPath, PackageManager.GET_ACTIVITIES);

        //Resources(AssetManager assets, DisplayMetrics metrics, Configuration config) {
        AssetManager assets = null;
        try {
            assets = AssetManager.class.newInstance();
            Method addAssetPath = AssetManager.class.getMethod("addAssetPath", String.class);
            addAssetPath.invoke(assets, dexPath);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        pluginResources = new Resources(assets,
                context.getResources().getDisplayMetrics(),
                context.getResources().getConfiguration());
    }


    public DexClassLoader getPluginDexClassLoader() {
        return pluginDexClassLoader;
    }


    public Resources getPluginResources() {
        return pluginResources;
    }
//
//    作者：徐爱卿
//    链接：https://www.jianshu.com/p/a4ab102fa4ac
//    來源：简书
//    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
}
