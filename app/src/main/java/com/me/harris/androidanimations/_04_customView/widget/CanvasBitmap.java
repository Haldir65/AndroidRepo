package com.me.harris.androidanimations._04_customView.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.me.harris.androidanimations.R;

/**
 * Created by Harris on 2016/9/19.
 */

public class CanvasBitmap extends View {
    private Paint mPaint;

    public CanvasBitmap(Context context) {
        super(context);
    }

    public CanvasBitmap(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.image_04);
        canvas.drawBitmap(bitmap, 200, 200, mPaint);
    }
}
