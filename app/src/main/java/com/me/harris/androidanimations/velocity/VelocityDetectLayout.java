package com.me.harris.androidanimations.velocity;

import android.content.Context;
import androidx.annotation.AttrRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * Created by Harris on 2017/3/4.
 */

public class VelocityDetectLayout extends FrameLayout {
    public static final String TAG = VelocityDetectLayout.class.getSimpleName();
    private VelocityTracker mVelocityTracker;//生命变量
    TextView textView;
    int maxVelocity;

    public VelocityDetectLayout(@NonNull Context context) {
        this(context, null);
    }

    public VelocityDetectLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public VelocityDetectLayout(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }



    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
//在onTouchEvent(MotionEvent ev)中
        if (mVelocityTracker == null) {
            mVelocityTracker = VelocityTracker.obtain();//获得VelocityTracker类实例
        }
        mVelocityTracker.addMovement(ev);//将事件加入到VelocityTracker类实例中
//判断当ev事件是MotionEvent.ACTION_UP时：计算速率
        final VelocityTracker verTracker = mVelocityTracker;
        final int action = ev.getActionMasked();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                return true;
            case MotionEvent.ACTION_MOVE:
                verTracker.computeCurrentVelocity(1000); //1000表示1秒内滑动过的像素值，1表示一毫秒内滑动的像素值
                final float velocityX = verTracker.getXVelocity();
                final float velocityY = verTracker.getYVelocity();
                if (textView != null) {
                        textView.setText("速度: X" + velocityX + " Y= " + velocityY);
                }
                Log.d(TAG, "dispatchTouchEvent: " + maxVelocity);
                break;
            case MotionEvent.ACTION_UP:
                if (null != mVelocityTracker) {
                    mVelocityTracker.clear();
                    mVelocityTracker.recycle();
                    mVelocityTracker = null;
                }
                break;
            case MotionEvent.ACTION_CANCEL:
                if (null != mVelocityTracker) {
                    mVelocityTracker.clear();
                    mVelocityTracker.recycle();
                    mVelocityTracker = null;
                }
                break;
            default:
                break;
        }
        return super.dispatchTouchEvent(ev);
    }
}
