package com.me.harris.androidanimations.canvas.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

import com.me.harris.androidanimations.R;
import com.me.harris.androidanimations.utils.LogUtil;

/**
 * http://www.jianshu.com/p/f09feb25b54d#
 * Created by Fermi on 2016/9/20.
 * 添加一些自定义Attr
 */

public class SesameCreditView extends View {

    public static final String TAG = SesameCreditView.class.getSimpleName();

    private Paint mArcPaint;
    private Paint mInnerArcPaint;
    private Paint mMiddleArcPaint;

    private Paint mTextPaint;
    private Paint mDashArcPaint;
    private Paint mAvatarPaint;
    private Paint mBackgroundPaint;

    private float mRatio;


    private final int[] mColors = new int[]{0xffff0000, 0xffffff00, 0xff00ff00,
            0xff00ffff, 0xff0000ff, 0xffff00ff};// 渐变色环颜色

    private static final String[] text = {"950","极好","700","优秀","650","良好","600","中等","550","较差","350","很差","150"};


    private int mWidth;//自定义View宽
    private int mHeight;//自定义View高
    private int mBackgroundCorner;//背景四角的弧度


    private int mArcCenterX; //圆弧中心点x坐标
    private int mArcCenterY;// 圆弧中心点Y坐标
    private RectF mArcRect;
    private float mArcWidth;
    private Paint paintGap1;
    private Paint paintGap2;


    public SesameCreditView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    public SesameCreditView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SesameCreditView(Context context) {
        this(context, null);
    }

    private void init() {
        setLayerType(View.LAYER_TYPE_SOFTWARE, null); //关闭硬件加速
        mRatio = 450.f / 525.f;
        mArcPaint = new Paint(Paint.ANTI_ALIAS_FLAG);// 渐变色环画笔，抗锯齿
        Shader s = new SweepGradient(0, 0, mColors, null);
        mArcPaint.setShader(s);
        mArcPaint.setStyle(Paint.Style.STROKE);
        mArcPaint.setStrokeWidth(40);

        mInnerArcPaint = new Paint();

        mMiddleArcPaint = new Paint();

        paintGap1 = new Paint();
        paintGap2 = new Paint();

        mTextPaint = new Paint();

        mAvatarPaint = new Paint();

        mDashArcPaint = new Paint();

        mBackgroundPaint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {


        // 第一步，画背景
        mBackgroundPaint.setAntiAlias(true);
        mBackgroundPaint.setColor(ContextCompat.getColor(getContext(), R.color.md_blue_400));
        canvas.drawRect(0, 0, mWidth, mHeight, mBackgroundPaint);

        //第二部，画圆环，有两点需要注意，一个是Paint的一些set方法，一个是圆环（canvas）的角度
        float r = mArcCenterX - mArcPaint.getStrokeWidth() * 0.5f;
        canvas.save() ;
        canvas.translate(mArcCenterX, mArcCenterX);// 移动中心
        canvas.rotate(150);
        canvas.drawOval(new RectF(-r, -r, r, r), mArcPaint);// 画出色环和中心圆，
        canvas.restore();

        //第三部，画内弧
        mMiddleArcPaint.setColor(Color.GRAY);
        mInnerArcPaint.setColor(Color.GRAY);
        mMiddleArcPaint.setStrokeWidth(4);
        mInnerArcPaint.setStrokeWidth(4);
        mMiddleArcPaint.setStyle(Paint.Style.STROKE);
        mInnerArcPaint.setStyle(Paint.Style.STROKE);
        PathEffect effects = new DashPathEffect(new float[]{5,5,5,5},1);
        mInnerArcPaint.setPathEffect(effects);

        canvas.save() ;
        canvas.translate(mArcCenterX, mArcCenterX);
        canvas.drawCircle(0, 0, mArcCenterX * 5 / 8, mInnerArcPaint  );
        canvas.drawCircle(0, 0, mArcCenterX * 3 / 4, mMiddleArcPaint);
        canvas.restore();

        //第四部，画圆环的分割线
        paintGap1.setColor(Color.WHITE);
        paintGap2.setColor(Color.WHITE);
        paintGap1.setStrokeWidth(2);
        paintGap2.setStrokeWidth(4);

        int a = (int) (2 * mArcCenterX - mArcPaint.getStrokeWidth());
        for ( int i=0;i<=60; i++) {
            canvas.save() ;
            canvas.rotate(-(-30 + 4 * i), mArcCenterX, mArcCenterX);
            if ( i % 10 == 0 ) {
                canvas.drawLine( a ,mArcCenterX, 2 * mArcCenterX, mArcCenterX, paintGap2);
            } else {
                canvas.drawLine( a ,mArcCenterX, 2 * mArcCenterX, mArcCenterX, paintGap1);
            }
            canvas.restore();
        }

        //第五步，圆环变圆弧
        mBackgroundPaint.setAntiAlias(true);
        mBackgroundPaint.setColor(ContextCompat.getColor(getContext(), R.color.md_blue_400));
        float height =  (float) ( ( Math.tan(Math.PI / 6) + 1 ) * (mWidth+mArcWidth) / 2 ) ;
        Path path = new Path();
        path.moveTo(mArcCenterX, mArcCenterX);
        path.lineTo((float) (mArcCenterX+mWidth*(Math.cos(Math.PI/6)/2)), height);
        path.lineTo((float) (mArcCenterX-mWidth*(Math.cos(Math.PI/6)/2)), height);
        path.lineTo(mArcCenterX, mArcCenterX);
        path.close();
        canvas.drawPath(path, mBackgroundPaint);

        mTextPaint.setColor(Color.WHITE);
        mTextPaint.setTextAlign(Paint.Align.CENTER);
        mTextPaint.setTextSize(40.f);


        //第六步，画环形文字
        for (int i = 0; i < 12; i++) {
            canvas.save();
            canvas.rotate(-(-120 + 20 * i), mArcCenterX, mArcCenterX);
            canvas.drawText(text[i], mArcCenterX - 20, mArcCenterY * 3 / 16, mTextPaint);
        }




    }

    /**
     * 主要是为了针对不同尺寸、像素密度屏幕自适应
     *
     * @param w
     * @param h
     * @param oldw
     * @param oldh
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mWidth = w;
        mHeight = h;

        mArcCenterX = (int) (mWidth / 2.f);
        mArcCenterY = (int) (160.f / 525.f * mHeight);
        mArcRect = new RectF();
        //确定外圆环的外切矩形
        mArcRect.left = mArcCenterX - 125.f / 450.f * mWidth;
        mArcRect.top = mArcCenterY - 125.f / 525.f * mHeight;
        mArcRect.right = mArcCenterX + 125.f / 450.f * mWidth;
        mArcRect.bottom = mArcCenterY + 125.f / 525.f * mHeight;

        //设置画壁宽度
        mArcWidth = 20.f / 450.f * mWidth;
        mArcPaint.setStrokeWidth(mArcWidth);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width;
        int height;
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        if (widthMode == MeasureSpec.EXACTLY || widthMode == MeasureSpec.AT_MOST) {
            width = widthSize;
        } else {
            width = com.me.harris.androidanimations.utils.Utils.getScreenWidth(getContext());
        }
        int defaultHeight = (int) (width * 1.f / mRatio);
        height = defaultHeight;
        setMeasuredDimension(width, height);
        LogUtil.w(TAG, " width = " + width + " height = " + height);
    }
}
