package com.me.harris.androidanimations.canvas.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewConfiguration;

import com.me.harris.androidanimations.R;
import com.me.harris.androidanimations.utils.Utils;

/**
 * Created by Fermi on 2016/10/5.
 */

public class PolygonView extends View {

    private int mWidth;
    private int mHeight;
    private int mRadius;
    private Paint mTextPaint, mArcPaint, mRadiusPaint, mPolygonPaint;
    private int mTouchSlop, center;
    private int defalutSize = 300;//默认大小
    private Rect mTextRect;

    private Paint rank_Paint;//各等级进度画笔
    private Paint str_paint;//字体画笔
    private Paint center_paint;//中心线画笔
    private Paint one_paint;//最外层多边形画笔
    private Paint two_paint;//第二层多边形画笔
    private Paint three_paint;//第三层多边形画笔
    private Paint four_paint;//第四层多边形画笔
    private float f1, f2, f3, f4, f5, f6, f7;

    private String[] str = {"击杀", "生存", "助攻", "物理", "魔法", "防御", "金钱"};
    private int distance;

    public PolygonView(Context context) {
        this(context, null);
    }

    public PolygonView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PolygonView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        //文字画笔初始化
        defalutSize = Utils.dip2px(context, defalutSize);
        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setColor(Color.BLACK);
        mTextPaint.setTextSize(Utils.dip2px(context, 16));
        mTextRect = new Rect();
        mTextPaint.getTextBounds(str[0], 0, str[0].length(), mTextRect);


        //初始化各等级进度画笔
        rank_Paint = new Paint();
        rank_Paint.setAntiAlias(true);
        rank_Paint.setColor(Color.RED);
        rank_Paint.setStrokeWidth(8);
        rank_Paint.setStyle(Paint.Style.STROKE);//设置空心
        //初始化最外层多边形画笔
        one_paint = new Paint();
        one_paint.setAntiAlias(true);
        one_paint.setColor(getResources().getColor(R.color.one));
        one_paint.setStyle(Paint.Style.FILL);//设置实心
        //初始化第二层多边形画笔
        two_paint = new Paint();
        two_paint.setAntiAlias(true);
        two_paint.setColor(getResources().getColor(R.color.two));
        two_paint.setStyle(Paint.Style.FILL);//设置实心
        //初始化第三层多边形画笔
        three_paint = new Paint();
        three_paint.setAntiAlias(true);
        three_paint.setColor(getResources().getColor(R.color.three));
        three_paint.setStyle(Paint.Style.FILL);//设置实心
        //初始化最内层多边形画笔
        four_paint = new Paint();
        four_paint.setAntiAlias(true);
        four_paint.setColor(getResources().getColor(R.color.four));
        four_paint.setStyle(Paint.Style.FILL);//设置实心
        //初始化中心线画笔
        center_paint = new Paint();
        center_paint.setAntiAlias(true);
        center_paint.setColor(Color.WHITE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawText(canvas);
        drawPolygon(canvas);
        drawProgress(canvas);
        center(canvas);
    }

    /**
     * 画顶角的文字
     * http://chris.banes.me/2014/03/27/measuring-text/
     *
     * @param canvas
     */
    private void drawText(Canvas canvas) {
        canvas.drawText(str[0], center - mTextRect.width() / 2, (float) (getPaddingTop() + 1.5 * mTextRect.height()), mTextPaint);
        canvas.drawText(str[1], ((float) (center + mRadius * Math.sin(Math.toRadians(360 / 7)))),
                (float) (center - mRadius * Math.cos(Math.toRadians(360 / 7))), mTextPaint);
        canvas.drawText(str[2], (float) (center + Math.sin(Math.toRadians(360 / 7 + 360 / 7 / 2)) * mRadius + mTextRect.height() / 2),
                (float) (Math.cos(Math.toRadians(360 / 7 + 360 / 7 / 2)) * mRadius) + center + mTextRect.height() / 2, mTextPaint);
        canvas.drawText(str[3], (float) (center + Math.sin(Math.toRadians(360 / 7 / 2)) * mRadius - mTextRect.height() / 2 + mTextRect.width() / 2),
                (float) ((Math.cos(Math.toRadians(360 / 7 / 2)) * mRadius) + center + mTextRect.height()), mTextPaint);
        canvas.drawText(str[4], (float) (center - Math.sin(Math.toRadians(360 / 7 / 2)) * mRadius + mTextRect.height() / 2 - mTextRect.width() * 1.5),
                (float) ((Math.cos(Math.toRadians(360 / 7 / 2)) * mRadius) + center + mTextRect.height()), mTextPaint);
        canvas.drawText(str[5], (float) (center - Math.sin(Math.toRadians(360 / 7 + 360 / 7 / 2)) * mRadius - mTextRect.height() / 2 - mTextRect.width()),
                (float) (Math.cos(Math.toRadians(360 / 7 + 360 / 7 / 2)) * mRadius) + center + mTextRect.height() / 2, mTextPaint);
        canvas.drawText(str[6], (float) (center - Math.sin(Math.toRadians(360 / 7)) * mRadius - mTextRect.height() / 2 - mTextRect.width()),
                (float) ((getPaddingTop() + 2 * mTextRect.height() + mRadius - Math.abs(Math.cos(Math.toRadians(360 / 7)) * mRadius))), mTextPaint);
    }

