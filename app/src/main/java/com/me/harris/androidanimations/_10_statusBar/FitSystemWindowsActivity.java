package com.me.harris.androidanimations._10_statusBar;

import android.os.Build;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
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
//        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);


    }

    @Override
    public void onSetStatusBarMode() {
        setFullScreen();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View view) {
        flag = !flag;
       setDarkStatusIcon(flag);
    /*    if (flag) {
            moveContentViewDownWards();
//            setStatusBarColor(R.color.md_deep_purple_700);
        } else {
            moveContentViewUpwards();
           setFullScreen();
        }*/


    }



}
