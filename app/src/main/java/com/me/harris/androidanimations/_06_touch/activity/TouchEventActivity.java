package com.me.harris.androidanimations._06_touch.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.me.harris.androidanimations.R;
import com.me.harris.androidanimations.databinding.ActivityTouchBinding;
import com.me.harris.androidanimations._06_touch.fragment.TouchEventMainFragment;

/**
 * Created by Harris on 2016/10/6.
 */

public class TouchEventActivity extends AppCompatActivity {
    ActivityTouchBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_touch);
        setSupportActionBar(binding.toolbar);
        getSupportFragmentManager().
                beginTransaction().
                add(R.id.frameLayout, TouchEventMainFragment.newInstance()).
                commit();
    }

    public void setToolBarTitle(String name) {
        getSupportActionBar().setTitle(name);
    }
}
