package com.me.harris.scrolllayoutsample;

import android.os.Bundle;
import androidx.annotation.Nullable;
import android.support.v4.app.Fragment;
import androidx.core.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, ScrollableLayout.OnScrollListener, SwipeRefreshLayout.OnRefreshListener {

    LeftFragment mLeftFragment;
    RightFragment mRightFragment;


    MyPager mPager;
    FrameLayout mHeader;
    ScrollableLayout mScrollableLayout;
    SwipeRefreshLayout mSwipeLayout;

    SamplePagerAdapter mAdapter;
    private List<Fragment> fragments;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        mHeader = findViewById(R.id.frameHeader);
        mPager = findViewById(R.id.viewPager);
        mScrollableLayout = findViewById(R.id.scrollableLayout);
        mSwipeLayout = findViewById(R.id.swipeRefresh);
        mTab1 = findViewById(R.id.tab1);
        mTab2 = findViewById(R.id.tab2);
        mLeftFragment = LeftFragment.newInstance();
        mRightFragment =RightFragment.newInstance();
        fragments = new ArrayList<>(2);
        fragments.add(mLeftFragment);
        fragments.add(mRightFragment);
        mAdapter = new SamplePagerAdapter(getSupportFragmentManager(), fragments);
        mPager.setAdapter(mAdapter);
        mPager.addOnPageChangeListener(this);

        mSwipeLayout.setOnRefreshListener(this);
        mSwipeLayout.setColorSchemeColors(ContextCompat.getColor(this,android.R.color.holo_blue_bright),
                ContextCompat.getColor(this,android.R.color.holo_green_light),
                ContextCompat.getColor(this,android.R.color.holo_orange_light),
                ContextCompat.getColor(this,android.R.color.holo_red_light));
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    TextView mTab1,mTab2;


    @Override
    public void onPageSelected(int position) {
        if (position == 0) {
            mScrollableLayout.getHelper().setCurrentScrollableContainer(mLeftFragment);
            mTab1.setTextColor(ContextCompat.getColor(this,R.color.md_red_A400));
            mTab2.setTextColor(ContextCompat.getColor(this,R.color.md_black_1000));
        } else {
            mScrollableLayout.getHelper().setCurrentScrollableContainer(mRightFragment);
            mTab1.setTextColor(ContextCompat.getColor(this,R.color.md_black_1000));
            mTab2.setTextColor(ContextCompat.getColor(this,R.color.md_red_A400));
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

    @Override
    public void onRefresh() {
        mSwipeLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mSwipeLayout.isRefreshing()) {
                    mSwipeLayout.setRefreshing(false);
                }
            }
        }, 1000);
    }

    @Override
    public void onScroll(int currentY, int maxY) {
    }
}
