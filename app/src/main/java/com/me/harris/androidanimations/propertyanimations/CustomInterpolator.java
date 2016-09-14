package com.me.harris.androidanimations.propertyanimations;

import android.animation.TimeInterpolator;

/**
 * Created by Harris on 2016/9/14.
 */

public class CustomInterpolator implements TimeInterpolator {
    @Override
    public float getInterpolation(float input) {
        return input * input;
    }
}
