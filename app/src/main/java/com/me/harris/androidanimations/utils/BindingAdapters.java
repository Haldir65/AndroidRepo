package com.me.harris.androidanimations.utils;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.me.harris.androidanimations.R;

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

    @BindingAdapter("animatedVisibility")
    public static void setVisibility(final View view,
                                     final int visibility) {
        // Were we animating before? If so, what was the visibility?
        Integer endAnimVisibility =
                (Integer) view.getTag(R.id.finalVisibility);
        int oldVisibility = endAnimVisibility == null
                ? view.getVisibility()
                : endAnimVisibility;

        if (oldVisibility == visibility) {
            // just let it finish any current animation.
            return;
        }

        boolean isVisibile = oldVisibility == View.VISIBLE;
        boolean willBeVisible = visibility == View.VISIBLE;

        view.setVisibility(View.VISIBLE);
        float startAlpha = isVisibile ? 1f : 0f;
        if (endAnimVisibility != null) {
            startAlpha = view.getAlpha();
        }
        float endAlpha = willBeVisible ? 1f : 0f;

        // Now create an animator
        ObjectAnimator alpha = ObjectAnimator.ofFloat(view,
                View.ALPHA, startAlpha, endAlpha);
        alpha.setAutoCancel(true);

        alpha.addListener(new AnimatorListenerAdapter() {
            private boolean isCanceled;

            @Override
            public void onAnimationStart(Animator anim) {
                view.setTag(R.id.finalVisibility, visibility);
            }

            @Override
            public void onAnimationCancel(Animator anim) {
                isCanceled = true;
            }

            @Override
            public void onAnimationEnd(Animator anim) {
                view.setTag(R.id.finalVisibility, null);
                if (!isCanceled) {
                    view.setAlpha(1f);
                    view.setVisibility(visibility);
                }
            }
        });
        alpha.start();
    }

}
