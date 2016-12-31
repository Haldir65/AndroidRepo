package com.me.harris.androidanimations._20_annotations;

import android.os.Bundle;
import android.support.annotation.CheckResult;
import android.support.annotation.DrawableRes;
import android.support.annotation.FloatRange;
import android.support.annotation.IntDef;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.Size;
import android.support.annotation.StringRes;
import android.support.annotation.UiThread;

import com.me.harris.androidanimations.BaseAppCompatActivity;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 用于演示Annotation warning的使用
 * Created by Harris on 2016/12/20.
 */

public abstract class AnnotationDemo extends BaseAppCompatActivity {

    public static final int SUNDAY = 0;
    public static final int MONDAY = 1;
    public static final int TUESDAY = 2;
    public static final int WEDNESDAY = 3;
    public static final int THURSDAY = 4;
    public static final int FRIDAY = 5;
    public static final int SATURDAY = 6;

    @IntDef({SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY})
    @Retention(RetentionPolicy.SOURCE)
    public @interface WeekDays {
    }

    @WeekDays
    private int currentDays;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        currentDays = currentDays;//必须选择以上七种值以内的一种
    }

    public void setCurrentDays(@WeekDays int currentDays) {
        this.currentDays = currentDays;
    }

    @WeekDays
    public int getCurrentDays() {
        return currentDays;
    }

   /* private void demo1() {
        Intent intent = new Intent(ACTION_CALL);
        intent.setData(Uri.parse("tel:1234567899"));
        startActivity(intent);//需要电话权限

    }
*/
    public void setAlpha(@IntRange(from = 0, to = 255) int alppha) {
    }

    public void setSize(@Size(min = 1) int size) {

    }

    public void FloatRange(@FloatRange(from = 0.0, to = 1.0) float haha) {

    }

    public void exactSize(@Size(2) int hah[]) {


    }

    @UiThread
    public void setDrawable(@DrawableRes int res) {

    }

    @UiThread
    public void setTitle(@StringRes int res) {

    }

    @CheckResult(suggest = "#enforce")
    public abstract int checkPermission(@NonNull String permission, int pid, int uid);


}
