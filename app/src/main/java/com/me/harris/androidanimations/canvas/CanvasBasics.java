package com.me.harris.androidanimations.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Harris on 2016/9/19.
 */

public class CanvasBasics extends View {

    private Paint paint;

    public CanvasBasics(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (paint == null) {
            paint = new Paint();
        }

        // 以200,200坐标为圆心画圆弧
        //去锯齿
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);//画实心圆的话设置为Style.Fill就可以了
        //设置颜色
        paint.setColor(getResources().getColor(android.R.color.holo_blue_light));
        //绘制普通圆
        canvas.drawCircle(200,200,100, paint);//默认情况下，Paint是有一个default的StrokeWidth的，估计只有1px



       /* //设置空心Style
        paint.setStyle(Paint.Style.STROKE);
        //设置空心边框的宽度
        paint.setStrokeWidth(20);
        paint.setColor(getResources().getColor(android.R.color.holo_blue_light));
        //绘制空心圆
        canvas.drawCircle(200,500,90, paint);//圆心的x坐标,y坐标，半径
*/



    /*    //去锯齿
        paint.setAntiAlias(true);
        //设置颜色
        paint.setColor(getResources().getColor(android.R.color.holo_blue_light));
        //绘制正方形
        canvas.drawRect(100, 100, 300, 300, paint);
        //上面代码等同于
        //RectF rel=new RectF(100,100,300,300);
        //canvas.drawRect(rel, paint);*/




       /* //设置空心Style
        paint.setStyle(Paint.Style.STROKE);
        //设置空心边框的宽度
        paint.setStrokeWidth(20);
        //绘制空心矩形
        canvas.drawRect(100, 400, 600, 800, paint);*/




      /*  paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        //设置颜色
        paint.setColor(getResources().getColor(android.R.color.holo_blue_light));
        //绘制圆角矩形
        canvas.drawRoundRect(100, 100, 300, 300, 30, 30, paint);
        //上面代码等同于
        //RectF rel=new RectF(100,100,300,300);
        //canvas.drawRoundRect(rel,30,30,paint);
*/




      /*  //设置空心Style
        paint.setStyle(Paint.Style.STROKE);
        //设置空心边框的宽度
        paint.setStrokeWidth(20);
        //绘制空心圆角矩形
        canvas.drawRoundRect(100, 400, 600, 800, 30, 30, paint);*/





    /*    //Paint默认的Style是Fill，也就是实心的
        paint.setAntiAlias(true);
        //设置颜色
        paint.setColor(getResources().getColor(android.R.color.holo_orange_dark));
        //绘制椭圆
        canvas.drawOval(100, 100, 500, 300, paint);*/




       /* //设置空心Style
        paint.setStyle(Paint.Style.STROKE);
        //设置空心边框的宽度
        paint.setStrokeWidth(20);
        //绘制空心椭圆
        canvas.drawOval(100, 400, 600, 800, paint);*/




       /* paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        //设置颜色
        paint.setColor(getResources().getColor(android.R.color.holo_orange_dark));
        RectF rel = new RectF(100, 100, 300, 300);
        //实心圆弧
        canvas.drawArc(rel, 0, 270, false, paint);
        //实心圆弧 将圆心包含在内
        RectF rel2 = new RectF(100, 400, 300, 600);
        canvas.drawArc(rel2, 0, 270, true, paint);
        //设置空心Style
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(20);

        RectF rel3 = new RectF(100, 700, 300, 900);
        canvas.drawArc(rel3, 0, 270, false, paint);

        RectF rel4 = new RectF(100, 1000, 300, 1200);
        canvas.drawArc(rel4, 0, 270, true, paint);*/

        /*paint.setAntiAlias(true);
        //设置颜色
        paint.setColor(getResources().getColor(android.R.color.holo_orange_dark));
        paint.setTextSize(100);
        //绘制文本
        canvas.drawText("jEh", 80, 150, paint);*/


    }
}
