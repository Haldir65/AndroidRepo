package com.me.harris.androidanimations.canvas.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.me.harris.androidanimations.R;
import com.me.harris.androidanimations.databinding.FragmentRoundImageViewBinding;

/**
 * Created by Fermi on 2016/9/24.
 */

public class RoundImageViewsFragment extends Fragment {
    FragmentRoundImageViewBinding binding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding =DataBindingUtil.inflate(inflater, R.layout.fragment_round_image_view, container, false);
        return binding.getRoot();
    }
}
