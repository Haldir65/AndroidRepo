package com.me.harris.androidanimations._34_bottomsheet;

import android.support.annotation.DrawableRes;

/**
 * Created by Harris on 2017/5/6.
 */

public class BottomSheetItem {

    @DrawableRes
    private int mDrawableRes;

    private String mTitle;

    public BottomSheetItem(@DrawableRes int drawable, String title) {
        mDrawableRes = drawable;
        mTitle = title;
    }

    @DrawableRes
    public int getDrawableResource() {
        return mDrawableRes;
    }

    public String getTitle() {
        return mTitle;
    }
}
