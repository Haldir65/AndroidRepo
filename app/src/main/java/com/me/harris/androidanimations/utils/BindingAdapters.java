package com.me.harris.androidanimations.utils;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

/**
 * Created by Harris on 2016/9/13.
 */

public class BindingAdapters {
    @BindingAdapter(value = {"imgUrl", "placeHolder"}, requireAll = false)
    public static void loadImage(ImageView view, String url, Drawable error) {
        Glide.with(view.getContext()).load(url).error(error).diskCacheStrategy(DiskCacheStrategy.RESULT).into(view);
    }



    @BindingAdapter({"android:src","placeHolder"})
    public static void setImageUrl(ImageView view, String url, int placeHolder) {
        Glide.with(view.getContext()).load(url).placeholder(placeHolder).diskCacheStrategy(DiskCacheStrategy.RESULT).into(view);
    }
    //用法: android:src="@{contact.largeImageUrl}"
    //       app:placeHolder= "@{R.drawable.contact_placeHolder}"

    @BindingAdapter({"imageSrcId"})
    public static void setImage(ImageView view, int resId) {
        Glide.with(view.getContext()).load(resId).diskCacheStrategy(DiskCacheStrategy.RESULT).into(view);
    }

}
