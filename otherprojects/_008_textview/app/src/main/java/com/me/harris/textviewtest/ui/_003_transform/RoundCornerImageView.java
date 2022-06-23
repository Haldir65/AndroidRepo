package com.me.harris.textviewtest.ui._003_transform;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import android.util.AttributeSet;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.me.harris.textviewtest.utils.Utils;

import java.security.MessageDigest;

public class RoundCornerImageView extends AppCompatImageView {

    int radius = 10;

    public RoundCornerImageView(Context context) {
        super(context);
    }

    public RoundCornerImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.radius = Utils.dip2px(context, 12f);
    }

    public RoundCornerImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public void load(String url){
        Glide.with(getContext())
//                .applyDefaultRequestOptions(new RequestOptions().transforms(new RoundedCorners(radius*3)))
                .load(url)
                .apply(new RequestOptions().transforms(new RoundedCorners(radius*3)))
                .into(this);
    }

    public void load2(String url){
        Glide.with(getContext())
                .load(url)
                .into(this);
    }

    private static class MyBitmapTransformation extends BitmapTransformation {

        private static final String ID = "com.bumptech.glide.load.resource.bitmap.CenterCrop";
        private static final byte[] ID_BYTES = ID.getBytes(CHARSET);

        private int radius;

        public MyBitmapTransformation(int radius) {
            this.radius = radius;
        }

        @Override
        protected Bitmap transform(BitmapPool pool, Bitmap source, int outWidth, int outHeight) {

            // https://likfe.com/2016/08/31/android-glide-roundedBitmapTransformation/
            if (source == null) return null;
            Bitmap result = pool.get(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
            if (result == null) {
                result = Bitmap.createBitmap(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
            }
            Canvas canvas = new Canvas(result);
            Paint paint = new Paint();
            paint.setShader(new BitmapShader(source, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
            paint.setAntiAlias(true);
            RectF rectF = new RectF(0f, 0f, source.getWidth(), source.getHeight());
            canvas.drawRoundRect(rectF, radius, radius, paint);


//            RoundedBitmapDrawable drawableB = RoundedBitmapDrawableFactory.create(getResources(), bitmap);
//            drawableB.setCornerRadius(30L);
//            Bitmap b = drawableToBitmap(drawableB);

            return result;
        }



        @Override
        public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {

        }


    }





}
