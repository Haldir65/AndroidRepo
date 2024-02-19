package com.me.harris.androidanimations._15_swipeBackLayout.demo;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Vibrator;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.RadioGroup;

import com.me.harris.androidanimations.R;
import com.me.harris.androidanimations._15_swipeBackLayout.SwipeBackLayout;
import com.me.harris.androidanimations._15_swipeBackLayout.app.SwipeBackActivity;

/**
 * Created by harris on 2016/11/22.
 */

public class SwipeBackDemoActivity extends SwipeBackActivity implements View.OnClickListener {
    private static final int VIBRATE_DURATION = 20;

    private int[] mBgColors;

    private static int mBgIndex = 0;

    private String mKeyTrackingMode;

    private RadioGroup mTrackingModeGroup;

    private SwipeBackLayout mSwipeBackLayout;

    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        findViews();
        changeActionBarColor();
        mKeyTrackingMode = getString(R.string.key_tracking_mode);
        mSwipeBackLayout = getSwipeBackLayout();

        mTrackingModeGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int edgeFlag;
                if (checkedId == R.id.mode_left) {
                    edgeFlag = SwipeBackLayout.EDGE_LEFT;
                } else if (checkedId == R.id.mode_right) {
                    edgeFlag = SwipeBackLayout.EDGE_RIGHT;
                } else if (checkedId == R.id.mode_bottom) {
                    edgeFlag = SwipeBackLayout.EDGE_BOTTOM;
                } else {
                    edgeFlag = SwipeBackLayout.EDGE_ALL;
                }
                mSwipeBackLayout.setEdgeTrackingEnabled(edgeFlag);
                saveTrackingMode(edgeFlag);
            }
        });
        mSwipeBackLayout.addSwipeListener(new SwipeBackLayout.SwipeListener() {
            @Override
            public void onScrollStateChange(int state, float scrollPercent) {

            }

            @Override
            public void onEdgeTouch(int edgeFlag) {
                vibrate(VIBRATE_DURATION);
            }

            @Override
            public void onScrollOverThreshold() {
                vibrate(VIBRATE_DURATION);
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        restoreTrackingMode();
    }

    private void saveTrackingMode(int flag) {
        PreferenceUtils.setPrefInt(getApplicationContext(), mKeyTrackingMode, flag);
    }

    private void restoreTrackingMode() {
        int flag = PreferenceUtils.getPrefInt(getApplicationContext(), mKeyTrackingMode,
                SwipeBackLayout.EDGE_LEFT);
        mSwipeBackLayout.setEdgeTrackingEnabled(flag);
        switch (flag) {
            case SwipeBackLayout.EDGE_LEFT:
                mTrackingModeGroup.check(R.id.mode_left);
                break;
            case SwipeBackLayout.EDGE_RIGHT:
                mTrackingModeGroup.check(R.id.mode_right);
                break;
            case SwipeBackLayout.EDGE_BOTTOM:
                mTrackingModeGroup.check(R.id.mode_bottom);
                break;
            case SwipeBackLayout.EDGE_ALL:
                mTrackingModeGroup.check(R.id.mode_all);
                break;
        }
    }

    private void changeActionBarColor() {
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getColors()[mBgIndex]));
        mBgIndex++;
        if (mBgIndex >= getColors().length) {
            mBgIndex = 0;
        }
    }

    private void findViews() {
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        findViewById(R.id.btn_start).setOnClickListener(this);
        findViewById(R.id.btn_finish).setOnClickListener(this);
        mTrackingModeGroup = (RadioGroup) findViewById(R.id.tracking_mode);
    }

    private int[] getColors() {
        if (mBgColors == null) {
            Resources resource = getResources();
            mBgColors = new int[] {
                    resource.getColor(R.color.md_blue_400),
                    resource.getColor(R.color.androidColorB),
                    resource.getColor(R.color.androidColorC),
                    resource.getColor(R.color.androidColorD),
                    resource.getColor(R.color.androidColorE),
            };
        }
        return mBgColors;
    }

    private void vibrate(long duration) {
        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        long[] pattern = {
                0, duration
        };
        vibrator.vibrate(pattern, -1);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_start) {
            startActivity(new Intent(SwipeBackDemoActivity.this, SwipeBackDemoActivity.class));
        } else if (id == R.id.btn_finish) {
            scrollToFinishActivity();
        }
    }
}
