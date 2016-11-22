package com.me.harris.androidanimations;

import android.os.Build;
import android.support.annotation.ColorRes;
import android.support.annotation.LayoutRes;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.me.harris.androidanimations.utils.Utils;

/** 把一些常规的方法丢到这里
 * Created by harris on 2016/11/15.
 */

public class BaseAppCompatActivity extends AppCompatActivity {

    private View mStatusBarView;


    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        setStatusBarMode(false);
    }

    /** 在setContentView之后会被调用，子类可以通过复写的方式决定具体实现
     * @param isFullScreen
     */
    public void setStatusBarMode(boolean isFullScreen) {
        if (isFullScreen) {
            ((ViewGroup) getWindow().getDecorView().findViewById(android.R.id.content))
                    .getChildAt(0).setFitsSystemWindows(false);
            handleFullScreen();
        }
        else {
            ((ViewGroup) getWindow().getDecorView().findViewById(android.R.id.content))
                    .getChildAt(0).setFitsSystemWindows(true);
            handleTintMode();
        }
    }

    protected void handleFullScreen() {
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        moveToolbarDownwards();

    }


    protected void handleTintMode() {
        //使用着色模式
        setStatusBarColor(R.color.colorPrimaryDark);
//        moveToolbarDownwards();
    }

    /** 对外提供的设置statusBar颜色的方法,子类请不要复写该方法
     * @param statusBarColor
     */
    public void setStatusBarColor(@ColorRes int statusBarColor) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().setStatusBarColor(ContextCompat.getColor(this, statusBarColor));
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            ViewGroup decorView = (ViewGroup) getWindow().getDecorView();
            if (mStatusBarView == null) {
                mStatusBarView = new View(this);
                mStatusBarView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, Utils.getStatusBarHeight()));
                mStatusBarView.setBackgroundColor(ContextCompat.getColor(this, statusBarColor));
                decorView.addView(mStatusBarView);
            } else {
                mStatusBarView.setBackgroundColor(ContextCompat.getColor(this, statusBarColor));
            }
        }
    }

    protected Toolbar mToolbar;

    protected void moveToolbarDownwards() {
        if (mToolbar != null) {
            ViewGroup.LayoutParams layoutParams = mToolbar.getLayoutParams();
            if (layoutParams != null) {
                if (layoutParams instanceof LinearLayout.LayoutParams) {
                    ((LinearLayout.LayoutParams) layoutParams).setMargins(0, Utils.getStatusBarHeight(), 0, 0);
                } else if (layoutParams instanceof RelativeLayout.LayoutParams) {
                    ((RelativeLayout.LayoutParams) layoutParams).setMargins(0, Utils.getStatusBarHeight(), 0, 0);
                } else if (layoutParams instanceof CoordinatorLayout.LayoutParams) {
                    ((CoordinatorLayout.LayoutParams) layoutParams).setMargins(0, Utils.getStatusBarHeight(), 0, 0);
                }
                mToolbar.setLayoutParams(layoutParams);
            }
        }
    }

}
