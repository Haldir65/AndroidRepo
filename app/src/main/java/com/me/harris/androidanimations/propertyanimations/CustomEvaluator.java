package com.me.harris.androidanimations.propertyanimations;

import android.animation.TypeEvaluator;

/**
 * Created by Harris on 2016/9/14.
 */

public class CustomEvaluator implements TypeEvaluator<Number> {
    @Override
    public Number evaluate(float fraction, Number startValue, Number endValue) {
        float propertyResult = fraction+100;
        /*float startFloat = startValue.floatValue();
        return (startFloat + fraction * (endValue.floatValue() - startFloat));*/
        return propertyResult;
    }
}
