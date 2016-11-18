package com.me.harris.androidanimations._13_night_mode;

import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;

import com.me.harris.androidanimations.BaseAppCompatActivity;
import com.me.harris.androidanimations.R;
import com.me.harris.androidanimations.databinding.ActivityDayNightBinding;
import com.me.harris.androidanimations.interfaces.ActionCallBack;

/**
 * Created by harris on 2016/11/18.
 */

public class DayNightActivity extends BaseAppCompatActivity implements ActionCallBack {
    ActivityDayNightBinding binding;
    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_day_night);
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        binding.setCallback(this);
    }
    
    @Override
    public void onClickView(View view) {
        switch (view.getId()) {
            case R.id.button1:
                setNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                break;
            case R.id.button2:
                setNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                break;
            case R.id.button3:
                setNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
                break;
            case R.id.button4:
                setNightMode(AppCompatDelegate.MODE_NIGHT_AUTO);
                break;
            
            default:
                break;
        }
    }
    
    private void setNightMode(@AppCompatDelegate.NightMode int nightMode) {
        if (AppCompatDelegate.getDefaultNightMode() != nightMode) {
            AppCompatDelegate.setDefaultNightMode(nightMode);
            if (Build.VERSION.SDK_INT >= 11) {
                recreate();
            }
        }
        
    }
}
