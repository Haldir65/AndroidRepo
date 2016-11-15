package com.me.harris.androidanimations;

import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

import com.me.harris.androidanimations.utils.Utils;

/** 把一些常规的方法丢到这里
 * Created by harris on 2016/11/15.
 */

public class BaseAppCompatActivity extends AppCompatActivity {



    protected void handleStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            ViewGroup.LayoutParams statusParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, Utils.getStatusBarHeight());
            View statusbarView = new View(this);
            statusbarView.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
            ((ViewGroup) getWindow().getDecorView().findViewById(android.R.id.content)).addView(statusbarView, statusParams);
        }
    }

}
