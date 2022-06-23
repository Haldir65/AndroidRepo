package com.me.harris.gridsample;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * Class containing some static utility methods.
 */
public class Utils {

    private static final String HTTP_CACHE_DIR = "http";

    public static final File getHttpCacheDir(Context context) {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return new File(context.getExternalCacheDir(), HTTP_CACHE_DIR);
        }
        return new File(context.getCacheDir(), HTTP_CACHE_DIR);
    }

    private Utils() {
    }

    ;


    @TargetApi(VERSION_CODES.HONEYCOMB)
    public static void enableStrictMode() {
        if (Build.VERSION.SDK_INT >= VERSION_CODES.GINGERBREAD) {
            StrictMode.ThreadPolicy.Builder threadPolicyBuilder =
                    new StrictMode.ThreadPolicy.Builder()
                            .detectAll()
                            .penaltyLog();
            StrictMode.VmPolicy.Builder vmPolicyBuilder =
                    new StrictMode.VmPolicy.Builder()
                            .detectAll()
                            .penaltyLog();

            /*if (Utils.hasHoneycomb()) {
                threadPolicyBuilder.penaltyFlashScreen();
                vmPolicyBuilder
                        .setClassInstanceLimit(ImageGridActivity.class, 1)
                        .setClassInstanceLimit(ImageDetailActivity.class, 1);
            }*/
            StrictMode.setThreadPolicy(threadPolicyBuilder.build());
            StrictMode.setVmPolicy(vmPolicyBuilder.build());
        }
    }


    public static boolean isNetWorkConnected(Context context) {
        ConnectivityManager connMgr = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

    public static float getDensity(Context context) {
        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager wm = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE));
        wm.getDefaultDisplay().getMetrics(metrics);

        return metrics.density;
    }


    /**
     * 通过Uri获取图片路径
     */
    public static String query(Context context, Uri uri) {
        if (context == null || uri == null) return "";
        Cursor cursor = context.getContentResolver().query(uri, new String[]{MediaStore.Images.ImageColumns.DATA}, null, null, null);
        cursor.moveToNext();
        return cursor.getString(cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA));
    }


   /* public  static <T> List<T> copyArrayList( List<T> origin ,int copyTimes) {
         for (int i = 0; i < 10; i++) {
                List<T> dst = new ArrayList<>(  Arrays.asList(new T[origin.size()]));
                Collections.copy(dst, origin);
                origin.addAll(dst);
            }
    }*/

    @SuppressWarnings("unused")
    private static String buildTransaction(final String type) {
        return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
    }





    public static int getScreenWidth(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        int w_screen = dm.widthPixels;
        return w_screen;
    }

    public static int getScreenHeight(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        int h_screen = dm.heightPixels;
        return h_screen;
    }


    /**
     * 把所有的setOnClickListener方法放到这里,不负责也不应该负责判断空指针
     *
     * @param impl
     * @param views
     */
    public static void setOnClickListeners(View.OnClickListener impl, View... views) {
        // for (View view : images)
        for (int i = 0; i < views.length; i++) {//for i 的方式比迭代要快，因为...传进去的参数是数组而不是列表
            views[i].setOnClickListener(impl);
        }
    }

    public static void setAccessibilityIgnore(View view) {
        view.setClickable(false);
        view.setFocusable(false);
        view.setContentDescription("");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            view.setImportantForAccessibility(View.IMPORTANT_FOR_ACCESSIBILITY_NO);
        }
    }

    public static final View inflate(int resId, ViewGroup parent) {
        return LayoutInflater.from(parent.getContext()).inflate(resId, parent, false);
    }


    public static int getNavigationBarHeight(Context context) {
        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
        int height = resources.getDimensionPixelSize(resourceId);
        return height;
    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    public static boolean hasNavigationBar(Context activity) {
        boolean hasNavigationBar = false;
        Resources rs = activity.getResources();
        int id = rs.getIdentifier("config_showNavigationBar", "bool", "android");
        if (id > 0) {
            hasNavigationBar = rs.getBoolean(id);
        }
        try {
            Class systemPropertiesClass = Class.forName("android.os.SystemProperties");
            Method m = systemPropertiesClass.getMethod("get", String.class);
            String navBarOverride = (String) m.invoke(systemPropertiesClass, "qemu.hw.mainkeys");
            if ("1".equals(navBarOverride)) {
                hasNavigationBar = false;
            } else if ("0".equals(navBarOverride)) {
                hasNavigationBar = true;
            }
        } catch (Exception e) {
        }

        return hasNavigationBar;
    }

    public static int getStatusBarHeight(Context context) {
        //计算标题栏高度
        Class<?> c = null;
        Object obj = null;
        Field field = null;
        int x = 0, sbar = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            sbar = context.getResources().getDimensionPixelSize(x);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return sbar;
    }

    public static int getStatusBarHeight() {
        Resources res = Resources.getSystem();
        int resId = res.getIdentifier("status_bar_height", "dimen", "android");
        if (resId > 0) {
            return res.getDimensionPixelSize(resId);
        }
        return 0;
    }

    /**
     * 仅用于设置Gone和visible,对于invisible的情况不要用,空指针判断不应该放在这个方法里
     *
     * @param isShow
     * @param views
     */
    public static void setVisibileOrGoneForViewsArray(boolean isShow, View... views) {
        int targetVisibility = isShow ? View.VISIBLE : View.GONE;
        for (int i = 0; i < views.length; i++) {
            if (views[i].getVisibility() != targetVisibility) {
                views[i].setVisibility(targetVisibility);
            }
        }
    }


    /**
     * 仅用于设置invisible和visible,对于gone的情况不要用,空指针判断不应该放在这个方法里
     *
     * @param isShow
     * @param views
     */
    public static void setVisibleOrInvisibleForViewsArray(boolean isShow, View... views) {
        int targetVisibility = isShow ? View.VISIBLE : View.INVISIBLE;
        for (int i = 0; i < views.length; i++) {
            if (views[i].getVisibility() != targetVisibility) {
                views[i].setVisibility(targetVisibility);
            }
        }
    }

    public static void launchActivity(Context context, Class<?> activity) {
        Intent intent = new Intent(context, activity);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        context.startActivity(intent);
    }

    public static void launchActivity(Context context, Class<?> activity, Bundle bundle) {
        Intent intent = new Intent(context, activity);
        intent.putExtras(bundle);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        context.startActivity(intent);
    }

    public static void launchActivity(Context context, Class<?> activity, String key, int value) {
        Bundle bundle = new Bundle();
        bundle.putInt(key, value);
        launchActivity(context, activity, bundle);
    }

    public static void launchActivity(Context context, Class<?> activity, String key, String value) {
        Bundle bundle = new Bundle();
        bundle.putString(key, value);
        launchActivity(context, activity, bundle);
    }

    public static void launchActivityForResult(Activity context, Class<?> activity, int requestCode) {
        Intent intent = new Intent(context, activity);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        context.startActivityForResult(intent, requestCode);
    }

    @SuppressLint("SimpleDateFormat")
    public static Uri launchActivityForResult(Activity activity, Intent intent, int requestCode) {
        SimpleDateFormat timeStampFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
        String filename = timeStampFormat.format(new Date());
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, filename);
        Uri photoUri = activity.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
        activity.startActivityForResult(intent, requestCode);
        return photoUri;
    }

    public static void launchService(Context context, Class<?> service) {
        Intent intent = new Intent(context, service);
        context.startService(intent);
    }

    public static void stopService(Context context, Class<?> service) {
        Intent intent = new Intent(context, service);
        context.stopService(intent);
    }


    public static int getWidthPixels(Activity activity) {
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }

    public static int getHeightPixels(Activity activity) {
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.heightPixels;
    }

    /**
     * 判断字符串是否为空
     *
     * @param text
     * @return true null false !null
     */
    public static boolean isNull(CharSequence text) {
        if (text == null || "".equals(text.toString().trim()) || "null".equals(text))
            return true;
        return false;
    }


    /**
     * 判断列表是否为空
     *
     * @param list
     * @return true null false !null
     */
    @SuppressWarnings("rawtypes")
    public static boolean isListEmpty(List list) {
        if (list == null || list.size() == 0) return true;
        return false;
    }


    /**
     * 将px值转换为dip或dp值，保证尺寸大小不变
     *
     * @param pxValue
     * @return
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 将dip或dp值转换为px值，保证尺寸大小不变
     *
     * @param dipValue
     * @return
     */
    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    /**
     * 将px值转换为sp值，保证文字大小不变
     *
     * @param pxValue
     * @return
     */
    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     * @param spValue
     * @return
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    public static int getScreenHeightWithDecorations(Context context) {
        int heightPixels;
        WindowManager w = ((Activity) context).getWindowManager();
        Display d = w.getDefaultDisplay();
        android.graphics.Point realSize = new android.graphics.Point();
        try {
            Display.class.getMethod("getRealSize", android.graphics.Point.class).invoke(d, realSize);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        heightPixels = realSize.y;
        return heightPixels;
    }


    public static  boolean isNetWorkAvailable(Context context) {
        ConnectivityManager connMgr = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }



}