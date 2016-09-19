package com.me.harris.androidanimations.canvas.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Picture;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Harris on 2016/9/19.
 */

public class CanvasPicture extends View {

    // 1.创建Picture
    private Picture mPicture = new Picture();


    // 2.录制内容方法
    private void recording() {
        // 开始录制 (接收返回值Canvas)
        Canvas canvas = mPicture.beginRecording(500, 500);
        // 创建一个画笔
        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.FILL);

        // 在Canvas中具体操作
        // 位移
        canvas.translate(250,250);
        // 绘制一个圆
        canvas.drawCircle(0,0,100,paint);

        mPicture.endRecording();
    }

    public CanvasPicture(Context context, AttributeSet attrs) {
        super(context, attrs);
        recording();
    }

    public CanvasPicture(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPicture(mPicture,new RectF(0,0,mPicture.getWidth(),200));

    }
}
