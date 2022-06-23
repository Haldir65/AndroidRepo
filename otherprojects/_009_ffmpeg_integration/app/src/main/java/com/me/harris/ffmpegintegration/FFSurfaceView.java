package com.me.harris.ffmpegintegration;

import android.content.Context;
import android.graphics.PixelFormat;
import android.util.AttributeSet;
import android.view.Surface;
import android.view.SurfaceView;

public class FFSurfaceView extends SurfaceView {

    public FFSurfaceView(Context context) {
        this(context, null);
    }

    public FFSurfaceView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FFSurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        getHolder().setFormat(PixelFormat.RGBA_8888);
    }


    public void play(final String url) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                render(url, FFSurfaceView.this.getHolder().getSurface());
            }
        }).start();
    }

    public native void render(String url, Surface surface);


}
