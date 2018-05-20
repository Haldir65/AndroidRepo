package com.me.harris.androidanimations._04_customView.fragment;

import android.databinding.DataBindingUtil;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.me.harris.androidanimations.R;
import com.me.harris.androidanimations._04_customView.widget.RoundCornerImageView;
import com.me.harris.androidanimations.databinding.FragmentRoundImageViewBinding;

/**
 * Created by Fermi on 2016/9/24.
 */

public class RoundImageViewsFragment extends Fragment {
    FragmentRoundImageViewBinding binding;

    RoundCornerImageView mIamge007;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_round_image_view, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RoundedBitmapDrawable drawable = RoundedBitmapDrawableFactory.
                create(getResources(), BitmapFactory.decodeResource
                        (getResources(), R.drawable.alamby));
        drawable.setCircular(true);
        binding.image05.setImageDrawable(drawable);
        mIamge007 = view.findViewById(R.id.image07);
        mIamge007.load("http://b.hiphotos.baidu.com/image/pic/item/d8f9d72a6059252d9dedd4b0389b033b5ab5b988.jpg");

    }
}
