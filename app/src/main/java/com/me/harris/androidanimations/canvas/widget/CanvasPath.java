package com.me.harris.androidanimations.canvas.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Harris on 2016/9/19.
 */

public class CanvasPath extends View {
    private Paint paint;

    public CanvasPath(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        setViewPaint();
       /* Path path=new Path();
        //设置path的起点位置
        path.moveTo(200,200);
        //path路径连接至某点
        path.lineTo(100,400);
        path.lineTo(300,400);
        //path路径的最后一个点与起点连接,如果不写这句三角形就会变成LEXUS的车标了：）
        path.close();
        //绘制三角形
        canvas.drawPath(path,paint);

        //如果这里不设置moveTo那么path将沿着上面那个三角形路径最后一点进行继续绘制
        path.moveTo(100,800);
        path.lineTo(200,500);
        path.lineTo(300,800);
        path.lineTo(400,500);
        path.lineTo(500,800);
        //绘制M形
        canvas.drawPath(path,paint);*/

       /* Path path=new Path();
        //添加各种角度弧线
        path.arcTo(100,100,300,300,0,90,true);

        path.arcTo(100,400,300,600,0,180,true);

        path.arcTo(100,700,300,900,270,180,false);

        //最后一个参数填false，看效果
        path.arcTo(100,1000,300,1200,0,180,false);
        //绘制
        canvas.drawPath(path,paint);*/


       /* Path path=new Path();
        //添加弧形到path
        path.addArc(100,100,300,300,0,270);
        //添加圆形到path
        path.addCircle(200,500,100,Path.Direction.CCW);
        //添加矩形到path
        path.addRect(100,700,300,800, Path.Direction.CW);
        //添加椭圆到path
        path.addOval(100,900,300,1000, Path.Direction.CCW);
        //绘制path
        canvas.drawPath(path,paint);*/

      /*  //设置填充风格，方便观察效果
        paint.setStyle(Paint.Style.FILL);
        //构建path
        Path path=new Path();
        path.addCircle(150, 150, 100, Path.Direction.CW);
        Path path2 = new Path();
        path2.addCircle(300, 150, 100, Path.Direction.CW);
        path.op(path2,Path.Op.UNION);//并集
        //Path.Op.UNION
        canvas.drawPath(path,paint);

        //清除路径
        path.reset();
        path2.reset();
        path.addCircle(150, 400, 100, Path.Direction.CW);
        path2.addCircle(300, 400, 100, Path.Direction.CW);
        path.op(path2,Path.Op.REVERSE_DIFFERENCE);
        //Path.Op.REVERSE_DIFFERENCE
        canvas.drawPath(path,paint);

        //清除路径
        path.reset();
        path2.reset();
        path.addCircle(150, 650, 100, Path.Direction.CW);
        path2.addCircle(300, 650, 100, Path.Direction.CW);
        path.op(path2,Path.Op.INTERSECT);//交集
        //Path.Op.INTERSECT
        canvas.drawPath(path,paint);

        //清除路径
        path.reset();
        path2.reset();
        path.addCircle(150, 900, 100, Path.Direction.CW);
        path2.addCircle(300, 900, 100, Path.Direction.CW);
        path.op(path2,Path.Op.DIFFERENCE);
        //Path.Op.DIFFERENCE
        canvas.drawPath(path,paint);

        //清除路径
        path.reset();
        path2.reset();
        path.addCircle(150, 1150, 100, Path.Direction.CW);
        path2.addCircle(300, 1150, 100, Path.Direction.CW);
        path.op(path2,Path.Op.XOR);
        //Path.Op.XOR
        canvas.drawPath(path,paint);*/

        Path path=new Path();
        path.addCircle(150, 150, 100, Path.Direction.CW);
        Path path2 = new Path();
        path2.addCircle(300, 150, 100, Path.Direction.CW);
        canvas.drawPath(path,paint);



    }

    private void setViewPaint(){
        //绘制风格
        paint=new Paint();
        //去锯齿
        paint.setAntiAlias(true);
        //设置绘制颜色
        paint.setColor(getResources().getColor(android.R.color.holo_blue_light));
        //为了方便看Path的路径效果
        //设置绘制风格为空心
        paint.setStyle(Paint.Style.STROKE);
        //设置空心边框的宽度
        paint.setStrokeWidth(10);
    }


}
