package com.me.harris.androidanimations._05_animation;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.me.harris.androidanimations.BaseAppCompatActivity;
import com.me.harris.androidanimations.R;
import com.me.harris.androidanimations.databinding.ActivitySimpleSlideInAnimationBinding;
import com.me.harris.androidanimations.utils.Utils;

/**
 * Created by Harris on 2017/2/26.
 */

public class SimpleSlideInAnimationActivity extends BaseAppCompatActivity implements Runnable {

    ActivitySimpleSlideInAnimationBinding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int screenWidth = Utils.getScreenWidth(this);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_simple_slide_in_animation);
        binding.frameLayout.setVisibility(View.VISIBLE);
        binding.frameLayout.setTranslationX(-screenWidth);
        binding.frameLayout.animate().translationX(0).setDuration(1200).setInterpolator(new AccelerateDecelerateInterpolator()).withEndAction(this);
    }

    @Override
    public void run() {
//        binding.frameLayout.setTranslationX(0);
    }
}
