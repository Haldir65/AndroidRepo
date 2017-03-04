package com.me.harris.androidanimations._29_velocity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.ViewConfiguration;

import com.me.harris.androidanimations.BaseAppCompatActivity;
import com.me.harris.androidanimations.R;
import com.me.harris.androidanimations.databinding.ActivityVelocityTrackerBinding;

/**
 * Created by Harris on 2017/3/4.
 */

public class VelocityTrackerActivity extends BaseAppCompatActivity {

    int mMaxVelocity;

    ActivityVelocityTrackerBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_velocity_tracker);
        setSupportActionBar(binding.included.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mMaxVelocity = ViewConfiguration.get(this).getScaledMaximumFlingVelocity();//
        binding.velocity.textView = binding.text;
        binding.velocity.maxVelocity = mMaxVelocity;
    }
}
