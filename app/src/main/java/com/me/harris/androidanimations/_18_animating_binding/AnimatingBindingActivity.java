package com.me.harris.androidanimations._18_animating_binding;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.me.harris.androidanimations.BaseAppCompatActivity;
import com.me.harris.androidanimations.R;
import com.me.harris.androidanimations.databinding.ActivityAnimateBindingBinding;
import com.me.harris.androidanimations.interfaces.ActionCallBack;

/**
 * https://medium.com/google-developers/android-data-binding-animations-55f6b5956a64#.t71lgcjch
 * Created by Fermi on 2016/12/4.
 */

public class AnimatingBindingActivity extends BaseAppCompatActivity implements ActionCallBack {
    private ActivityAnimateBindingBinding binding;
    private GirlsAlpha data;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_animate_binding);
        setSupportActionBar(binding.includedToolbar.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        binding.setData(data);
        binding.setCallback(this);

    }

    @Override
    public void onClickView(View view) {
        //change data , make it reflect view appearance
    }
}
