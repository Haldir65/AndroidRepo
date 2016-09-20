package com.me.harris.androidanimations.canvas.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.me.harris.androidanimations.R;
import com.me.harris.androidanimations.databinding.ActivityQqhealthV1Binding;

/**
 * Created by Harris on 2016/9/19.
 */

public class QQHealthViewActivityV1 extends AppCompatActivity {

    ActivityQqhealthV1Binding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_qqhealth_v1);
        setTitle(getClass().getSimpleName());
    }
}

