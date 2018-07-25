package com.me.harris.textviewtest.clippath;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

public class RoundCornerImageView2 extends AppCompatImageView {




    // 四个角的x,y半径
    private float[] radiusArray = { 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f };
    private Paint bitmapPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private Bitmap makeRoundRectFrame(int w, int h) {
        Bitmap bm = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bm);
        Path path = new Path();
        setRadius(w/2,w/2,h/2,h/2);
        path.addRoundRect(new RectF(0, 0, w, h), radiusArray, Path.Direction.CW);
        Paint bitmapPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        bitmapPaint.setColor(Color.GREEN); // 颜色随意，不要有透明度。
        c.drawPath(path, bitmapPaint);
        return bm;
    }

    public RoundCornerImageView2(Context context) {
        super(context);
        init();
    }


    public RoundCornerImageView2(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RoundCornerImageView2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
//        setRadius(60,60,6,6);
//        setLayerType(LAYER_TYPE_SOFTWARE, null); // Xfermode 需要禁用硬件加速
        setScaleType(ScaleType.CENTER_CROP);

    }

    public void setRadius(float leftTop, float rightTop, float rightBottom, float leftBottom) {
        radiusArray[0] = leftTop;
        radiusArray[1] = leftTop;
        radiusArray[2] = rightTop;
        radiusArray[3] = rightTop;
        radiusArray[4] = rightBottom;
        radiusArray[5] = rightBottom;
        radiusArray[6] = leftBottom;
        radiusArray[7] = leftBottom;
    }


    @Override
    protected void onDraw(Canvas canvas) {

        final int w = getWidth();
        final int h = getHeight();
        Bitmap bitmapOriginal = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bitmapOriginal);
        super.onDraw(c);

        Bitmap bitmapFrame = makeRoundRectFrame(w, h);

        int sc = canvas.saveLayer(0, 0, w, h, null);

        canvas.drawBitmap(bitmapFrame, 0, 0, bitmapPaint); //先画一个圆形的框框条条出来
// 利用Xfermode取交集（利用bitmapFrame作为画框来裁剪bitmapOriginal）
        bitmapPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN)); //后续的画图操作，只有交集的部分才会显示在最终的canvas上
        canvas.drawBitmap(bitmapOriginal, 0, 0, bitmapPaint);

        bitmapPaint.setXfermode(null);
        canvas.restoreToCount(sc);

    }
}
