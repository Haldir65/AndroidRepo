package com.me.harris.androidanimations.customview.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.ImageView;

import com.me.harris.androidanimations.R;

import java.lang.ref.WeakReference;

/**
 * Created by Fermi on 2016/9/24.
 */

public class RoundImageViewByXfermode extends ImageView {

    private Paint mPaint;
    private Xfermode mXfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);
    private Bitmap mMaskBitmap;

    /**
     * 图片的类型，圆形or圆角
     */
    private int type;
    public static final int TYPE_CIRCLE = 0;
    public static final int TYPE_ROUND = 1;
    /**
     * 圆角大小的默认值
     */
    private static final int BODER_RADIUS_DEFAULT = 10;
    /**
     * 圆角的大小
     */
    private int mBorderRadius;


    private WeakReference<Bitmap> mWeakBitmap;
    public RoundImageViewByXfermode(Context context) {
        this(context,null);
    }

    public RoundImageViewByXfermode(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoundImageViewByXfermode(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.RoundImageViewByXfermode);

        //获得xml中写的自定义属性的定义值
        mBorderRadius = a.getDimensionPixelSize(R.styleable.RoundImageViewByXfermode_borderRadius
                , (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, BODER_RADIUS_DEFAULT, getResources().getDisplayMetrics()));

        type = a.getInt(R.styleable.RoundImageViewByXfermode_type, TYPE_CIRCLE);

        a.recycle();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (type == TYPE_CIRCLE) {
            int width = Math.min(getMeasuredWidth(), getMeasuredHeight());
            setMeasuredDimension(width, width);
        } /*else {

        } */
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //super.onDraw(canvas);
        Bitmap bitmap = mWeakBitmap == null ? null : mWeakBitmap.get();
        if (null == bitmap || bitmap.isRecycled()) {
            Drawable drawable = getDrawable();

            if (drawable != null) {
                int dWidth = drawable.getIntrinsicWidth();
                int dHeight = drawable.getIntrinsicHeight();
                bitmap = Bitmap.
                        createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
                float scale = 1.0f;
                Canvas drawCanvas = new Canvas(bitmap);

                if (type == TYPE_ROUND) {
                    scale = Math.max(getWidth() * 1.0f / dWidth, getHeight() * 1.0f / dHeight);
                } else {
                    scale = getWidth() * 1.f / Math.min(dWidth, dHeight);
                }

                drawable.setBounds(0, 0, (int) (scale * dWidth), ((int) (scale * dHeight)));
                drawable.draw(drawCanvas);
                if (mMaskBitmap == null || mMaskBitmap.isRecycled())
                {
                    mMaskBitmap = getBitmap();
                }

                // Draw Bitmap.
                mPaint.reset();
                mPaint.setFilterBitmap(false);
                mPaint.setXfermode(mXfermode);

                drawCanvas.drawBitmap(mMaskBitmap, 0, 0, mPaint); //关键在于这一点，等于在原来的正方形的图片上盖上一个白色的中心有个内切圆的正方形
                mPaint.setXfermode(null);

                canvas.drawBitmap(bitmap, 0, 0, null);

                mWeakBitmap = new WeakReference<Bitmap>(bitmap);

            }
        }
        if (bitmap != null) {
            mPaint.setXfermode(null);
            canvas.drawBitmap(bitmap, 0f, 0f, mPaint);
        }

    }



    public Bitmap getBitmap()
    {
        Bitmap bitmap = Bitmap.createBitmap(getWidth(), getHeight(),
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.GREEN);

        if (type == TYPE_ROUND)
        {
            canvas.drawRoundRect(new RectF(0, 0, getWidth(), getHeight()),
                    mBorderRadius, mBorderRadius, paint);
        } else
        {
            canvas.drawCircle(getWidth() / 2, getWidth() / 2, getWidth() / 2,
                    paint);
        }

        return bitmap;
    }

    @Override
    public void invalidate()
    {
        mWeakBitmap = null;
        if (mMaskBitmap != null)
        {
            mMaskBitmap.recycle();
            mMaskBitmap = null;
        }
        super.invalidate();
    }
}
