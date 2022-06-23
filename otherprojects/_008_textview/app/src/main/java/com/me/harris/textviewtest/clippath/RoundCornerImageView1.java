package com.me.harris.textviewtest.clippath;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import androidx.appcompat.widget.AppCompatImageView;
import android.util.AttributeSet;

public class RoundCornerImageView1 extends AppCompatImageView {

    float[] radiusArray = new float[8];


    public RoundCornerImageView1(Context context) {
        super(context);
        init();
    }



    public RoundCornerImageView1(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RoundCornerImageView1(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
//        setRadius(60,60,6,6);
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
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Path path = new Path();
        int width = getWidth();
        int height = getHeight();
        setRadius(width/2,width/2,height/2,height/2);

        path.addRoundRect(new RectF(0, 0, width,height), radiusArray, Path.Direction.CW);
        canvas.clipPath(path);

        super.onDraw(canvas);
    }
}
