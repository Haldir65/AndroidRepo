package com.me.harris.androidanimations._21_wave_animation;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.me.harris.androidanimations.BaseAppCompatActivity;
import com.me.harris.androidanimations.R;
import com.me.harris.androidanimations.databinding.ActivityWaveViewBinding;

/**
 * Created by Harris on 2017/1/13.
 */

public class WaveAnimationActivity extends BaseAppCompatActivity {
    ActivityWaveViewBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_wave_view);

    }
}
