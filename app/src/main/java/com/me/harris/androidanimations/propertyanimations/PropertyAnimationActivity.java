package com.me.harris.androidanimations.propertyanimations;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.me.harris.androidanimations.R;
import com.me.harris.androidanimations.databinding.ActivityPropertyBinding;

/**
 * Created by Harris on 2016/9/13.
 */

public class PropertyAnimationActivity extends AppCompatActivity implements View.OnClickListener {
    ActivityPropertyBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_property);
        binding.imageView.setOnClickListener(this);
    }

    @Override
    public void onClick(final View v) {
       final ObjectAnimator animator =  ObjectAnimator
                .ofFloat(v, "wtf", 1f, 0.1f).
                setDuration(1500);
        animator.start();
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float cValue = (float) animation.getAnimatedValue();
                v.setAlpha(cValue);
                v.setScaleX(cValue);
                v.setScaleY(cValue);
            }
        });
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                binding.linearLayout.removeViewAt(1);

            }
        });
    }

}
