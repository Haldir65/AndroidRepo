package com.me.harris.androidanimations._04_customView.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposeShader;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.request.RequestOptions;

import java.security.MessageDigest;

public class RoundCornerImageView extends AppCompatImageView {

    int cornerRadius = 10;

    public RoundCornerImageView(Context context) {
        super(context);
    }

    public RoundCornerImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RoundCornerImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public void load(String url){
        Glide.with(getContext())
                .setDefaultRequestOptions(new RequestOptions().transforms(new MyBitmapTransformation()))
                .load(url)
                .into(this);
    }

    private class MyBitmapTransformation extends BitmapTransformation {

        @Override
        protected Bitmap transform(BitmapPool pool, Bitmap source, int outWidth, int outHeight) {
            if (source == null) return null;
            Canvas canvas = new Canvas(source);
            BitmapShader bitmapShader = new BitmapShader(source, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
            int min = Math.min(source.getWidth(), source.getHeight());
            int radius = min / 2;
            RadialGradient radialGradient = new RadialGradient(source.getWidth() / 2 , source.getHeight() / 2, radius, Color.TRANSPARENT, Color.WHITE, Shader.TileMode.CLAMP);
            ComposeShader composeShader = new ComposeShader(bitmapShader, radialGradient, PorterDuff.Mode.SRC_OVER);
            Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
            paint.setShader(composeShader);
            canvas.drawRect(0, 0, source.getWidth(), source.getHeight(), paint);
            return source;
        }

        @Override
        public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {

        }


    }





}