    private void drawPolygon(Canvas canvas) {
        onePolygons(canvas);
        twoPolygons(canvas);
        threePolygons(canvas);
        fourPolygons(canvas);

    }

    private void drawProgress(Canvas canvas) {
        Path path = new Path();
        f1 = mRadius-mRadius/4*3;
        path.moveTo(center, getPaddingTop() + 2 * mTextRect.height() + f1);                           //起始点
        path.lineTo((float) (center + Math.sin(Math.toRadians(360 / 7)) * (mRadius - f2)), (float) (getPaddingTop() + 2 * mTextRect.height() + (mRadius) - Math.abs(Math.cos(Math.toRadians(360 / 7)) * (mRadius - f2))));
        path.lineTo((float) (center + Math.sin(Math.toRadians(360 / 7 + 360 / 7 / 2)) * (mRadius - f3)), (float) (Math.cos(Math.toRadians(360 / 7 + 360 / 7 / 2)) * (mRadius - f3)) + center);
        path.lineTo((float) (center + Math.sin(Math.toRadians(360 / 7 / 2)) * (mRadius - f4)), (float) (Math.cos(Math.toRadians(360 / 7 / 2)) * (mRadius - f4)) + center);
        path.lineTo((float) (center - Math.sin(Math.toRadians(360 / 7 / 2)) * (mRadius - f5)), (float) (Math.cos(Math.toRadians(360 / 7 / 2)) * (mRadius - f5)) + center);
        path.lineTo((float) (center - Math.sin(Math.toRadians(360 / 7 + 360 / 7 / 2)) * (mRadius - f6)), (float) (Math.cos(Math.toRadians(360 / 7 + 360 / 7 / 2)) * (mRadius - f6)) + center);
        path.lineTo((float) (center - Math.sin(Math.toRadians(360 / 7)) * (mRadius - f7)), (float) (getPaddingTop() + 2 * mTextRect.height() + (mRadius) - Math.abs(Math.cos(Math.toRadians(360 / 7)) * (mRadius - f7))));
        path.close();
        canvas.drawPath(path, rank_Paint);
    }

    /**
     * //绘制最外层多边形
     *
     * @param canvas
     */
    private void onePolygons(Canvas canvas) {
        Path path = new Path();
        path.moveTo(center, getPaddingTop() + 2 * mTextRect.height());                           //起始点
        path.lineTo((float) (center + Math.sin(Math.toRadians(360 / 7)) * mRadius), (float) (getPaddingTop() + 2 * mTextRect.height() + mRadius - Math.abs(Math.cos(Math.toRadians(360 / 7)) * mRadius)));
        path.lineTo((float) (center + Math.sin(Math.toRadians(360 / 7 + 360 / 7 / 2)) * mRadius), (float) (Math.cos(Math.toRadians(360 / 7 + 360 / 7 / 2)) * mRadius) + center);
        path.lineTo((float) (center + Math.sin(Math.toRadians(360 / 7 / 2)) * mRadius), (float) (Math.cos(Math.toRadians(360 / 7 / 2)) * mRadius) + center);
        path.lineTo((float) (center - Math.sin(Math.toRadians(360 / 7 / 2)) * mRadius), (float) (Math.cos(Math.toRadians(360 / 7 / 2)) * mRadius) + center);
        path.lineTo((float) (center - Math.sin(Math.toRadians(360 / 7 + 360 / 7 / 2)) * mRadius), (float) (Math.cos(Math.toRadians(360 / 7 + 360 / 7 / 2)) * mRadius) + center);
        path.lineTo((float) (center - Math.sin(Math.toRadians(360 / 7)) * mRadius), (float) (getPaddingTop() + 2 * mTextRect.height() + mRadius - Math.abs(Math.cos(Math.toRadians(360 / 7)) * mRadius)));
        path.close();
        canvas.drawPath(path, one_paint);
    }


