package com.me.harris.scrolllayoutsample;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class MyPager extends ViewPager {
    public MyPager(@NonNull Context context) {
        super(context);
    }

    public MyPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptHoverEvent(MotionEvent event) {
        return super.onInterceptHoverEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    ScrollableLayout parent;

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (parent == null) {
             parent = (ScrollableLayout) getParent();
        }
//        if (ev.getAction() == MotionEvent.ACTION_CANCEL&&parent.mIsHorizontalScrolling) {
//            if (getCurrentItem() == 0) {
////                scrollToItem(1, true, 0, false);
//                setCurrentItem(1,true);
//            } else {
////                scrollToItem(0, true, 0, false);
//                setCurrentItem(0,true);
//            }
//            return true;
//        }
        return super.onTouchEvent(ev);
    }

}
