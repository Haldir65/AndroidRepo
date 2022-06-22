package com.me.harris.androidanimations._01_drawable.fragment;

import androidx.databinding.DataBindingUtil;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.me.harris.androidanimations.R;
import com.me.harris.androidanimations.databinding.FragmentGradientDrawableBinding;
import com.me.harris.androidanimations.utils.LogUtil;
import com.me.harris.androidanimations.utils.Utils;

/**
 * Created by Harris on 2017/4/9.
 */

public class GradientDrawableFragment extends Fragment {

    FragmentGradientDrawableBinding binding;

    GradientDrawable mDrawable;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_gradient_drawable, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
       /* mDrawable = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT,
                new int[] {ContextCompat.getColor(getActivity(),R.color.md_red_900),
                        ContextCompat.getColor(getActivity(),R.color.md_red_900),
                        ContextCompat.getColor(getActivity(),R.color.md_red_400),
                        ContextCompat.getColor(getActivity(),R.color.md_deep_orange_500),
                        ContextCompat.getColor(getActivity(),R.color.md_yellow_700),
                        ContextCompat.getColor(getActivity(),R.color.md_green_500),
                        ContextCompat.getColor(getActivity(),R.color.md_green_900)});
        mDrawable.setCornerRadii(new float[]{10,10,10,10,10,10,20,20});
        binding.rainBow.setBackground(mDrawable);*/
        binding.textLevel.setTranslationX(0);
        binding.textLevel.setText("My Text");
        binding.textLevel.animate().
                translationX(3*Utils.getScreenWidth(getActivity())/7).
                setDuration(2000)
                .setInterpolator(new AccelerateDecelerateInterpolator())
                .withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        LogUtil.d(binding.textLevel.getWidth()+"宽度");
                    }
                });

    }
}