    /**
     * 绘制第二层多边形
     *
     * @param canvas
     */
    private void twoPolygons(Canvas canvas) {
        distance = mRadius / 4;
        Path path = new Path();
        path.moveTo(center, getPaddingTop() + 2 * mTextRect.height() + distance);                           //起始点
        path.lineTo((float) (center + Math.sin(Math.toRadians(360 / 7)) * (mRadius - distance)), (float) (getPaddingTop() + 2 * mTextRect.height() + (mRadius) - Math.abs(Math.cos(Math.toRadians(360 / 7)) * (mRadius - distance))));
        path.lineTo((float) (center + Math.sin(Math.toRadians(360 / 7 + 360 / 7 / 2)) * (mRadius - distance)), (float) (Math.cos(Math.toRadians(360 / 7 + 360 / 7 / 2)) * (mRadius - distance)) + center);
        path.lineTo((float) (center + Math.sin(Math.toRadians(360 / 7 / 2)) * (mRadius - distance)), (float) (Math.cos(Math.toRadians(360 / 7 / 2)) * (mRadius - distance)) + center);
        path.lineTo((float) (center - Math.sin(Math.toRadians(360 / 7 / 2)) * (mRadius - distance)), (float) (Math.cos(Math.toRadians(360 / 7 / 2)) * (mRadius - distance)) + center);
        path.lineTo((float) (center - Math.sin(Math.toRadians(360 / 7 + 360 / 7 / 2)) * (mRadius - distance)), (float) (Math.cos(Math.toRadians(360 / 7 + 360 / 7 / 2)) * (mRadius - distance)) + center);
        path.lineTo((float) (center - Math.sin(Math.toRadians(360 / 7)) * (mRadius - distance)), (float) (getPaddingTop() + 2 * mTextRect.height() + (mRadius) - Math.abs(Math.cos(Math.toRadians(360 / 7)) * (mRadius - distance))));
        path.close();
        canvas.drawPath(path, two_paint);
    }

    /**
     * 绘制第三层多边形
     *
     * @param canvas
     */
    private void threePolygons(Canvas canvas) {
        distance = mRadius / 2;
        Path path = new Path();
        path.moveTo(center, getPaddingTop() + 2 * mTextRect.height() + distance);                           //起始点
        path.lineTo((float) (center + Math.sin(Math.toRadians(360 / 7)) * (mRadius - distance)), (float) (getPaddingTop() + 2 * mTextRect.height() + (mRadius) - Math.abs(Math.cos(Math.toRadians(360 / 7)) * (mRadius - distance))));
        path.lineTo((float) (center + Math.sin(Math.toRadians(360 / 7 + 360 / 7 / 2)) * (mRadius - distance)), (float) (Math.cos(Math.toRadians(360 / 7 + 360 / 7 / 2)) * (mRadius - distance)) + center);
        path.lineTo((float) (center + Math.sin(Math.toRadians(360 / 7 / 2)) * (mRadius - distance)), (float) (Math.cos(Math.toRadians(360 / 7 / 2)) * (mRadius - distance)) + center);
        path.lineTo((float) (center - Math.sin(Math.toRadians(360 / 7 / 2)) * (mRadius - distance)), (float) (Math.cos(Math.toRadians(360 / 7 / 2)) * (mRadius - distance)) + center);
        path.lineTo((float) (center - Math.sin(Math.toRadians(360 / 7 + 360 / 7 / 2)) * (mRadius - distance)), (float) (Math.cos(Math.toRadians(360 / 7 + 360 / 7 / 2)) * (mRadius - distance)) + center);
        path.lineTo((float) (center - Math.sin(Math.toRadians(360 / 7)) * (mRadius - distance)), (float) (getPaddingTop() + 2 * mTextRect.height() + (mRadius) - Math.abs(Math.cos(Math.toRadians(360 / 7)) * (mRadius - distance))));
        path.close();
        canvas.drawPath(path, three_paint);
    }

