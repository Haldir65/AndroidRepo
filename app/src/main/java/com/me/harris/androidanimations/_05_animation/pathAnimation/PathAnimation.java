package com.me.harris.androidanimations._05_animation.pathAnimation;

import android.animation.ObjectAnimator;
import android.graphics.Path;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Harris on 2016/10/12.
 */

public class PathAnimation extends AppCompatActivity {

    private FloatingActionButton fab;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Path path = new Path();
        path.moveTo(10, 10);
        path.cubicTo(20, 20, 20, 100, 100, 100);
        ObjectAnimator.ofFloat(fab, "x", "y", path).start(); // added in api 21
        // http://graphics-geek.blogspot.com/2012/01/curved-motion-in-android.html
    }
}
