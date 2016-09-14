package com.me.harris.androidanimations.propertyanimations;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.me.harris.androidanimations.R;
import com.me.harris.androidanimations.databinding.ActivityPropertyBinding;
import com.me.harris.androidanimations.utils.ActionCallBack;

/**
 * Created by Harris on 2016/9/13.
 */

public class PropertyAnimationActivity extends AppCompatActivity implements ActionCallBack {
    ActivityPropertyBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_property);
        binding.setCallback(this);
        setTitle("PropertyAnimation");
    }


    /**
     * @param v
     */
    @Override
    public void onClickView(final View v) {
        switch (v.getId()) {
            case R.id.imageView:
                final ObjectAnimator animator = ObjectAnimator
                        .ofFloat(v, "WTF", 1f, 0.5f).setDuration(1500);
                animator.setRepeatCount(2);
                animator.setRepeatMode(ValueAnimator.REVERSE);
                animator.start();
                animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        float cValue = (float) animation.getAnimatedValue();
                        v.setAlpha(cValue);
                        v.setScaleX(1.2f);
                        v.setScaleY(1.2f);
                    }
                });
                animator.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {

                    }
                });
                break;
            case R.id.imageView2:
                ObjectAnimator animX = ObjectAnimator.ofFloat(v, "x", 50f);
                ObjectAnimator animY = ObjectAnimator.ofFloat(v, "y", 100f);
                AnimatorSet animSetXY = new AnimatorSet();
                animSetXY.playTogether(animX, animY);
                animSetXY.start();
                break;
            case R.id.imageView3:
                PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat("x", 50f);
                PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat("y", 100f);
                ObjectAnimator.ofPropertyValuesHolder(v, pvhX, pvhY).start();
                break;
            case R.id.imageView4:
                v.animate().x(v.getX()+50f).y(v.getY()+100f).z(120);
                break;
            case R.id.imageView5:
                AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(this,
                        R.animator.sequential_effects);
                set.setTarget(v);
                set.start();
                break;
            case R.id.imageView6:
                break;
            case R.id.imageView7:
                break;
            case R.id.imageView8:
                break;
            case R.id.imageView9:
                break;
            default:
                break;

        }
    }
}