    /**
     * 绘制最内层多边形
     *
     * @param canvas
     */
    private void fourPolygons(Canvas canvas) {
        distance = mRadius / 2 + mRadius / 4;
        Path path = new Path();
        path.moveTo(center, getPaddingTop() + 2 * mTextRect.height() + distance);                           //起始点
        path.lineTo((float) (center + Math.sin(Math.toRadians(360 / 7)) * (mRadius - distance)), (float) (getPaddingTop() + 2 * mTextRect.height() + (mRadius) - Math.abs(Math.cos(Math.toRadians(360 / 7)) * (mRadius - distance))));
        path.lineTo((float) (center + Math.sin(Math.toRadians(360 / 7 + 360 / 7 / 2)) * (mRadius - distance)), (float) (Math.cos(Math.toRadians(360 / 7 + 360 / 7 / 2)) * (mRadius - distance)) + center);
        path.lineTo((float) (center + Math.sin(Math.toRadians(360 / 7 / 2)) * (mRadius - distance)), (float) (Math.cos(Math.toRadians(360 / 7 / 2)) * (mRadius - distance)) + center);
        path.lineTo((float) (center - Math.sin(Math.toRadians(360 / 7 / 2)) * (mRadius - distance)), (float) (Math.cos(Math.toRadians(360 / 7 / 2)) * (mRadius - distance)) + center);
        path.lineTo((float) (center - Math.sin(Math.toRadians(360 / 7 + 360 / 7 / 2)) * (mRadius - distance)), (float) (Math.cos(Math.toRadians(360 / 7 + 360 / 7 / 2)) * (mRadius - distance)) + center);
        path.lineTo((float) (center - Math.sin(Math.toRadians(360 / 7)) * (mRadius - distance)), (float) (getPaddingTop() + 2 * mTextRect.height() + (mRadius) - Math.abs(Math.cos(Math.toRadians(360 / 7)) * (mRadius - distance))));
        path.close();
        canvas.drawPath(path, four_paint);
    }



    /**
     * 绘制中心线
     *
     * @param canvas
     */
    private void center(Canvas canvas) {
        //绘制七边形中心线
        canvas.save();//保存当前状态
        canvas.rotate(0, center, center);
        float startY = getPaddingTop() + 2 * mTextRect.height();
        float endY = center;
        float du = (float) (360 / 7 + 0.5);
        for (int i = 0; i < 7; i++) {
            canvas.drawLine(center, startY, center, endY, center_paint);
            canvas.rotate(du, center, center);
        }
        canvas.restore();//恢复之前状态
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int wMode = MeasureSpec.getMode(widthMeasureSpec);
        int wSize = MeasureSpec.getSize(widthMeasureSpec);
        int hMode = MeasureSpec.getMode(heightMeasureSpec);
        int hSize = MeasureSpec.getSize(heightMeasureSpec);
        int width, height;
        if (wMode == MeasureSpec.EXACTLY) {
            width = wSize;
        } else {
            width = Math.min(wSize, defalutSize);
        }
        if (hMode == MeasureSpec.EXACTLY) {
            height = hSize;
        } else {
            height = Math.min(hSize, defalutSize);
        }
        center = width / 2;//中心点
        mRadius = center - getPaddingTop() - 2*mTextRect.height();//中心点距离控件顶部边缘的距离
        f1 = mRadius - mRadius / 4 * 1;
        f2 = mRadius - mRadius / 4 * 1;
        f3 = mRadius - mRadius / 4 * 1;
        f4 = mRadius - mRadius / 4 * 1;
        f5 = mRadius - mRadius / 4 * 1;
        f6 = mRadius - mRadius / 4 * 1;
        f7 = mRadius - mRadius / 4 * 1;
        setMeasuredDimension(width, height);
    }
}
