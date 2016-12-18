package com.me.harris.androidanimations;

import android.graphics.Color;
import android.os.Build;
import android.support.annotation.ColorRes;
import android.support.annotation.LayoutRes;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.me.harris.androidanimations.utils.Utils;

/** 把一些常规的方法丢到这里
 * Created by harris on 2016/11/15.
 */

public class BaseAppCompatActivity extends AppCompatActivity {

    private View mStatusBarView;


    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        onSetStatusBarMode();
    }

    /** 在setContentView之后会被调用，子类可以通过复写的方式决定具体实现
     * @param
     */
    public void onSetStatusBarMode() {
        setStatusBarColor(R.color.colorPrimaryDark);
    }

    protected void handleFullScreen() {
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }


    }


    protected void handleTintMode() {
        //使用着色模式
        setStatusBarColor(R.color.colorPrimaryDark);
//        moveToolbarDownwards();
    }

    public void moveContentViewDownWards() {
        ViewGroup contentview = (ViewGroup) ((ViewGroup) findViewById(android.R.id.content)).getChildAt(0);
        if (contentview != null) {
            ViewCompat.setFitsSystemWindows(contentview, true);
            contentview.setClipToPadding(false);
        }

    }


    public void moveContentViewupwards() {
        ViewGroup contentview = (ViewGroup) ((ViewGroup) findViewById(android.R.id.content)).getChildAt(0);
        if (contentview != null) {
            ViewCompat.setFitsSystemWindows(contentview, false);
            contentview.setClipToPadding(true);
        }

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



    public   void setFullSreen(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
            );
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN| View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }else
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 设置透明状态栏,这样才能让 ContentView 向上
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }



}
