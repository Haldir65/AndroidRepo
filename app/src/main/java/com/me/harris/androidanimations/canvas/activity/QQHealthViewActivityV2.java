package com.me.harris.androidanimations.canvas.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.me.harris.androidanimations.R;
import com.me.harris.androidanimations.databinding.ActivityQqhealthV2Binding;

/**
 * Created by Fermi on 2016/9/20.
 */

public class QQHealthViewActivityV2 extends AppCompatActivity {
    ActivityQqhealthV2Binding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_qqhealth_v2);
        setTitle(getClass().getSimpleName());
    }
}
