package com.me.harris.androidanimations._04_customView.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.me.harris.androidanimations.utils.Utils;

/**
 * Created by Harris on 2016/9/19.
 */

public class CanvasManipulation extends View {

    private Paint mPaint;
    private int mWidth;
    private int mHeight;
    public CanvasManipulation(Context context) {
        this(context,null);
    }

    public CanvasManipulation(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mHeight = Utils.getScreenHeight(getContext());
        mWidth = Utils.getScreenWidth(getContext());

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
       /* mPaint.setColor(Color.BLACK);
        canvas.translate(200,200);
        canvas.drawCircle(0,0,100,mPaint);

        // 在坐标原点绘制一个蓝色圆形
        mPaint.setColor(Color.BLUE);
        canvas.translate(200,200);
        canvas.drawCircle(0,0,100,mPaint);*/




        /*// 将坐标系原点移动到画布正中心
        canvas.translate(mWidth / 2, mHeight / 2);

        RectF rect = new RectF(0,-400,400,0);   // 矩形区域

        mPaint.setColor(Color.BLACK);           // 绘制黑色矩形
        canvas.drawRect(rect,mPaint);

        canvas.scale(0.5f,0.5f,200,200);                // 画布缩放

        mPaint.setColor(Color.BLUE);            // 绘制蓝色矩形
        canvas.drawRect(rect,mPaint);
*/

        /*canvas.translate(mWidth / 2, mHeight / 2);
        mPaint.setStyle(Paint.Style.STROKE);
        RectF rect = new RectF(-400,-400,400,400);   // 矩形区域

        for (int i=0; i<=20; i++)
        {
            canvas.scale(0.9f,0.9f);
            canvas.drawRect(rect,mPaint);
        }*/

       /* canvas.translate(mWidth / 2, mHeight / 2);
        mPaint.setStyle(Paint.Style.STROKE);
        RectF rectF = new RectF(0, -400, 400, 0);
        mPaint.setColor(Color.BLUE);
        canvas.drawRect(rectF, mPaint);

        canvas.rotate(120,200,0);
        canvas.drawRect(rectF, mPaint);*/

        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.BLUE);
        canvas.drawCircle(mWidth / 2, mHeight / 2, 400, mPaint);
        canvas.drawCircle(mWidth / 2, mHeight / 2, 300, mPaint);
        canvas.translate(mWidth / 2, mHeight / 2);
        for (int i = 0; i < 190; i += 10) {
            canvas.drawLine(0, 300, 0, 400, mPaint);
            canvas.rotate(10);
        }

    }
}
