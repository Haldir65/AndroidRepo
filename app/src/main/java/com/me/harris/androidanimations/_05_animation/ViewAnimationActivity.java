package com.me.harris.androidanimations._05_animation;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.me.harris.androidanimations.BaseAppCompatActivity;
import com.me.harris.androidanimations.R;
import com.me.harris.androidanimations.databinding.ActivityPropertyBinding;
import com.me.harris.androidanimations.utils.LogUtil;
import com.me.harris.androidanimations.utils.Utils;

/**
 * Created by Harris on 2016/9/13.
 */

public class ViewAnimationActivity extends BaseAppCompatActivity {

    ActivityPropertyBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_property);
        setTitle(getClass().getSimpleName());

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if (hasFocus) {
            LogUtil.w("statusBarHeight = " + Utils.getStatusBarHeight()+"\n");
            int[] rect1 = new int[2];
            binding.rootScrollView.getLocationInWindow(rect1);
            LogUtil.w("getLocationInWindow  x = "+rect1[0]+" y = "+rect1[1]+" \n");
            int[] rect2 = new int[2];
            binding.rootScrollView.getLocationOnScreen(rect2);
            LogUtil.w("getLocationOnScreen x = " + rect2[0] + " y = " + rect2[1] + "\n");
        }

    }
}
