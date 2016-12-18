package com.me.harris.androidanimations._10_statusBar;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.me.harris.androidanimations.BaseAppCompatActivity;
import com.me.harris.androidanimations.R;

/**
 * Created by Fermi on 2016/12/18.
 */

public class FitSystemWindowsActivity extends BaseAppCompatActivity implements View.OnClickListener {
    private LinearLayout mRootView;
    private RelativeLayout mRelativeLayout;
    private ImageView mImage;
    private boolean flag = false;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fitsyswindows);

        mRootView = (LinearLayout) findViewById(R.id.rootView);
        mRelativeLayout = (RelativeLayout) findViewById(R.id.relativeLayout);
        mImage = (ImageView) findViewById(R.id.image);
        mImage.setOnClickListener(this);
        setFullSreen();

    }

    @Override
    public void setStatusBarMode(boolean isFullScreen) {

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View view) {
        flag = !flag;
        if (flag) {
            setStatusBarColor(R.color.md_amber_A100);
        } else {
            setFullSreen();
        }
    }





}
