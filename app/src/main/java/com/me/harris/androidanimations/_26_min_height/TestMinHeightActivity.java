package com.me.harris.androidanimations._26_min_height;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.widget.LinearLayout;

import com.me.harris.androidanimations.BaseAppCompatActivity;
import com.me.harris.androidanimations.R;

/**
 * Created by Fermi on 2017/2/22.
 */

public class TestMinHeightActivity extends BaseAppCompatActivity {

    private Toolbar mToolbar;
    private LinearLayout mLlBottom;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_min_height);




        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mLlBottom = (LinearLayout) findViewById(R.id.ll_bottom);




    }
}
