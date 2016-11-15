package com.me.harris.androidanimations._01_drawable.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.me.harris.androidanimations.BaseAppCompatActivity;
import com.me.harris.androidanimations.R;
import com.me.harris.androidanimations._01_drawable.fragment.DrawableMainFragment;
import com.me.harris.androidanimations.databinding.ActivityCanvasBinding;

/**
 * Created by Fermi on 2016/10/8.
 */

public class DrawableMainActivity extends BaseAppCompatActivity {
    ActivityCanvasBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_canvas);
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setTitle("Drawable");
        getSupportFragmentManager().
                beginTransaction().
                add(R.id.frameLayout, DrawableMainFragment.newInstance()).
                commit();
        handleStatusBar();
    }

    public void setToolBarTitle(String simpleName) {
        getSupportActionBar().setTitle(simpleName);

    }
}
