package com.me.harris.androidanimations._04_customView.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import com.me.harris.androidanimations.R;

/**
 * Created by Fermi on 2016/9/20.
 */

public class ProgressView extends View {

    private String mText = "";
    private float mTextSize = 100f;
    private float mRadius = 20f;
    private float mStrokeWidth = 10f;
    private int mTextColor = Color.WHITE;
    private int mInnerColor = Color.GREEN;
    private int mOuterColor = Color.BLUE;
    private int mProgressColor = Color.WHITE;
    private int mCurrentProgress = 0;
    private int mMaxProgress = 100;

    private Paint mInnerPaint;
    private Paint mOuterPaint;
    private Paint mProgressPaint;
    private TextPaint mTextPaint;
    private RectF rect;

    public ProgressView(Context context) {
        this(context, null);
    }

    public ProgressView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs, defStyleAttr);
    }

    private void init(AttributeSet attrs, int defStyle) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.ProgressView, defStyle, 0);
        mText = a.getString(R.styleable.ProgressView_text);
        mTextColor = a.getColor(R.styleable.ProgressView_textColor, mTextColor);
        mTextSize = a.getDimension(R.styleable.ProgressView_textSize, (int) mTextSize);
        mInnerColor = a.getColor(R.styleable.ProgressView_innerColor, mInnerColor);
        mOuterColor = a.getColor(R.styleable.ProgressView_outerColor, mOuterColor);
        mProgressColor = a.getColor(R.styleable.ProgressView_progressColor, mProgressColor);
        mRadius = a.getDimension(R.styleable.ProgressView_radius, (int) mRadius);
        mStrokeWidth = a.getDimension(R.styleable.ProgressView_strokeWidth, (int) mStrokeWidth);
        mCurrentProgress = a.getInteger(R.styleable.ProgressView_currentProgress, mCurrentProgress);
        mMaxProgress = a.getInteger(R.styleable.ProgressView_maxProgress, mMaxProgress);

        a.recycle();

        mTextPaint = new TextPaint();
        mTextPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setTextAlign(Paint.Align.CENTER);
        mTextPaint.setTypeface(Typeface.MONOSPACE);
        mTextPaint.setTextSize(mTextSize);
        mTextPaint.setColor(mTextColor);

        mInnerPaint = new Paint();
        mInnerPaint.setAntiAlias(true);
        mInnerPaint.setDither(true);
        mInnerPaint.setStyle(Paint.Style.FILL);
        //mInnerPaint.setStrokeWidth(mStrokeWidth);
        mInnerPaint.setColor(mInnerColor);

        mOuterPaint = new Paint();
        mOuterPaint.setAntiAlias(true);
        mOuterPaint.setStyle(Paint.Style.STROKE);
        mOuterPaint.setStrokeWidth(mStrokeWidth);
        mOuterPaint.setColor(mOuterColor);

        mProgressPaint = new Paint();
        mProgressPaint.setAntiAlias(true);
        mProgressPaint.setStyle(Paint.Style.STROKE);
        mProgressPaint.setStrokeWidth(mStrokeWidth);
        mProgressPaint.setColor(mProgressColor);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int width;
        int height;
        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        } else {
            width = (int) (getPaddingLeft() + mRadius * 2 + mStrokeWidth * 2 + getPaddingRight());
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else {
            height = (int) (getPaddingTop() + mRadius * 2 + mStrokeWidth * 2 + getPaddingBottom());
        }
        setMeasuredDimension(width, height);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();
        int contentWidth = getWidth() - paddingLeft - paddingRight;
        int contentHeight = getHeight() - paddingTop - paddingBottom;

        int centerX = contentWidth / 2, centerY = contentHeight / 2;// view中心点位置
        float angle = mCurrentProgress / (mMaxProgress * 1.0f) * 360; // 进度条的圆弧角度

        if (rect == null) {
            rect = new RectF();
        }
        int viewSize = (int) (mRadius * 2 + mStrokeWidth);
        int left = (int) mStrokeWidth / 2;
        int top = (int) mStrokeWidth / 2;
        int right = left + viewSize;
        int bottom = top + viewSize;
        rect.set(left, top, right, bottom);

        canvas.drawCircle(centerX, centerY, mRadius + mStrokeWidth / 2, mOuterPaint);//stroke 左右两边扩展
        canvas.drawCircle(centerX, centerY, mRadius, mInnerPaint);
        canvas.drawArc(rect, -90, angle, false, mProgressPaint);
        canvas.drawText(mText, centerX, centerY, mTextPaint);

        /*Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLACK);
        canvas.drawRect(rect, paint);
        canvas.drawCircle(contentWidth / 2, contentHeight / 2, mRadius, paint);
        canvas.drawCircle(contentWidth / 2, contentHeight / 2, mRadius + mStrokeWidth, paint);*/
    }
}
