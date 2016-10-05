package com.me.harris.androidanimations.canvas.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewConfiguration;

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

    private String[] str = {"击杀", "生存", "助攻", "物理", "魔法", "防御", "金钱"};

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
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
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
    }
}
