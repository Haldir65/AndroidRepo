package com.me.harris.androidanimations._042_layoutmanager;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.me.harris.androidanimations.BaseAppCompatActivity;
import com.me.harris.androidanimations.R;
import com.me.harris.androidanimations.databinding.ActivityLayoutmangerBinding;

/**
 * Created by Harris on 2017/9/13.
 * copy of https://github.com/yuqirong/CardSwipeLayout
 * and https://github.com/mcxtzhang/ZLayoutManager
 *
 */

public class LayoutManagerActivity extends BaseAppCompatActivity implements View.OnClickListener {

    ActivityLayoutmangerBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_layoutmanger);
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        binding.button1.setOnClickListener(this);
        binding.button2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:

                break;
            case R.id.button2:
                break;
            default:
                break;
        }
    }
}
