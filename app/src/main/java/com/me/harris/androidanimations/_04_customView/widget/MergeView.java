package com.me.harris.androidanimations._04_customView.widget;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.me.harris.androidanimations.R;

/**
 * Created by Harris on 2017/4/10.
 */

public class MergeView extends FrameLayout {

    RelativeLayout rl;
    TextView tv;


    public MergeView(@NonNull Context context) {
        this(context,null);
    }

    public MergeView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MergeView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.view_merge, this);
        rl = (RelativeLayout) findViewById(R.id.rl_merge);
        tv = (TextView) findViewById(R.id.tv_inside);

    }
}
