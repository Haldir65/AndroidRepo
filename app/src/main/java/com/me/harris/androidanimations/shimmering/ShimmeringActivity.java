package com.me.harris.androidanimations.shimmering;

import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.View;

import com.me.harris.androidanimations.BaseAppCompatActivity;
import com.me.harris.androidanimations.R;
import com.me.harris.androidanimations.databinding.ActivityShimmeringBinding;
import com.me.harris.androidanimations.interfaces.ActionCallBack;

/**
 * Created by Harris on 2017/1/14.
 */

public class ShimmeringActivity extends BaseAppCompatActivity implements ActionCallBack {

    ActivityShimmeringBinding binding;

    Shimmer shimmer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_shimmering);
        binding.setCallback(this);
        setSupportActionBar(binding.included.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void toggleAnimation(View target) {
        if (shimmer != null && shimmer.isAnimating()) {
            shimmer.cancel();
        } else {
            shimmer = new Shimmer();
            shimmer.start(binding.shimmerTv);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (shimmer != null) {
            shimmer.cancel();
        }
    }

    @Override
    public void onClickView(View view) {
        toggleAnimation(view);
    }
}
