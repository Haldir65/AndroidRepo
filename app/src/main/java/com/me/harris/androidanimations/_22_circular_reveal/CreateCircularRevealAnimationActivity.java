package com.me.harris.androidanimations._22_circular_reveal;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.me.harris.androidanimations.BaseAppCompatActivity;
import com.me.harris.androidanimations.R;
import com.me.harris.androidanimations.databinding.ActivityCreateCircularRevealBinding;

/**
 * Created by Harris on 2017/1/14.
 * 使用createCircularAnimation的展示
 */

public class CreateCircularRevealAnimationActivity extends BaseAppCompatActivity {
    ActivityCreateCircularRevealBinding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_create_circular_reveal);
        setSupportActionBar(binding.included.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
