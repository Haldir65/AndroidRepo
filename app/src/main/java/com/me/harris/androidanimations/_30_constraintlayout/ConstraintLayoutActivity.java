package com.me.harris.androidanimations._30_constraintlayout;

import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.transition.Scene;
import androidx.transition.TransitionManager;
import android.view.View;

import com.me.harris.androidanimations.BaseAppCompatActivity;
import com.me.harris.androidanimations.R;
import com.me.harris.androidanimations.databinding.ActivityConstraintLayoutBinding;

/**
 * Created by Harris on 2017/3/9. Animating ConstraintLayout
 */

public class ConstraintLayoutActivity extends BaseAppCompatActivity implements View.OnClickListener {
    ActivityConstraintLayoutBinding binding;
     Scene scene ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_constraint_layout);
        setSupportActionBar(binding.included.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        binding.button6.setOnClickListener(this);
        scene = new Scene(binding.constraintLayout);
    }


    @Override
    public void onClick(View v) {
        ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) v.getLayoutParams();
        params.bottomToBottom = binding.constraintLayout.getId();
        params.rightToRight = binding.constraintLayout.getId();
        v.setLayoutParams(params);
        // animate changes
        TransitionManager.go(scene);
    }
}
