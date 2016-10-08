package com.me.harris.androidanimations._05_animation;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.me.harris.androidanimations.R;
import com.me.harris.androidanimations.databinding.ActivityDrawableBinding;

/**
 * Created by Harris on 2016/9/13.
 */

public class DrawableAnimationActivity extends AppCompatActivity {

    ActivityDrawableBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_drawable);
        setTitle(getClass().getSimpleName());


    }
}
