package com.me.harris.routercore;

import android.app.Activity;
import android.content.Intent;
import androidx.annotation.NonNull;

import java.util.HashMap;

public class Routers {
    public static Routers INSTANCE;

    private Routers() {

    }

    private static HashMap<String, Class<? extends Activity>> sMapping = new HashMap<>();

    public static void map(String path,Class<? extends Activity> ac){
        sMapping.put(path, ac);
    }

    public static boolean navigate(@NonNull Activity context, String path) {
        if (sMapping.containsKey(path)) {
            Class<? extends Activity> clazz = sMapping.get(path);
            Intent intent = new Intent(context, clazz);
            context.startActivity(intent);
            return true;
        }
        return false;
    }
}
