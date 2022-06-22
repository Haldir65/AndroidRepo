package com.me.harris.androidanimations._19_rx_java_2;

import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.annotation.Nullable;

import com.me.harris.androidanimations.BaseAppCompatActivity;
import com.me.harris.androidanimations.R;
import com.me.harris.androidanimations.databinding.ActivityPictureScanDisplayBinding;

/**
 * Created by Harris on 2017/3/2.
 * Scanning all the pic on the phone and display on the screen
 * http://blog.csdn.net/carrey1989/article/details/12002033
 * http://blog.csdn.net/xiaanming/article/details/18730223
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
