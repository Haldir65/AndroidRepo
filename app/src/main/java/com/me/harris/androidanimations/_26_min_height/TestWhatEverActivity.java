package com.me.harris.androidanimations._26_min_height;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import android.text.Html;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.me.harris.androidanimations.BaseAppCompatActivity;
import com.me.harris.androidanimations.R;

/**
 * Created by Fermi on 2017/2/22.
 */

public class TestWhatEverActivity extends BaseAppCompatActivity {

    private Toolbar mToolbar;
    private LinearLayout mLlBottom;
    private TextView mTvCenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_whatever);


        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mLlBottom = (LinearLayout) findViewById(R.id.ll_bottom);
        mTvCenter = (TextView) findViewById(R.id.tv_center);


        String source = "这只是一个测试<font color='red'>红色字</font>的格式";

        mTvCenter.setText(Html.fromHtml(source));






    }
}
