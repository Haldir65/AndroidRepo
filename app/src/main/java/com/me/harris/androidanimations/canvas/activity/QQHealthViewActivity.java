package com.me.harris.androidanimations.canvas.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.me.harris.androidanimations.R;
import com.me.harris.androidanimations.databinding.ActivityQqhealthBinding;

/**
 * Created by Harris on 2016/9/19.
 */

public class QQHealthViewActivity extends AppCompatActivity {

    ActivityQqhealthBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_qqhealth);

    }
}

