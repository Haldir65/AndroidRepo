package com.me.harris.androidanimations._04_customView.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.me.harris.androidanimations.BaseAppCompatActivity;
import com.me.harris.androidanimations.R;
import com.me.harris.androidanimations._04_customView.fragment.canvasMainFragment;
import com.me.harris.androidanimations.databinding.ActivityCanvasBinding;

/**
 * Created by Harris on 2016/9/19.
 */

public class CustomViewActivity extends BaseAppCompatActivity {
    
    ActivityCanvasBinding binding;
    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_canvas);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.frameLayout, canvasMainFragment.newInstance()).commit();
        setSupportActionBar(binding.toolbar);
        handleStatusBar();
    }
    
    public void setToolBarTitle(String title) {
        binding.toolbar.setTitle(title);
    }
    
}
