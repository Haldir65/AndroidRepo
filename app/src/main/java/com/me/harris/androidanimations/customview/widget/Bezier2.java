package com.me.harris.androidanimations.customview.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import androidx.core.content.ContextCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.me.harris.androidanimations.R;

/**
 * Created by Fermi on 2016/9/22.
 */

public class Bezier2 extends View {
    private Paint mPaint,mTextPaint;
    private int centerX, centerY;

    private PointF start, end, control1, control2;
    private boolean mode = true;

    public Bezier2(Context context) {
        this(context, null);

    }

    public Bezier2(Context context, AttributeSet attrs) {
        super(context, attrs);

        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(8);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setTextSize(60);

        start = new PointF(0, 0);
        end = new PointF(0, 0);
        control1 = new PointF(0, 0);
        control2 = new PointF(0, 0);

        mTextPaint = new Paint();
        mTextPaint.setStyle(Paint.Style.STROKE);
        mTextPaint.setColor(ContextCompat.getColor(context, R.color.md_red_500));
        mTextPaint.setStrokeWidth(5f);
        mTextPaint.setTextSize(60f);
    }

    public void setMode(boolean mode) {
        this.mode = mode;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        centerX = w / 2;
        centerY = h / 2;

        // 初始化数据点和控制点的位置
        start.x = centerX - 200;
        start.y = centerY;
        end.x = centerX + 200;
        end.y = centerY;
        control1.x = centerX;
        control1.y = centerY - 100;
        control2.x = centerX;
        control2.y = centerY - 100;

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // 根据触摸位置更新控制点，并提示重绘
        if (mode) {
            control1.x = event.getX();
            control1.y = event.getY();
        } else {
            control2.x = event.getX();
            control2.y = event.getY();
        }
        invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //drawCoordinateSystem(canvas);

        // 绘制数据点和控制点
        mPaint.setColor(Color.GRAY);
        mPaint.setStrokeWidth(20);
        canvas.drawPoint(start.x, start.y, mPaint);
        canvas.drawPoint(end.x, end.y, mPaint);
        canvas.drawPoint(control1.x, control1.y, mPaint);
        canvas.drawPoint(control2.x, control2.y, mPaint);

        // 绘制辅助线
        mPaint.setStrokeWidth(4);
        canvas.drawLine(start.x, start.y, control1.x, control1.y, mPaint);
        canvas.drawLine(control1.x, control1.y,control2.x, control2.y, mPaint);
        canvas.drawLine(control2.x, control2.y,end.x, end.y, mPaint);

        // 绘制贝塞尔曲线
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(8);

        Path path = new Path();

        path.moveTo(start.x, start.y);
        path.cubicTo(control1.x, control1.y, control2.x,control2.y, end.x, end.y);

        canvas.drawPath(path, mPaint);

        float textWidth = mTextPaint.measureText("Bezier2");
        canvas.drawText("Bezier2", centerX-textWidth/2, centerY * 1.5f, mTextPaint);
    }
}
