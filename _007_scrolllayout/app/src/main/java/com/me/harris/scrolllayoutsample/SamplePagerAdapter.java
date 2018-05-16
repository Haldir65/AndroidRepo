package com.me.harris.scrolllayoutsample;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class SamplePagerAdapter extends FragmentPagerAdapter {

    List<Fragment> mFragments;

    public SamplePagerAdapter(FragmentManager fm,List<Fragment> s) {
        super(fm);
        mFragments = s;
    }

    @Override
    public int getCount() {
        return 2;
    }



    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }




}
