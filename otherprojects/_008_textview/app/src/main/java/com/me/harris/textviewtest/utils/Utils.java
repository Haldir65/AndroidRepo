package com.me.harris.textviewtest.utils;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by Harris on 2016/6/18.
 */

public class Utils {


    public static float getDensity(Context context) {
        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager wm = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE));
        wm.getDefaultDisplay().getMetrics(metrics);

        return metrics.density;
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     *
     * @param context 上下文
     * @param dpValue dp
     * @return px
     */
    public static int dip2px(ContextThemeWrapper context, float dpValue) {
        return (int) (dpValue * getDensity(context) + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     *
     * @param context 上下文
     * @param pxValue px
     * @return dp
     */
    public static int px2dip(ContextThemeWrapper context, float pxValue) {
        return (int) (pxValue / getDensity(context) + 0.5f);
    }

    /**
     * 获取当前手机的屏幕像素密度
     *
     * @param context 上下文
     * @return 像素密度
     */
    public static float getDensity(ContextThemeWrapper context) {
        return context.getResources().getDisplayMetrics().density;
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

    /**
     * dp to px
     *
     * @param context
     * @param dpValue
     * @return
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * px to dp
     *
     * @param context
     * @param pxValue
     * @return
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
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


    public boolean isExternalSDCardMounted() {
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
    }

    /**
     * file copy
     */
    public static boolean copyFile(String srcFilePath, String tagFilepath) {

        File src = new File(srcFilePath);
        File target = new File(tagFilepath);
        if (!src.exists() || src.isDirectory() || !src.canRead()) {
            return false;
        }
        if (target.exists()) {
            target.delete();
        } else if (!target.getParentFile().exists()) {
            target.getParentFile().mkdirs();
        }
        try {
            FileInputStream fosfrom = new FileInputStream(src);
            FileOutputStream fosto = new FileOutputStream(target);
            byte bt[] = new byte[1024];
            int c;
            while ((c = fosfrom.read(bt)) > 0) {
                fosto.write(bt, 0, c); //将内容写到新文件当中
            }
            fosfrom.close();
            fosto.close();
            return true;
        } catch (Exception e) {
            LogUtil.e("File io FileUtils", e.getMessage());
            return false;
        }
    }

    /**
     * convert bitmap to byte array
     *
     * @param bmp
     * @param needRecycle
     * @return
     */
    public static byte[] bmpToByteArray(final Bitmap bmp, final boolean needRecycle) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, output);
        if (needRecycle) {
            bmp.recycle();
        }

        byte[] result = output.toByteArray();
        try {
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }


    /**
     * get file name from url
     *
     * @param url
     * @return
     */
    public static String getFileNameByUrl(String url) {
        if (Utils.isNull(url)) {
            return null;
        }
        int index = url.lastIndexOf('?');
        int index2 = url.lastIndexOf("/");
        if (index > 0 && index2 >= index) {
            return UUID.randomUUID().toString();
        }
        return url.substring(index2 + 1, index < 0 ? url.length() : index);
    }

    /**
     * get file extend name
     *
     * @param fileName
     * @return
     */
    public static String getFileExtendName(String fileName) {
        if (Utils.isNull(fileName)) {
            return null;
        }
        int index = fileName.lastIndexOf('.');
        if (index < 0) {
            return "unknown";
        } else {
            return fileName.substring(index + 1);
        }
    }

    public static boolean isUriALocalFilePath(String uri) {
        if (uri.startsWith("/")) {
            return true;
        }
        return false;
    }


    public static boolean isFileExists(String filePath) {
        if (Utils.isNull(filePath)) {
            return false;
        }

        return new File(filePath).exists();
    }

    public static boolean deleteFile(String filePath) {
        if (Utils.isNull(filePath)) {
            return false;
        }

        return new File(filePath).delete();
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

    public static boolean isNetWorkConnected(Context context) {
        ConnectivityManager connMgr = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
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


}
