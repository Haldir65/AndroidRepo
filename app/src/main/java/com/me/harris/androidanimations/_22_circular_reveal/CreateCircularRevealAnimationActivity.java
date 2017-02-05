package com.me.harris.androidanimations._22_circular_reveal;

import android.animation.Animator;
import android.annotation.TargetApi;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.me.harris.androidanimations.BaseAppCompatActivity;
import com.me.harris.androidanimations.R;
import com.me.harris.androidanimations.databinding.ActivityCreateCircularRevealBinding;
import com.me.harris.androidanimations.interfaces.ActionCallBack;

/**
 * Created by Harris on 2017/1/14.
 * 使用createCircularAnimation的展示
 */

public class CreateCircularRevealAnimationActivity extends BaseAppCompatActivity implements ActionCallBack {
    ActivityCreateCircularRevealBinding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_create_circular_reveal);
        setSupportActionBar(binding.included.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        binding.setCallback(this);
    }

    @Override
    public void onClickView(View view) {
        switch (view.getId()) {
            case R.id.fab:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    createAnimation(binding.cardView, 0).start();
                }
                break;

        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private Animator createAnimation(final View view, double radius) {
        Animator animator;
        if (radius == 0L) {
            radius = Math.hypot(view.getWidth(), view.getHeight());
        }
        if (!isOn) {
            animator = ViewAnimationUtils.createCircularReveal(view, view.getWidth()/2, view.getHeight()/2, 0, (float) radius);
        } else {
            animator = ViewAnimationUtils.createCircularReveal(view,  view.getWidth()/2, view.getHeight()/2, (float) radius, 0);
        }
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                if (!isOn) {
                    view.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (isOn) {
                    view.setVisibility(View.INVISIBLE);
                }
                isOn = !isOn;
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.setDuration(500);
        return animator;
    }

    private boolean isOn;
}
