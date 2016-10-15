package com.me.harris.androidanimations._10_statusBar;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;

import com.me.harris.androidanimations.R;
import com.me.harris.androidanimations.databinding.ActivityStatusbarOneBinding;
import com.me.harris.androidanimations.utils.Utils;

/**
 * Created by Harris on 2016/10/15.
 */

public class StatusBarActivity_Activity_one extends AppCompatActivity {
    ActivityStatusbarOneBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_statusbar_one);
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");
        CollapsingToolbarLayout.LayoutParams params = (CollapsingToolbarLayout.LayoutParams) binding.toolbar.getLayoutParams();
        params.setMargins(0, Utils.getStatusBarHeight(), 0, 0);
        binding.toolbar.setLayoutParams(params);


        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT ) {
            Window window = getWindow();
            ViewGroup mContentView = (ViewGroup) findViewById(Window.ID_ANDROID_CONTENT);

//首先使 ChildView 不预留空间
            View mChildView = mContentView.getChildAt(0);
            if (mChildView != null) {
                ViewCompat.setFitsSystemWindows(mChildView, false);
            }

            int statusBarHeight = Utils.getStatusBarHeight();

            if (mChildView != null&&mChildView instanceof CoordinatorLayout) {
                FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) mChildView.getLayoutParams();
                //清除 ChildView 的 marginTop 属性
                if (lp != null ) {
                    lp.topMargin -= statusBarHeight;
                    mChildView.setLayoutParams(lp);
                }
               AppBarLayout.LayoutParams params = (AppBarLayout.LayoutParams) binding.collapsingToolbarLayout.getLayoutParams();
                    params.topMargin += statusBarHeight;
                    binding.collapsingToolbarLayout.setLayoutParams(params);
            }
        }*/

    }
}
