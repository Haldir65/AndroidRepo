package com.me.harris.androidanimations._19_rx_java_2;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.me.harris.androidanimations.BaseAppCompatActivity;
import com.me.harris.androidanimations.R;
import com.me.harris.androidanimations.databinding.ActivityPictureScanDisplayBinding;

/**
 * Created by Harris on 2017/3/2.
 * Scanning all the pic on the phone and display on the screen
 * http://blog.csdn.net/carrey1989/article/details/12002033
 */

public class PictureScanDisplayActivity extends BaseAppCompatActivity {
    ActivityPictureScanDisplayBinding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_picture_scan_display);
        setSupportActionBar(binding.included.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
