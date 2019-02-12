package com.me.harris.ffmpegintegration;

import android.content.Context;
import android.graphics.PixelFormat;
import android.util.AttributeSet;
import android.view.Surface;
import android.view.SurfaceView;

public class FFCommand extends SurfaceView {

    public FFCommand(Context context) {
        this(context, null);
    }

    public FFCommand(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FFCommand(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        getHolder().setFormat(PixelFormat.RGBA_8888);
    }


    public void play(final String url) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                render(url, FFCommand.this.getHolder().getSurface());
            }
        }).start();
    }

    public native void render(String url, Surface surface);


}
