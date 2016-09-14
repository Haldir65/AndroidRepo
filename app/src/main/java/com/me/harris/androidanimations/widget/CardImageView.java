package com.me.harris.androidanimations.widget;

import android.content.Context;
import android.graphics.Outline;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;

/**
 * Created by Harris on 2016/9/14.
 */

public class CardImageView extends ImageView {
    public CardImageView(Context context) {
        super(context);
    }

    public CardImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CardImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            Outline outline = new Outline();
            outline.setRoundRect(0, 0, w, h, 20);
            setClipToOutline(true);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                animate().setDuration(100).scaleX(1.2f).scaleY(1.2f).translationZ(20);
                return true;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                animate().setDuration(100).scaleX(1).scaleY(1).translationZ(0);
                return true;
        }
        return super.onTouchEvent(event);
    }
}
