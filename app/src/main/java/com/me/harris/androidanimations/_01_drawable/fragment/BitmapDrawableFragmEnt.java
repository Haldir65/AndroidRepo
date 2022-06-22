package com.me.harris.androidanimations._01_drawable.fragment;

import androidx.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.me.harris.androidanimations.R;
import com.me.harris.androidanimations.databinding.FragmentBitmapDrawableBinding;

/**
 * Created by Fermi on 2016/10/8.
 */

public class BitmapDrawableFragmEnt extends Fragment {
    FragmentBitmapDrawableBinding binding;

    public static BitmapDrawableFragmEnt newInstance() {
        Bundle args = new Bundle();
        BitmapDrawableFragmEnt fragment = new BitmapDrawableFragmEnt();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_bitmap_drawable, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.image_18);
        BitmapDrawable mBitmapDrawable = new BitmapDrawable(getResources(), bitmap);
        mBitmapDrawable.setTileModeXY(Shader.TileMode.REPEAT, Shader.TileMode.CLAMP);
        mBitmapDrawable.setAntiAlias(true);
        mBitmapDrawable.setDither(true); //防抖动
        binding.imageView2.setImageDrawable(mBitmapDrawable);
    }
}
