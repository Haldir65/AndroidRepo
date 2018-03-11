package com.me.harris.cloak;

import android.app.Application;
import android.content.Context;

import com.me.harris.lazydex.MultiDexHook;

import java.util.HashMap;

/**
 * Created by Fermi on 2018/3/11.
 */

public class App extends Application {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        HashMap<String, Integer> config = new HashMap<>(4);
        config.put(MainActivity.class.getName(), 1);
        config.put(SecondActivity.class.getName(), 2);
        MultiDexHook.lazyInstall(config);

    }
}
