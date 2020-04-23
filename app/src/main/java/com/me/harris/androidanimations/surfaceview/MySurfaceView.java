package com.me.harris.androidanimations.surfaceview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.me.harris.androidanimations.R;
import com.me.harris.androidanimations.utils.LogUtil;

/**
 * Created by Fermi on 2016/10/18.
 */

public class MySurfaceView extends SurfaceView {

    private SurfaceHolder surfaceHolder;
    private Bitmap bmpIcon;
    private MyThread myThread;
    int xPos = 0;
    int yPos = 0;
    int deltaX = 5;
    int deltaY = 5;
    int iconWidth;
    int iconHeight;

    public MySurfaceView(Context context) {
        this(context, null);
    }

    public MySurfaceView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MySurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        myThread = new MyThread(this);
        surfaceHolder = getHolder();

        bmpIcon = BitmapFactory.decodeResource(getResources(), R.drawable.fan_xxhdpi);
        iconWidth = bmpIcon.getWidth();
        iconHeight = bmpIcon.getHeight();
        surfaceHolder.addCallback(new SurfaceHolder.Callback(){

            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                myThread.setRunning(true);
                myThread.start();
                LogUtil.w("");

            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
                LogUtil.w("");

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                boolean retry = true;
                myThread.setRunning(false);
                LogUtil.w("");
                while (retry) {
                    try {
                        myThread.join();
                        retry = false;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public void drawSomething(Canvas canvas) {

        canvas.drawBitmap(bmpIcon,
                getWidth()/2, getHeight()/2, null);

        xPos += deltaX;
        if(deltaX > 0){
            if(xPos >= getWidth() - iconWidth){
                deltaX *= -1;
            }
        }else{
            if(xPos <= 0){
                deltaX *= -1;
            }
        }

        yPos += deltaY;
        if(deltaY > 0){
            if(yPos >= getHeight() - iconHeight){
                deltaY *= -1;
            }
        }else{
            if(yPos <= 0){
                deltaY *= -1;
            }
        }

        canvas.drawColor(ContextCompat.getColor(getContext(),R.color.md_blue_200));
        canvas.drawBitmap(bmpIcon,
                xPos, yPos, null);
    }
}
