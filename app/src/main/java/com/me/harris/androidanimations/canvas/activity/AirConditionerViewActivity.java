package com.me.harris.androidanimations.canvas.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.me.harris.androidanimations.R;
import com.me.harris.androidanimations.databinding.ActivityAirConditionerBinding;

/**
 * Created by Fermi on 2016/9/20.
 */

public class AirConditionerViewActivity extends AppCompatActivity {
    ActivityAirConditionerBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_air_conditioner);
        setTitle(getClass().getSimpleName());
    }
}
