package com.me.harris.androidanimations._21_wave_animation;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;

import com.me.harris.androidanimations.BaseAppCompatActivity;
import com.me.harris.androidanimations.R;
import com.me.harris.androidanimations.databinding.ActivityWaveViewBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Harris on 2017/1/13.
 */

public class WaveAnimationActivity extends BaseAppCompatActivity {
    ActivityWaveViewBinding binding;
    private AnimatorSet mAnimatorSet;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_wave_view);
        setSupportActionBar(binding.included.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initWaveView();

    }

    @Override
    public void onSetStatusBarMode() {
        super.onSetStatusBarMode();
    }

    private void initWaveView() {
        binding.waveView.setShapeType(WaveView.TYPE_CIRCLE);
        binding.waveView.setBorder(10, ContextCompat.getColor(this, R.color.md_red_500));

        binding.waveView.setWaveColor(
                Color.parseColor("#28f16d7a"),
                Color.parseColor("#3cf16d7a"));
//        mBorderColor = Color.parseColor("#44f16d7a");
//        binding.waveView.setBorder(ContextCompat, mBorderColor);

        List<Animator> animators = new ArrayList<>();

        // horizontal animation.
        // wave waves infinitely.
        ObjectAnimator waveShiftAnim = ObjectAnimator.ofFloat(
                binding.waveView, "waveShiftRatio", 0f, 1f);
        waveShiftAnim.setRepeatCount(ValueAnimator.INFINITE);
        waveShiftAnim.setDuration(1000);
        waveShiftAnim.setInterpolator(new LinearInterpolator());
        animators.add(waveShiftAnim);

        // vertical animation.
        // water level increases from 0 to center of WaveView
        ObjectAnimator waterLevelAnim = ObjectAnimator.ofFloat(
                binding.waveView, "waterLevelRatio", 0f, 0.5f);
        waterLevelAnim.setDuration(10000);
        waterLevelAnim.setInterpolator(new DecelerateInterpolator());
        animators.add(waterLevelAnim);

        // amplitude animation.
        // wave grows big then grows small, repeatedly
        ObjectAnimator amplitudeAnim = ObjectAnimator.ofFloat(
                binding.waveView, "amplitudeRatio", 0.0001f, 0.05f);
        amplitudeAnim.setRepeatCount(ValueAnimator.INFINITE);
        amplitudeAnim.setRepeatMode(ValueAnimator.REVERSE);
        amplitudeAnim.setDuration(5000);
        amplitudeAnim.setInterpolator(new LinearInterpolator());
        animators.add(amplitudeAnim);

        mAnimatorSet = new AnimatorSet();
        mAnimatorSet.playTogether(animators);
        mAnimatorSet.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mAnimatorSet != null) {
            mAnimatorSet.end();
        }
    }
}
