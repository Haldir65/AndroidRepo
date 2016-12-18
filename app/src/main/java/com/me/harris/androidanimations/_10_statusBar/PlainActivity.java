package com.me.harris.androidanimations._10_statusBar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.me.harris.androidanimations.R;

/** for fun ,用来观察setFitSystemWindows的问题
 * Created by Harris on 2016/12/18.
 */

public class PlainActivity extends AppCompatActivity {

    private ScrollView mScrollView;
    private LinearLayout mLinearLayout;
    private ImageView mImage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plain);



        mScrollView = (ScrollView) findViewById(R.id.ScrollView);
        mLinearLayout = (LinearLayout) findViewById(R.id.LinearLayout);
        mImage = (ImageView) findViewById(R.id.image);
        ViewCompat.setFitsSystemWindows(mScrollView,false);


    }
}
