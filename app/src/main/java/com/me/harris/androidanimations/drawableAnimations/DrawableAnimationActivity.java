package com.me.harris.androidanimations.drawableAnimations;

import android.databinding.DataBindingUtil;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.me.harris.androidanimations.R;
import com.me.harris.androidanimations.databinding.ActivityDrawableBinding;

/**
 * Created by Harris on 2016/9/13.
 */

public class DrawableAnimationActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityDrawableBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_drawable);
        setTitle(getClass().getSimpleName());
        binding.imageView2.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        binding.imageView.setImageDrawable(getResources().getDrawable(R.drawable.postive_drawable_anim));
        AnimationDrawable anim = (AnimationDrawable) binding.imageView.getDrawable();
        anim.start();
    }
}
