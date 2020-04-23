package com.me.harris.androidanimations.customview.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.OnApplyWindowInsetsListener;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.WindowInsetsCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.me.harris.androidanimations.R;
import com.me.harris.androidanimations.customview.fragment.canvasMainFragment;
import com.me.harris.androidanimations.databinding.ActivityCanvasBinding;

/**
 * Created by Harris on 2016/9/19.
 */

public class CustomViewActivity extends AppCompatActivity implements OnApplyWindowInsetsListener {
    
    ActivityCanvasBinding binding;
    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_canvas);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.frameLayout, canvasMainFragment.newInstance()).commit();
        setSupportActionBar(binding.toolbar);
        ViewCompat.setOnApplyWindowInsetsListener(binding.getRoot(),this);
    }

    public void setToolBarTitle(String title) {
        binding.toolbar.setTitle(title);
    }

    @Override
    public WindowInsetsCompat onApplyWindowInsets(View v, WindowInsetsCompat insets) {
        insets.replaceSystemWindowInsets(0, 0, 0, com.me.harris.androidanimations.utils.Utils.getStatusBarHeight());
        return insets;
    }
}
