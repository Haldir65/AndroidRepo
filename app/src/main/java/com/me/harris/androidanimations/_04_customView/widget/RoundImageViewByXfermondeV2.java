package com.me.harris.androidanimations._04_customView.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.me.harris.androidanimations.R;

/**  http://blog.csdn.net/lmj623565791/article/details/24555655
 * 本质上和V1都是一样的,注意这不是继承自ImageView
 * Created by Harris on 2016/9/24.
 */

public class RoundImageViewByXfermondeV2 extends View {
    /**
     * TYPE_CIRCLE / TYPE_ROUND
     */
    private int type;
    private static final int TYPE_CIRCLE = 0;
    private static final int TYPE_ROUND = 1;

    /**
     * 图片
     */
    private Bitmap mSrc;

    /**
     * 圆角的大小
     */
    private int mRadius;

    /**
     * 控件的宽度
     */
    private int mWidth;
    /**
     * 控件的高度
     */
    private int mHeight;


    public RoundImageViewByXfermondeV2(Context context) {
        this(context,null);
    }

    public RoundImageViewByXfermondeV2(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public RoundImageViewByXfermondeV2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs,defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.RoundImageViewByXfermondeV2, defStyleAttr, 0);

        int n = a.getIndexCount();
        for (int i = 0; i < n; i++)
        {
            int attr = a.getIndex(i);
            switch (attr)
            {
                case R.styleable.RoundImageViewByXfermondeV2_src:
                    mSrc = BitmapFactory.decodeResource(getResources(), a.getResourceId(attr, 0));
                    break;
                case R.styleable.RoundImageViewByXfermondeV2_type:
                    type = a.getInt(attr, 0);// 默认为Circle
                    break;
                case R.styleable.RoundImageViewByXfermondeV2_borderRadius:
                    mRadius= a.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10f,
                            getResources().getDisplayMetrics()));// 默认为10DP
                    break;
            }
        }
        a.recycle();
    }

    /**
     * 计算控件的高度和宽度
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        // super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        /**
         * 设置宽度
         */
        int specMode = MeasureSpec.getMode(widthMeasureSpec);
        int specSize = MeasureSpec.getSize(widthMeasureSpec);

        if (specMode == MeasureSpec.EXACTLY)// match_parent , accurate
        {
            mWidth = specSize;
        } else
        {
            // 由图片决定的宽
            int desireByImg = getPaddingLeft() + getPaddingRight()
                    + mSrc.getWidth();
            if (specMode == MeasureSpec.AT_MOST)// wrap_content
            {
                mWidth = Math.min(desireByImg, specSize);
            } else

                mWidth = desireByImg;
        }

        /***
         * 设置高度
         */

        specMode = MeasureSpec.getMode(heightMeasureSpec);
        specSize = MeasureSpec.getSize(heightMeasureSpec);
        if (specMode == MeasureSpec.EXACTLY)// match_parent , accurate
        {
            mHeight = specSize;
        } else
        {
            int desire = getPaddingTop() + getPaddingBottom()
                    + mSrc.getHeight();

            if (specMode == MeasureSpec.AT_MOST)// wrap_content
            {
                mHeight = Math.min(desire, specSize);
            } else
                mHeight = desire;
        }

        setMeasuredDimension(mWidth, mHeight);

    }

    /**
     * 绘制
     */
    @Override
    protected void onDraw(Canvas canvas)
    {

        switch (type)
        {
            // 如果是TYPE_CIRCLE绘制圆形
            case TYPE_CIRCLE:

                int min = Math.min(mWidth, mHeight);
                /**
                 * 长度如果不一致，按小的值进行压缩
                 */
//                mSrc = Bitmap.createScaledBitmap(mSrc, min, min, false);
                mSrc = scaleCenterCrop(mSrc, mHeight, mWidth);
                canvas.drawBitmap(createCircleImage(mSrc, min), 0, 0, null);
                break;
            case TYPE_ROUND:
                canvas.drawBitmap(createRoundConerImage(mSrc), 0, 0, null);
                break;

        }

    }

    /**
     * 根据原图和变长绘制圆形图片
     *
     * @param source
     * @param min
     * @return
     */
    private Bitmap createCircleImage(Bitmap source, int min)
    {
        final Paint paint = new Paint();
        paint.setAntiAlias(true);
        Bitmap target = Bitmap.createBitmap(min, min, Bitmap.Config.ARGB_8888);
        /**
         * 产生一个同样大小的画布
         */
        Canvas canvas = new Canvas(target);
        /**
         * 首先绘制圆形
         */
        canvas.drawCircle(min / 2, min / 2, min / 2, paint);
        /**
         * 使用SRC_IN，参考上面的说明
         */
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        /**
         * 绘制图片
         */
        canvas.drawBitmap(source, 0, 0, paint);
        return target;
    }

    /**
     * 根据原图添加圆角
     *
     * @param source
     * @return
     */
    private Bitmap createRoundConerImage(Bitmap source)
    {
        final Paint paint = new Paint();
        paint.setAntiAlias(true);
        Bitmap target = Bitmap.createBitmap(mWidth, mHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(target);
        RectF rect = new RectF(0, 0, mWidth, mHeight);
        canvas.drawRoundRect(rect, mRadius, mRadius, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(source, 0, 0, paint);
        return target;
    }

    private Bitmap scaleCenterCrop(Bitmap source, int newHeight, int newWidth) {
        int sourceWidth = source.getWidth();
        int sourceHeight = source.getHeight();

        // Compute the scaling factors to fit the new height and width, respectively.
        // To cover the final image, the final scaling will be the bigger
        // of these two.
        float xScale = (float) newWidth / sourceWidth;
        float yScale = (float) newHeight / sourceHeight;
        float scale = Math.max(xScale, yScale);

        // Now get the size of the source bitmap when scaled
        float scaledWidth = scale * sourceWidth;
        float scaledHeight = scale * sourceHeight;

        // Let's find out the upper left coordinates if the scaled bitmap
        // should be centered in the new size give by the parameters
        float left = (newWidth - scaledWidth) / 2;
        float top = (newHeight - scaledHeight) / 2;

        // The target rectangle for the new, scaled version of the source bitmap will now
        // be
        RectF targetRect = new RectF(left, top, left + scaledWidth, top + scaledHeight);

        // Finally, we create a new bitmap of the specified size and draw our new,
        // scaled bitmap onto it.
        Bitmap dest = Bitmap.createBitmap(newWidth, newHeight, source.getConfig());
        Canvas canvas = new Canvas(dest);
        canvas.drawBitmap(source, null, targetRect, null);

        return dest;
    }
}
